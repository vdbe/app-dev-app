package com.example.appdev

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceScreen
import androidx.preference.PreferenceManager
import androidx.preference.PreferenceFragmentCompat
import androidx.appcompat.app.AppCompatDelegate


class SettingsActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    lateinit var settingsFragment: SettingsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsFragment = SettingsFragment()
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, settingsFragment)
                .commit()
        }

        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        lateinit var preferences: PreferenceScreen

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences, rootKey)

            preferences = preferenceScreen
            val prefScreen = preferenceScreen
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        when(key) {
            "quote_preference" -> {
                Log.d("SETTINGS", key);
            }
            "toast_preference" -> {
                Log.d("SETTINGS", key);
            }
        }
    }
}