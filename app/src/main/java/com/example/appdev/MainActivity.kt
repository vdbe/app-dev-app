package com.example.appdev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.preference.PreferenceManager

const val FACTORY_TITLE = "com.example.app.FACTORY_NAME"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        val quoteText = findViewById<TextView>(R.id.quoteTextView)
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("quote_preference", false)) {
            val api = networkHandler(this)
            api.quote.observe(this) {
                quoteText.text = "git commit -a -m \'" + it.toString().trim() + "\'";
            }
        }
    }

    fun spinUpFactory(view: View) {
        val editText = findViewById<EditText>(R.id.inputFactoryName)
        val message = editText.text.toString()
        val factoryIntent = Intent(this, Factory::class.java).apply {
            putExtra(FACTORY_TITLE, message)
        }
        startActivity(factoryIntent)
    }

    fun openSettings(view: View) {
        val settingsIntent = Intent(this, SettingsActivity::class.java).apply { }
        startActivity(settingsIntent)
    }

}