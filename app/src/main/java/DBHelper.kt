import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object DBContract {
    // Table contents are grouped together in an anonymous object.
    object DBEntry : BaseColumns {
        const val TABLE_NAME = "factory"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_COUNTER = "counter"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${DBEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${DBEntry.COLUMN_NAME_NAME} TEXT," +
                "${DBEntry.COLUMN_NAME_COUNTER} INTEGER)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DBEntry.TABLE_NAME}"
}

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DBContract.SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DBContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 0
        const val DATABASE_NAME = "CookieClickerFactory.db"
    }

}