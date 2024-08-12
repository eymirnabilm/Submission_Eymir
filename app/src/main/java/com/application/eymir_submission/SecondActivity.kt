package com.application.eymir_submission

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        supportActionBar?.title = "Second Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("name")

        val nameTextView: TextView = findViewById(R.id.nameTV)
        val chooseUserButton: AppCompatButton = findViewById(R.id.chooseUserBtn)

        nameTextView.text = name

        chooseUserButton.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SELECT_USER)
        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_USER && resultCode == Activity.RESULT_OK) {
            val selectedUserName = data?.getStringExtra("selectedUserName")
            val selectedUserTextView: TextView = findViewById(R.id.selectedUserTV)
            selectedUserTextView.text = selectedUserName
        }
    }

    companion object {
        const val REQUEST_CODE_SELECT_USER = 1
    }
}