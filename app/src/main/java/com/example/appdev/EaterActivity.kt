package com.example.appdev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appdev.eaterList.EaterAdapter

class EaterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eater)

        // Get the Intent that started this activity and extract the factory name
        val counter = intent.getIntExtra("COOKIE_COUNT", 0)

        val eatList = Array(counter) { i -> i }
        val recyclerView: RecyclerView = findViewById(R.id.bob)
        val layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = EaterAdapter(eatList)

    }
}