package com.teguh.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.teguh.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // deklarasi variabel untuk data binding
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // sintkas diatas diganti karena kalau make databinding, sintaksnya yg dibawah ini

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // ketika done button diklik
        binding.doneButton.setOnClickListener() {
            addNickName(it) //it mengirim nilai ke parameter addNickName, pada function addNickname variabelnya view dan objeknya View
            printButtonClickLog()
        }

        // ketika hasil tetview diklik
        binding.nicknameTextview.setOnClickListener(){
            updateNickName(it) //it mengirim ke parameter addNickName, variabelnya view dan objeknya View
        }

    }

    // function button done click
    private fun addNickName(view: View) {

        binding.nicknameTextview.text = binding.nicknameEdit.text.toString() // mengambil nilai edittext ke textview

        binding.nicknameEdit.visibility = View.GONE
        binding.doneButton.visibility = View.GONE // menghilangkan button done, karena nilai view adalah doneButton
        binding.nicknameTextview.visibility = View.VISIBLE

        // meng-hide keyboard setelah proses diatas selesai
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
    }

    // function kalau setelah diklik done kita mau update name
    private fun updateNickName(view: View){

        binding.apply {
            binding.nicknameEdit.visibility = View.VISIBLE
            binding.doneButton.visibility = View.VISIBLE
            binding.nicknameTextview.visibility = View.GONE
        }

        binding.nicknameEdit.requestFocus()

        // munculkan keyboard
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.nicknameEdit, 0)
    }

    // function log
    private fun printButtonClickLog() {
        Log.i("MainActivity", "klik btn done berhasil")
    }
}