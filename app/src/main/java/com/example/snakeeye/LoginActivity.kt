package com.example.snakeeye

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
class LoginActivity : AppCompatActivity() {

//    private lateinit var btnBack: ImageView
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnContinue: MaterialButton
    private lateinit var btnGoogleSignIn: MaterialButton
    private lateinit var btnAppleSignIn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.mint_bg)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
//        btnBack = findViewById(R.id.btnBack)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnContinue = findViewById(R.id.btnContinue)
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn)
        btnAppleSignIn = findViewById(R.id.btnAppleSignIn)
    }

    private fun setupClickListeners() {
//        btnBack.setOnClickListener {
//            finish() // Go back to previous screen
//        }

        btnContinue.setOnClickListener {
            // TODO: Handle login logic
            // For now, just navigate to main activity or dashboard
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        btnGoogleSignIn.setOnClickListener {
            // TODO: Handle Google Sign In
        }

        btnAppleSignIn.setOnClickListener {
            // TODO: Handle Apple Sign In
        }

        // Optional: Add Sign Up navigation if you add the tvSignUp TextView
        findViewById<TextView>(R.id.tvSignUp)?.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}