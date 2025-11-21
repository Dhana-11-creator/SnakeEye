package com.example.snakeeye

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.widget.LinearLayout

class ProfileActivity : BaseNavigationActivity() {

    override fun getSelectedTabIndex(): Int = 4 // Profile tab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Set status bar color to match your design
        window.statusBarColor = ContextCompat.getColor(this, R.color.mint_bg)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.profileToolbar)
        setSupportActionBar(toolbar)

        // Remove default title, use our custom TextView instead
        supportActionBar?.setDisplayShowTitleEnabled(false)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        initBottomNavigation()
        setupProfileData()
        setupProfileClickListeners()
    }

    private fun setupProfileData() {
        // Set profile data - you can replace these with actual user data
        val profileName = findViewById<TextView>(R.id.profileName)
        val profileLocation = findViewById<TextView>(R.id.profileLocation)
        val profilePhone = findViewById<TextView>(R.id.profilePhone)
        val totalSightings = findViewById<TextView>(R.id.totalSightings)
        val speciesIdentified = findViewById<TextView>(R.id.speciesIdentified)
        val achievementPercent = findViewById<TextView>(R.id.achievementPercent)

        // You can fetch this data from SharedPreferences, database, or API
        profileName?.text = "Dhanujaya Surage"
        profileLocation?.text = "Colombo, Sri Lanka"
        profilePhone?.text = "070-xxx-xxx"
        totalSightings?.text = "18"
        speciesIdentified?.text = "24"
        achievementPercent?.text = "90%"

        // Set profile image if needed
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        // You can load an image from URI, drawable, or URL here
        // For example: Glide.with(this).load(userImageUrl).into(profileImage)
    }

    private fun setupProfileClickListeners() {
        // Profile image click listener
        findViewById<androidx.cardview.widget.CardView>(R.id.profileImageCard)?.setOnClickListener {
            Toast.makeText(this, "Edit profile picture", Toast.LENGTH_SHORT).show()
            // TODO: Implement image picker to change profile picture
        }

        // Profile header click listener
        findViewById<androidx.cardview.widget.CardView>(R.id.profileHeaderCard)?.setOnClickListener {
            Toast.makeText(this, "Edit profile", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to edit profile activity
        }

        // Add click listeners for statistics cards if needed
        setupStatisticsClickListeners()

        // Add click listeners for achievement cards if needed
        setupAchievementClickListeners()
    }

    private fun setupStatisticsClickListeners() {
        // Total Sightings click
        findViewById<LinearLayout>(R.id.sightingsContainer)?.setOnClickListener {
            Toast.makeText(this, "View all sightings", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to sightings list activity
        }

        // Species Identified click
        findViewById<LinearLayout>(R.id.speciesContainer)?.setOnClickListener {
            Toast.makeText(this, "View identified species", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to identified species list
        }

        // Achievement percentage click
        findViewById<LinearLayout>(R.id.achievementContainer)?.setOnClickListener {
            Toast.makeText(this, "View achievement details", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to detailed achievements view
        }
    }

    private fun setupAchievementClickListeners() {
        // Since we don't have individual IDs for achievement cards,
        // we'll add click listeners to the entire achievement section for now

        // You can add individual achievement click handling here
        // For example, showing achievement details in a dialog



        // For demonstration, we'll show a toast when achievements are clicked
        // In a real app, you might want to:
        // 1. Show achievement details in a dialog
        // 2. Navigate to a detailed achievements activity
        // 3. Show progress towards locked achievements
    }

    // Method to update user statistics (call this when data changes)
    fun updateStatistics(sightings: Int, species: Int, achievement: Int) {
        findViewById<TextView>(R.id.totalSightings)?.text = sightings.toString()
        findViewById<TextView>(R.id.speciesIdentified)?.text = species.toString()
        findViewById<TextView>(R.id.achievementPercent)?.text = "${achievement}%"
    }

    // Method to update profile information
    fun updateProfileInfo(name: String, location: String, phone: String) {
        findViewById<TextView>(R.id.profileName)?.text = name
        findViewById<TextView>(R.id.profileLocation)?.text = location
        findViewById<TextView>(R.id.profilePhone)?.text = phone
    }

    // Method to load profile image from URL or URI
    fun loadProfileImage(imageUrl: String?) {
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        // TODO: Use an image loading library like Glide or Picasso
        // Glide.with(this)
        //     .load(imageUrl)
        //     .placeholder(R.drawable.ic_profile)
        //     .error(R.drawable.ic_profile)
        //     .into(profileImage)
    }

    // Method to refresh all profile data (useful for when returning from edit profile)
    fun refreshProfileData() {
        // TODO: Fetch updated data from your data source
        setupProfileData()
    }

    // Method to handle achievement unlock animation
    private fun unlockAchievement(achievementName: String) {
        Toast.makeText(this, "Achievement Unlocked: $achievementName!", Toast.LENGTH_LONG).show()
        // TODO: Add animation or special UI effect for achievement unlock
    }

    // Method to calculate and update achievement percentage
    private fun updateAchievementPercentage() {
        // TODO: Calculate based on actual unlocked achievements
        val totalAchievements = 4 // Update based on your actual achievement system
        val unlockedAchievements = 2 // Count from your data
        val percentage = (unlockedAchievements * 100) / totalAchievements
        findViewById<TextView>(R.id.achievementPercent)?.text = "${percentage}%"
    }
}
