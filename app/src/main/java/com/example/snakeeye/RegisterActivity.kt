package com.example.snakeeye

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
class RegisterActivity : AppCompatActivity() {

//    private lateinit var btnBack: ImageView
    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnSignIn: MaterialButton
    private lateinit var btnGoogleSignIn: MaterialButton
    private lateinit var btnAppleSignIn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Set status bar color
        window.statusBarColor = ContextCompat.getColor(this, R.color.mint_bg)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
//        btnBack = findViewById(R.id.btnBack)
        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        btnSignIn = findViewById(R.id.btnSignIn)
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn)
        btnAppleSignIn = findViewById(R.id.btnAppleSignIn)
    }

    private fun setupClickListeners() {
//        btnBack.setOnClickListener {
//            finish() // Go back to previous screen
//        }

        btnSignIn.setOnClickListener {
            // TODO: Handle sign up logic
            // For now, navigate to login activity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        btnGoogleSignIn.setOnClickListener {
            // TODO: Handle Google Sign In
        }

        btnAppleSignIn.setOnClickListener {
            // TODO: Handle Apple Sign In
        }

        // Optional: Add Login navigation if you add the tvLogin TextView
        findViewById<TextView>(R.id.tvLogin)?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}