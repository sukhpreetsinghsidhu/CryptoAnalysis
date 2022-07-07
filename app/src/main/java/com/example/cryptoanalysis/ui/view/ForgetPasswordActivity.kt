package com.example.cryptoanalysis.ui.view

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cryptoanalysis.MainActivity
import com.example.cryptoanalysis.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.resetpassword.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        with(binding) {
            val email = enteremail.text.toString()
            resetpassword.isEnabled = false

            if (email.isEmpty()) {
                enteremail.error = "Email is required"
                enteremail.requestFocus()
            } else {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener {
                        resetpassword.isEnabled = true
                        if (it.isSuccessful) {
                            Toast.makeText(
                                this@ForgetPasswordActivity,
                                "Please check your email address to reset your password",
                                Toast.LENGTH_LONG
                            ).show()
                            val intent =
                                Intent(this@ForgetPasswordActivity, MainActivity::class.java)
                            startActivity(intent)
                        } else {
                            Log.e(TAG, "sendEmailVerification", it.exception)
                            Toast.makeText(
                                this@ForgetPasswordActivity,
                                "Failed to send verification email.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }
}