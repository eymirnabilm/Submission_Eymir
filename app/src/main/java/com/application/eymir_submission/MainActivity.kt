package com.application.eymir_submission

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameET)
        val sentenceInput = findViewById<EditText>(R.id.sentenceET)
        val checkButton = findViewById<Button>(R.id.checkBtn)
        val nextButton = findViewById<Button>(R.id.nextBtn)

        checkButton.setOnClickListener {
            val sentence = sentenceInput.text.toString()
            if (isPalindrome(sentence)) {
                showAlertDialog("Palindrome")
            } else {
                showAlertDialog("Not Palindrome")
            }
        }

        nextButton.setOnClickListener {
            val name = nameInput.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }

    private fun isPalindrome(input: String): Boolean {
        val cleanedInput = input.replace("\\s".toRegex(), "").lowercase()
        return cleanedInput == cleanedInput.reversed()
    }

    private fun showAlertDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}