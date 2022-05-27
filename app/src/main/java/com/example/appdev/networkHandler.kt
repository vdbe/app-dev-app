package com.example.appdev

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class networkHandler(private val context: Context) {
    var quote : MutableLiveData<String> = MutableLiveData()
    private val scope = CoroutineScope(Dispatchers.Default)

    init {
        scope.launch {
            getQuote()
        }
    }

    private fun getQuote() {
        var url = URL("http://whatthecommit.com/index.txt");


        quote.postValue("changes?");

        try {
            with(url.openConnection() as HttpURLConnection) {
                Log.d("ABCDEFG", "1");
                requestMethod = "GET"

                BufferedReader(InputStreamReader(inputStream)).use {
                    Log.d("ABCDEFG", "2");
                    quote.postValue(it.readText());
                }
            }
        } catch (E: Exception) {
            Log.d("ABCDEFG", E.toString());
        }
    }
}