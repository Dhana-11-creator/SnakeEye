package com.example.snakeeye

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

abstract class BaseNavigationActivity : AppCompatActivity() {

    // Bottom Navigation Views
    protected lateinit var navHome: LinearLayout
    protected lateinit var navSpecies: LinearLayout
    protected lateinit var navCamera: LinearLayout
    protected lateinit var navSettings: LinearLayout
    protected lateinit var navProfile: LinearLayout
    protected lateinit var ivHome: ImageView
    protected lateinit var tvHome: TextView
    protected lateinit var ivSpecies: ImageView
    protected lateinit var tvSpecies: TextView
    protected lateinit var ivCamera: ImageView
    protected lateinit var tvCamera: TextView
    protected lateinit var ivSettings: ImageView
    protected lateinit var tvSettings: TextView
    protected lateinit var ivProfile: ImageView
    protected lateinit var tvProfile: TextView

    abstract fun getSelectedTabIndex(): Int

    protected fun initBottomNavigation() {
        try {
            // Initialize Bottom Navigation Views
            navHome = findViewById(R.id.navHome)
            navSpecies = findViewById(R.id.navSpecies)
            navCamera = findViewById(R.id.navCamera)
            navSettings = findViewById(R.id.navSettings)
            navProfile = findViewById(R.id.navProfile)

            ivHome = findViewById(R.id.ivHome)
            tvHome = findViewById(R.id.tvHome)
            ivSpecies = findViewById(R.id.ivSpecies)
            tvSpecies = findViewById(R.id.tvSpecies)
            ivCamera = findViewById(R.id.ivCamera)
            tvCamera = findViewById(R.id.tvCamera)
            ivSettings = findViewById(R.id.ivSettings)
            tvSettings = findViewById(R.id.tvSettings)
            ivProfile = findViewById(R.id.ivProfile)
            tvProfile = findViewById(R.id.tvProfile)

            setupBottomNavigationListeners()
            setSelectedTab(getSelectedTabIndex())
        } catch (e: Exception) {
            // Handle case where bottom navigation is not present in layout
            // This allows activities without bottom nav to extend this class too
        }
    }

    private fun setupBottomNavigationListeners() {
        navHome.setOnClickListener {
            if (getSelectedTabIndex() != 0) {
                navigateToActivity(DashboardActivity::class.java, 0)
            }
        }

        navSpecies.setOnClickListener {
            if (getSelectedTabIndex() != 1) {
                navigateToActivity(SafetyTipsActivity::class.java, 1)
            }
        }

        navCamera.setOnClickListener {
            if (getSelectedTabIndex() != 2) {
                navigateToActivity(CameraActivity::class.java, 2)
            }
        }

        navSettings.setOnClickListener {
            if (getSelectedTabIndex() != 3) {
                navigateToActivity(SettingsActivity::class.java, 3)
            }
        }

        navProfile.setOnClickListener {
            if (getSelectedTabIndex() != 4) {
                navigateToActivity(ProfileActivity::class.java, 4)
            }
        }
    }

    private fun navigateToActivity(activityClass: Class<*>, tabIndex: Int) {
        try {
            val intent = Intent(this, activityClass)
            startActivity(intent)
            overridePendingTransition(0, 0) // Remove transition animation for smooth tab switching
        } catch (e: Exception) {
            // Handle case where activity doesn't exist yet
            val activityName = when (tabIndex) {
                0 -> "Home"
                1 -> "Safety Tips"
                2 -> "Camera"
                3 -> "Settings"
                4 -> "Profile"
                else -> "Unknown"
            }
            Toast.makeText(this, "Opening $activityName", Toast.LENGTH_SHORT).show()
        }
    }

    protected fun setSelectedTab(selectedIndex: Int) {
        try {
            // Reset all tabs to unselected state
            resetAllTabs()

            // Set selected tab
            val activeColor = ContextCompat.getColor(this, R.color.colorPrimary) // #2E7D32

            when (selectedIndex) {
                0 -> tvHome.setTextColor(activeColor)
                1 -> tvSpecies.setTextColor(activeColor)
                2 -> tvCamera.setTextColor(activeColor)
                3 -> tvSettings.setTextColor(activeColor)
                4 -> tvProfile.setTextColor(activeColor)
            }
        } catch (e: Exception) {
            // Handle gracefully if views are not found
        }
    }

    private fun resetAllTabs() {
        try {
            val inactiveColor = ContextCompat.getColor(this, android.R.color.darker_gray)

            tvHome.setTextColor(inactiveColor)
            tvSpecies.setTextColor(inactiveColor)
            tvCamera.setTextColor(inactiveColor)
            tvSettings.setTextColor(inactiveColor)
            tvProfile.setTextColor(inactiveColor)
        } catch (e: Exception) {
            // Handle gracefully if views are not found
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            setSelectedTab(getSelectedTabIndex())
        } catch (e: Exception) {
            // Handle gracefully
        }
    }
}