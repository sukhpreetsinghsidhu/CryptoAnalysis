package com.example.cryptoanalysis.ui.view

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cryptoanalysis.data.model.User
import com.example.cryptoanalysis.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var db: FirebaseFirestore //Firestore Database/Cloud Firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.firestore
        auth = Firebase.auth

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        with(binding) {
            val userNameT = name.text.toString()
            val emailT = email.text.toString()
            val passwordT = password.text.toString()

            // check validation
            when {
                userNameT.isEmpty() -> {
                    name.error = "this field is required"
                    name.requestFocus()
                }
                emailT.isEmpty() -> {
                    email.error = "this field is required"
                    email.requestFocus()
                }
                passwordT.isEmpty() -> {
                    password.error = "this field is required"
                    password.requestFocus()
                }
                passwordT.length < 6 -> {
                    password.error = "password at least 6 character"
                    password.requestFocus()
                }
                else -> {
                    //                binding.progressBar.visibility = View.VISIBLE
                    //                binding.btnRegister.visibility = View.INVISIBLE
                    auth.createUserWithEmailAndPassword(emailT, passwordT)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {

                                auth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                                    // register success, store data
                                    // hardcoded at the moment
                                    val user = User(
                                        "someone",
                                        "somewhere",
                                        emailT,
                                        12345675,
                                        "somewhere in canada"
                                    )
                                    db.collection("users")
                                        .add(user)
                                        .addOnSuccessListener { documentReference ->
                                            Log.d(
                                                TAG,
                                                "DocumentSnapshot added with ID: ${documentReference.id}"
                                            )
                                        }
                                        .addOnFailureListener { e ->
                                            Log.w(TAG, "Error adding document", e)
                                        }
                                    //                            binding.progressBar.visibility = View.GONE
                                    //                            startActivity(Intent(this, LoginActivity::class.java))
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Register successfully! Please verify your email",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Register failed!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                }
            }
        }
    }
}