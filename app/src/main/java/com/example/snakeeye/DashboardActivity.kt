package com.example.snakeeye

import RecentActivityAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.snakeeye.databinding.ActivityHomeBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var identifySnakeCard: CardView
    private lateinit var nearbySightingsCard: CardView
    private lateinit var speciesGuideCard: CardView
    private lateinit var emergencyContactsCard: CardView
    private lateinit var activityRecyclerView: RecyclerView
    private lateinit var activityAdapter: RecentActivityAdapter
    private lateinit var viewAllText: TextView
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var headerSection: LinearLayout

    // Bottom Navigation Views
    private lateinit var navHome: LinearLayout
    private lateinit var navSpecies: LinearLayout
    private lateinit var navCamera: LinearLayout
    private lateinit var navSettings: LinearLayout
    private lateinit var navProfile: LinearLayout
    private lateinit var ivHome: ImageView
    private lateinit var tvHome: TextView
    private lateinit var ivSpecies: ImageView
    private lateinit var tvSpecies: TextView
    private lateinit var ivCamera: ImageView
    private lateinit var tvCamera: TextView
    private lateinit var ivSettings: ImageView
    private lateinit var tvSettings: TextView
    private lateinit var ivProfile: ImageView
    private lateinit var tvProfile: TextView

    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Setup edge-to-edge display
        setupSystemBars()

        initViews()
        setupSystemWindowInsets()
        setupRecyclerView()
        setupClickListeners()
        setupBottomNavigation()

        // Set Home as selected by default
        setSelectedTab(0)


    }

    private fun initViews() {
        identifySnakeCard = findViewById(R.id.identifySnakeCard)
        nearbySightingsCard = findViewById(R.id.nearbySightingsCard)
        speciesGuideCard = findViewById(R.id.speciesGuideCard)
        emergencyContactsCard = findViewById(R.id.emergencyContactsCard)
        activityRecyclerView = findViewById(R.id.activityRecyclerView)
        viewAllText = findViewById(R.id.viewAllText)
        nestedScrollView = findViewById(R.id.nestedScrollView)
        headerSection = findViewById(R.id.headerSection)

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
    }

    private fun setupRecyclerView() {
        val activities = getSampleActivities()

        activityAdapter = RecentActivityAdapter(activities) { activity ->
            // Handle activity item click
            Toast.makeText(this, "Clicked on ${activity.title}", Toast.LENGTH_SHORT).show()
        }

        activityRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = activityAdapter
            // Disable nested scrolling to work properly with NestedScrollView
            isNestedScrollingEnabled = false
        }

        // Enable smooth scrolling for the NestedScrollView
        nestedScrollView.isSmoothScrollingEnabled = true
    }

    private fun setupClickListeners() {
        identifySnakeCard.setOnClickListener {
            // Navigate to camera/identify screen
            setSelectedTab(2) // Select camera tab
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Opening snake identification", Toast.LENGTH_SHORT).show()
        }

        nearbySightingsCard.setOnClickListener {
            // Navigate to nearby sightings
            Toast.makeText(this, "Opening nearby sightings", Toast.LENGTH_SHORT).show()
        }

        speciesGuideCard.setOnClickListener {
            // Navigate to species guide
            setSelectedTab(1) // Select species tab
            // TODO: Navigate to species guide activity
            Toast.makeText(this, "Opening species guide", Toast.LENGTH_SHORT).show()
        }

        emergencyContactsCard.setOnClickListener {
            // Navigate to emergency contacts
            Toast.makeText(this, "Opening emergency contacts", Toast.LENGTH_SHORT).show()
        }

        viewAllText.setOnClickListener {
            // Navigate to full activity list
            Toast.makeText(this, "Opening full activity list", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        navHome.setOnClickListener {
            setSelectedTab(0)
            // Already on home, maybe scroll to top
            nestedScrollView.smoothScrollTo(0, 0)
        }

        navSpecies.setOnClickListener {
            setSelectedTab(1)
            // TODO: Navigate to SpeciesGuideActivity
            val intent = Intent(this, SafetyTipsActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Opening Safety Tips", Toast.LENGTH_SHORT).show()
        }

        navCamera.setOnClickListener {
            setSelectedTab(2)

            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show()
        }

        navSettings.setOnClickListener {
            setSelectedTab(3)
            // Navigate to SettingsActivity
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Opening Settings", Toast.LENGTH_SHORT).show()

        }

        navProfile.setOnClickListener {
            setSelectedTab(4)
            // TODO: Navigate to ProfileActivity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Opening Profile", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSelectedTab(selectedIndex: Int) {
        // Reset all tabs to unselected state
        resetAllTabs()

        // Set selected tab
        val activeColor = ContextCompat.getColor(this, R.color.colorPrimary) // #2E7D32
        val inactiveColor = ContextCompat.getColor(this, android.R.color.darker_gray) // #999999

        when (selectedIndex) {
            0 -> { // Home
                tvHome.setTextColor(activeColor)
                // You might want to change the icon to filled version
            }
            1 -> { // Species
                tvSpecies.setTextColor(activeColor)
            }
            2 -> { // Camera
                tvCamera.setTextColor(activeColor)
            }
            3 -> { // Settings
                tvSettings.setTextColor(activeColor)
            }
            4 -> { // Profile
                tvProfile.setTextColor(activeColor)
            }
        }
    }

    private fun resetAllTabs() {
        val inactiveColor = ContextCompat.getColor(this, android.R.color.darker_gray)

        tvHome.setTextColor(inactiveColor)
        tvSpecies.setTextColor(inactiveColor)
        tvCamera.setTextColor(inactiveColor)
        tvSettings.setTextColor(inactiveColor)
        tvProfile.setTextColor(inactiveColor)
    }

    override fun onResume() {
        super.onResume()
        // Reset to home tab when returning to dashboard
        setSelectedTab(0)
    }

    private fun getSampleActivities(): List<SnakeActivity> {
        return listOf(
            SnakeActivity(
                id = "1",
                title = "Russell's Viper Incident",
                activityType = ActivityType.SNAKE_BITING,
                venomousCategory = VenomousCategory.HIGHLY_VENOMOUS,
                location = "Colombo, Western Province",
                reporter = "Dr. Silva",
                timeAgo = "2h ago",
                icon = "🐍"
            ),
            SnakeActivity(
                id = "2",
                title = "Python Rescue Operation",
                activityType = ActivityType.SNAKE_RESCUE,
                venomousCategory = VenomousCategory.NON_VENOMOUS,
                location = "Kandy, Central Province",
                reporter = "Wildlife Team",
                timeAgo = "4h ago",
                icon = "🐍"
            ),
            SnakeActivity(
                id = "3",
                title = "Cobra Sighting Report",
                activityType = ActivityType.SNAKE_SIGHTING,
                venomousCategory = VenomousCategory.HIGHLY_VENOMOUS,
                location = "Galle, Southern Province",
                reporter = "Local Resident",
                timeAgo = "6h ago",
                icon = "🐍"
            ),
            SnakeActivity(
                id = "4",
                title = "Research Study - Rat Snake",
                activityType = ActivityType.SNAKE_RESEARCHING,
                venomousCategory = VenomousCategory.NON_VENOMOUS,
                location = "University of Peradeniya",
                reporter = "Research Team",
                timeAgo = "8h ago",
                icon = "🔬"
            ),
            SnakeActivity(
                id = "5",
                title = "Green Vine Snake Exploration",
                activityType = ActivityType.SNAKE_EXPLORING,
                venomousCategory = VenomousCategory.MILDLY_VENOMOUS,
                location = "Sinharaja Forest Reserve",
                reporter = "Nature Guide",
                timeAgo = "1d ago",
                icon = "🌿"
            ),
            SnakeActivity(
                id = "6",
                title = "Safe Snake Capture",
                activityType = ActivityType.SNAKE_CATCHING,
                venomousCategory = VenomousCategory.MODERATELY_VENOMOUS,
                location = "Anuradhapura, North Central",
                reporter = "Snake Handler",
                timeAgo = "1d ago",
                icon = "🤲"
            )
        )
    }

    private fun setupSystemBars() {
        // Make the app draw behind the status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    )
        }
    }

    private fun setupSystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(headerSection) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                view.paddingLeft,
                systemBars.top + 16, // Add 16dp extra padding below status bar
                view.paddingRight,
                view.paddingBottom
            )
            insets
        }
    }
}