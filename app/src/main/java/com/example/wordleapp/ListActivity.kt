package com.example.wordleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var wordList : Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        wordList = arrayOf("apple", "banana", "carat", "dance", "eager", "flair", "grape", "hazel",
            "igloo", "jolly", "kitty", "lemon", "mango", "noble", "olive", "piano", "queen", "rumba",
            "silly", "tiger", "unzip", "vital", "wedge", "xenon", "yacht", "zebra")

        recyclerView = findViewById(R.id.wordRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = WordAdapter(wordList)
    }
}