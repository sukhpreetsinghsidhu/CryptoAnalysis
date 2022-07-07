package com.example.cryptoanalysis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptoanalysis.ui.view.LoginActivity
import com.example.cryptoanalysis.ui.view.MainpageActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, LoginActivity::class.java)
        //val intent = Intent(this, MainpageActivity::class.java)
// To pass any data to next activity
       // intent.putExtra("keyIdentifier", value)
// start your next activity
        startActivity(intent)
    }
}