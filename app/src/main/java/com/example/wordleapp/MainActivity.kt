package com.example.wordleapp

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.wordleapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var numberOfAttempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        val game = Game(binding)

        val word = game.getRandomWord()

        val userName = intent.getStringExtra("Username")

        binding.txtWelcome.setText("Welcome $userName")

        val wordBoard = WordBoard()

        for (i in 0..4)
        {
            wordBoard.createRow(this, binding)
        }


        game.keepFocus()

        game.loopChild(word)

        val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)

        btnPlayAgain.setOnClickListener() {
            val intent = intent
            startActivity(intent)
        }

    }

}