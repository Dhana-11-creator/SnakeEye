package com.example.snakeeye

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SafetyTipsActivity : BaseNavigationActivity() {

    override fun getSelectedTabIndex(): Int = 1 // (example: second tab, adjust as needed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safety_tips)

        // Setup toolbar
        val toolbar: Toolbar = findViewById(R.id.safetyToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        initBottomNavigation()

        // Emergency button + contacts
        val btnEmergency: Button = findViewById(R.id.btnEmergency)
        val txtHospital: TextView = findViewById(R.id.txtHospital)
        val txtSnakeBite: TextView = findViewById(R.id.txtSnakeBite)
        val txtWildLife: TextView = findViewById(R.id.txtWildLife)

        btnEmergency.setOnClickListener { dialNumber("119") }
        txtHospital.setOnClickListener { dialNumber("+94112691111") }
        txtSnakeBite.setOnClickListener { dialNumber("+94117778888") }
        txtWildLife.setOnClickListener { dialNumber("+94117248258") }
    }

    private fun dialNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }
}
