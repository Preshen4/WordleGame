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
        val word = getRandomWord().uppercase()

        val userName = intent.getStringExtra("Username")

        val txtWelcome = findViewById<TextView>(R.id.txtWelcome)

        txtWelcome.setText("Welcome $userName")

        val wordBoard = WordBoard()

        for (i in 0..4)
        {
            wordBoard.createRow(this, binding)
        }


        keepFocus()

        loopChild(word, ::populateList)

        val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)

        btnPlayAgain.setOnClickListener() {
            val intent = intent
            startActivity(intent)
        }

    }

    fun getRandomWord():String{
        val words = arrayOf("apple", "banana", "carat", "dance", "eager", "flair", "grape", "hazel",
            "igloo", "jolly", "kitty", "lemon", "mango", "noble", "olive", "piano", "queen", "rumba",
            "silly", "tiger", "unzip", "vital", "wedge", "xenon", "yacht", "zebra")
        val randomIndex = Random().nextInt(words.size)
        return words[randomIndex]
    }


    fun validateRow(editTextList: MutableList<EditText>, word: String) {
        val wordChars = word.toCharArray()
        numberOfAttempts++
        var correctLetters = 0 // Initialize the counter

        editTextList.forEachIndexed { index, editText ->
            val editTextChar = editText.text.toString().uppercase().getOrNull(0)
            val wordChar = wordChars.getOrNull(index)

            val backgroundColor = when {
                editTextChar == null -> Color.WHITE // Empty EditText
                editTextChar == wordChar -> {
                    correctLetters += 1 // Increment the counter
                    Color.parseColor("#33cc33") // Correct letter
                }
                editTextChar !in word -> Color.RED // Letter not in word
                else -> Color.parseColor("#ffff00") // Incorrect letter
            }

            editText.setBackgroundColor(backgroundColor)
        }

        if (correctLetters == word.length) {

            val winText = findViewById<TextView>(R.id.Win)
            winText.visibility = View.VISIBLE

            val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)
            btnPlayAgain.visibility = View.VISIBLE


            val mainLayout = findViewById<LinearLayout>(R.id.main_layout)
            val mainLayoutCount = mainLayout.childCount  - 1

            for (i in 0..mainLayoutCount)  {
                val childLayout= mainLayout.getChildAt(i) as LinearLayout
                val childLayoutCount = childLayout.childCount - 1


                for (j in 0..childLayoutCount) {
                    val editTxt = childLayout.getChildAt(j) as EditText
                    editTxt.isEnabled = false
                }
            }

        }

        if (numberOfAttempts == 5 && correctLetters != word.length) {
            val winText = findViewById<TextView>(R.id.Win)
            winText.setText("Game Over")
            winText.visibility = View.VISIBLE
            val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)
            btnPlayAgain.visibility = View.VISIBLE
        }

    }

    fun keepFocus(){

        val editTextList = mutableListOf<EditText>()
        val mainLayout = findViewById<LinearLayout>(R.id.main_layout)
        val mainLayoutCount = mainLayout.childCount  - 1

        for (i in 0..mainLayoutCount)  {
            val childLayout= mainLayout.getChildAt(i) as LinearLayout
            val childLayoutCount = childLayout.childCount - 1


            for (j in 0..childLayoutCount) {
                editTextList.add(childLayout.getChildAt(j) as EditText)
            }
        }

        for (i in 0 until editTextList.size - 1) {
            getNewLetter(editTextList[i], editTextList[i + 1])
        }
    }

    fun getNewLetter(edt1 : EditText, edt2 : EditText)
    {

        edt1.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(edt: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(edt: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(edt: Editable?) {
                if (edt?.length == 1)
                {
                    edt2.requestFocus()
                }
            }

        })
    }

    fun loopChild(word: String, addList: (input: MutableList<EditText>, childElement: EditText) -> Unit) {
        val mainLayout = findViewById<LinearLayout>(R.id.main_layout)
        val mainLayoutCount = mainLayout.childCount  - 1
        for (i in 0..mainLayoutCount)  {
            val childLayout= mainLayout.getChildAt(i) as LinearLayout
            val childLayoutCount = childLayout.childCount - 1
            val editTextList = mutableListOf<EditText>()

            for (j in 0..childLayoutCount) {
                //editTextList.add(childLayout.getChildAt(j) as EditText)
                addList(editTextList, childLayout.getChildAt(j) as EditText)

            }
            val row =  childLayout.getChildAt(childLayoutCount) as EditText
            row.addTextChangedListener() {
                validateRow(editTextList, word)
            }
        }
    }

    fun populateList(input: MutableList<EditText>, childElement: EditText) {
        input.add(childElement)
    }

    /*fun addEditText() {
        val mainLayout = findViewById<LinearLayout>(R.id.main_layout)
        val mainLayoutCount = mainLayout.childCount - 1
        for (i in 0..mainLayoutCount) {
            val childLayout = mainLayout.getChildAt(i) as LinearLayout
            val childLayoutCount = childLayout.childCount - 1
            val editTextList = mutableListOf<EditText>()

            for (j in 0..childLayoutCount) {
                //editTextList.add(childLayout.getChildAt(j) as EditText)
                childLayout.addView(generateEditText(this))

            }
        }
    }*/
}