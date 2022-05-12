import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns



class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "CookieClickerFactory.db"


        const val TABLE_NAME = "factory"
        const val COLUMN_NAME_ID = "_id"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_COUNTER = "counter"

        const val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${COLUMN_NAME_NAME} TEXT," +
                    "${COLUMN_NAME_COUNTER} INTEGER)"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${TABLE_NAME}"
    }

    fun getFactories(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun getFactoryByName(name: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_NAME_NAME  IS ? LIMIT 1",
            arrayOf<String>(name)
        )
    }

    fun addFactory(name: String, counter: Int) {
        val values = ContentValues()
        values.put(COLUMN_NAME_NAME, name)
        values.put(COLUMN_NAME_COUNTER, counter)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun editFactory(id: Int, name: String, counter: Int) {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_NAME_NAME, name)
        values.put(COLUMN_NAME_COUNTER, counter)

        db.update(
            TABLE_NAME, values,
            "$COLUMN_NAME_ID IS ?", arrayOf<String>(id.toString())
        )
        db.close()
    }

    fun deleteFactory(id: Int) {
        val db = this.writableDatabase

        db.delete(
            TABLE_NAME,
            "$COLUMN_NAME_ID IS ?",
            arrayOf<String>(id.toString())
        )
        db.close()
    }
}