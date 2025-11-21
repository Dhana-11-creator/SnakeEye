package com.example.snakeeye

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.snakeeye.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Button Actions
        binding.btnIdentifySnake.setOnClickListener {
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        binding.btnViewSightings.setOnClickListener {
            // TODO: Navigate to Sightings screen
        }

//        binding.btnLogin.setOnClickListener {
//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
//        }

        binding.btnLogin.setOnClickListener {
            // Navigate to Login Activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // If you have a Sign Up button in your home layout, add this:
//        binding.btnSignUp?.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
//        }
    }
}
