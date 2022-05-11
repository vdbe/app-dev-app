package com.example.appdev

import DBHelper
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.log

class Factory : AppCompatActivity() {
    private var counter = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_factory)

        // Get the Intent that started this activity and extract the factory name
        val message = intent.getStringExtra(FACTORY_TITLE)

        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.factoryNameTextView).apply {
            text = message
        }
    }

    fun cookieClick(view: View) {
        this.counter += 1;
        Log.d( "COOCKIE", this.counter.toString());
        val textView = findViewById<TextView>(R.id.counterTextView).apply {
            text = counter.toString();
        }
    }
}