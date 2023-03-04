package com.example.wordleapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.wordleapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val word = getRandomWord()

        keepFocus()

        binding.edt15.addTextChangedListener {
            validateRow(binding.edt11,binding.edt12,binding.edt13,binding.edt14,binding.edt15, word)
        }

        binding.edt25.addTextChangedListener {
            validateRow(binding.edt21,binding.edt22,binding.edt23,binding.edt24,binding.edt25, word)
        }

        binding.edt35.addTextChangedListener {
            validateRow(binding.edt31,binding.edt32,binding.edt33,binding.edt34,binding.edt35, word)
        }

        binding.edt45.addTextChangedListener {
            validateRow(binding.edt41,binding.edt42,binding.edt43,binding.edt44,binding.edt45, word)
        }

        binding.edt55.addTextChangedListener {
            validateRow(binding.edt51,binding.edt52,binding.edt53,binding.edt54,binding.edt55, word)
        }

    }

    fun getRandomWord():String{
        val words = arrayOf("apple", "banana", "carat", "dance", "eager", "flair", "grape", "hazel",
            "igloo", "jolly", "kitty", "lemon", "mango", "noble", "olive", "piano", "queen", "rumba",
            "silly", "tiger", "unzip", "vital", "wedge", "xenon", "yacht", "zebra")
        val randomIndex = Random().nextInt(words.size)
        return words[randomIndex]
    }


    fun validateRow(edt1: EditText, edt2: EditText, edt3: EditText, edt4: EditText, edt5: EditText, word: String) {
        val editTexts = listOf(edt1, edt2, edt3, edt4, edt5)
        val wordChars = word.toCharArray()

        var correctLetters = 0 // Initialize the counter

        editTexts.forEachIndexed { index, editText ->
            val editTextChar = editText.text.toString().getOrNull(0)
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
            Toast.makeText(edt1.context, "Congratulations, you've solved it!", Toast.LENGTH_SHORT).show()
            binding.Win.visibility = View.VISIBLE
        }
    }

    fun keepFocus(){

        val editTexts = listOf(
            binding.edt11, binding.edt12, binding.edt13, binding.edt14, binding.edt15,
            binding.edt21, binding.edt22, binding.edt23, binding.edt24, binding.edt25,
            binding.edt31, binding.edt32, binding.edt33, binding.edt34, binding.edt35,
            binding.edt41, binding.edt42, binding.edt43, binding.edt44, binding.edt45,
            binding.edt51, binding.edt52, binding.edt53, binding.edt54, binding.edt55
        )

        for (i in 0 until editTexts.size - 1) {
            getNewLetter(editTexts[i], editTexts[i + 1])
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

}