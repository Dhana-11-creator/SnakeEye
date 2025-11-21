package com.example.snakeeye

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        pager = findViewById(R.id.viewPager)
        val skip = findViewById<TextView>(R.id.skipText)

        val pages = listOf(
            OnboardingItem(R.drawable.ic_camera, getString(R.string.ob1_title), getString(R.string.ob1_desc), getString(R.string.next)),
            OnboardingItem(R.drawable.ic_learn,  getString(R.string.ob2_title), getString(R.string.ob2_desc), getString(R.string.next)),
            OnboardingItem(R.drawable.ic_track,  getString(R.string.ob3_title), getString(R.string.ob3_desc), getString(R.string.lets_go))
        )

        pager.adapter = OnboardingAdapter(pages) { pos ->
            if (pos < pages.size - 1) pager.currentItem = pos + 1
            else finishOnboarding()
        }

        skip.setOnClickListener { finishOnboarding() }

        // Optional: small slide animation
        pager.setPageTransformer { page, position ->
            page.alpha = 0.2f + (1 - kotlin.math.abs(position)) * 0.8f
            page.translationX = -position * page.width * 0.1f
        }
    }

    private fun finishOnboarding() {
        Prefs(this).setOnboardingSeen(true)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
