package com.example.snakeeye

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SettingsActivity : BaseNavigationActivity() {

    override fun getSelectedTabIndex(): Int = 3 // Settings tab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Set status bar color to match your design
        window.statusBarColor = ContextCompat.getColor(this, R.color.mint_bg)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.settingsToolbar)
        setSupportActionBar(toolbar)

        // Remove default title, use our custom TextView instead
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        initBottomNavigation()
        setupSettingsClickListeners()

    }

    private fun setupSettingsClickListeners() {
        // Dark Mode toggle
        findViewById<androidx.cardview.widget.CardView>(R.id.darkModeCard)?.setOnClickListener {
            Toast.makeText(this, "Dark mode toggle clicked", Toast.LENGTH_SHORT).show()
            // TODO: Implement dark mode toggle logic
        }

        // Notifications toggle
        findViewById<androidx.cardview.widget.CardView>(R.id.notificationsCard)?.setOnClickListener {
            Toast.makeText(this, "Notifications toggle clicked", Toast.LENGTH_SHORT).show()
            // TODO: Implement notifications toggle logic
        }

        // Language selection
        findViewById<androidx.cardview.widget.CardView>(R.id.languageCard)?.setOnClickListener {
            Toast.makeText(this, "Language selection clicked", Toast.LENGTH_SHORT).show()
            // TODO: Show language selection dialog
        }

        // Share with Friends
        findViewById<androidx.cardview.widget.CardView>(R.id.shareCard)?.setOnClickListener {
            Toast.makeText(this, "Share with friends clicked", Toast.LENGTH_SHORT).show()
            // TODO: Implement share functionality
        }

        // Sign out
        findViewById<androidx.cardview.widget.CardView>(R.id.signOutCard)?.setOnClickListener {
            // TODO: Implement sign out logic
                // Navigate to camera/identify screen
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "User Signing Out", Toast.LENGTH_SHORT).show()

        }
    }
}