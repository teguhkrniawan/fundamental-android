package com.teguh.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val doneButton = findViewById<Button>(R.id.done_button)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_textview)

        // ketika done button diklik
        doneButton.setOnClickListener() {
            addNickName(it) //it mengirim nilai ke parameter addNickName, pada function addNickname variabelnya view dan objeknya View
            printButtonClickLog()
        }

        // ketika hasil tetview diklik
        nicknameTextView.setOnClickListener(){
            updateNickName(it) //it mengirim ke parameter addNickName, variabelnya view dan objeknya View
        }

    }

    // function button done click
    private fun addNickName(view: View) {

        val nameEditText = findViewById<EditText>(R.id.nickname_edit)
        val nicknameTextView = findViewById<TextView>(R.id.nickname_textview)

        nicknameTextView.text = nameEditText.text // mengambil nilai edittext ke textview

        nameEditText.visibility = View.GONE
        view.visibility = View.GONE // menghilangkan button done, karena nilai view adalah doneButton
        nicknameTextView.visibility = View.VISIBLE

        // meng-hide keyboard setelah proses diatas selesai
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }

    // function kalau setelah diklik done kita mau update name
    private fun updateNickName(view: View){
        val nameEditText = findViewById<EditText>(R.id.nickname_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        nameEditText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE
        // biar fokus ke edit text
        nameEditText.requestFocus()
        // munculkan keyboard
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(nameEditText, 0)
    }

    // function log
    private fun printButtonClickLog() {
        Log.i("MainActivity", "klik btn done berhasil")
    }
}