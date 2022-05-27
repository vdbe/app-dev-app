package com.example.appdev

import DBHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.preference.PreferenceManager

class Factory : AppCompatActivity() {
    private var counter = 0;
    private var factoryName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factory)

        // Get the Intent that started this activity and extract the factory name
        val message = intent.getStringExtra(FACTORY_TITLE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.factoryNameTextView).apply {
            text = message
        }

        if (message != null) {
            this.factoryName = message

            val db = DBHelper(this, null)
            val cursor = db.getFactoryByName(message)

            if (cursor.count > 0) {
                cursor.moveToFirst()
                this.counter = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME_COUNTER))

                findViewById<TextView>(R.id.counterTextView).apply {
                    text = counter.toString();
                }
            } else {
                db.addFactory(this.factoryName, 0)
            }

            db.close()
        }
    }

    override fun onPause() {
        val db = DBHelper(this, null)
        val cursor = db.getFactoryByName(this.factoryName)

        if (cursor.count > 0) {
            cursor.moveToFirst()

            val name = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME_NAME))
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DBHelper.COLUMN_NAME_ID))

            db.editFactory(id, this.factoryName, this.counter)
        }
        db.close()
        super.onPause()
    }

    fun cookieClick(view: View) {
        this.counter += 1;
        findViewById<TextView>(R.id.counterTextView).apply {
            text = counter.toString();
        }

        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("toast_preference", false)) {
            val toast = Toast.makeText(applicationContext, "COOKIE!!!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    fun eatCookies(view: View) {
        var counter = this.counter
        val intent = Intent(this, EaterActivity::class.java).apply {
            putExtra("COOKIE_COUNT", counter)
        }
        //startActivity(intent)
        cookiesEat.launch(intent)
    }

    private val cookiesEat = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        Log.d("CONSUMED", "IDFK")
    }
}
