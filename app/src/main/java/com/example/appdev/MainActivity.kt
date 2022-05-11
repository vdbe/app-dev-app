package com.example.appdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

const val FACTORY_TITLE = "com.example.app.FACTORY_NAME"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun spinUpFactory(view: View) {
        val editText = findViewById<EditText>(R.id.inputFactoryName)
        val message = editText.text.toString()
        val intent = Intent(this, Factory::class.java).apply {
            putExtra(FACTORY_TITLE, message)
        }
        startActivity(intent)
    }
}