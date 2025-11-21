package com.example.snakeeye

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.coroutines.*
import java.net.URL

class CameraActivity : BaseNavigationActivity() {

    private lateinit var cameraPreview: ImageView
    private lateinit var captureButton: CardView
    private lateinit var uploadPhotoCard: CardView
    private lateinit var selectPhotoButton: Button

    // Activity result launchers
    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            imageBitmap?.let {
                cameraPreview.setImageBitmap(it)
                // TODO: Process the captured image for snake identification
                processSnakeIdentification(it)
            }
        }
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    cameraPreview.setImageBitmap(bitmap)
                    // TODO: Process the selected image for snake identification
                    processSnakeIdentification(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val cameraPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(this, "Camera permission is required", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getSelectedTabIndex(): Int = 2 // Camera tab (adjust index based on your navigation)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        // Set status bar color to match your design
        window.statusBarColor = ContextCompat.getColor(this, R.color.mint_bg)

        initViews()
        initBottomNavigation()
        setupClickListeners()
        loadDefaultSnakeImage()
    }

    private fun initViews() {
        cameraPreview = findViewById(R.id.cameraPreview)
        captureButton = findViewById(R.id.captureButton)
        uploadPhotoCard = findViewById(R.id.uploadPhotoCard)
        selectPhotoButton = findViewById(R.id.selectPhotoButton)
    }

    private fun loadDefaultSnakeImage() {
        // Load a snake image from URL
        val snakeImageUrl = "https://images.unsplash.com/photo-1516975080664-ed2fc6a32937?w=800&h=600&fit=crop&crop=center"

        // Using coroutines to load image from URL
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url = URL(snakeImageUrl)
                val inputStream = url.openConnection().getInputStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)

                // Switch back to main thread to update UI
                withContext(Dispatchers.Main) {
                    cameraPreview.setImageBitmap(bitmap)
                }
            } catch (e: Exception) {
                // If URL loading fails, load a local drawable as fallback
                withContext(Dispatchers.Main) {
                    loadLocalSnakeImage()
                }
            }
        }
    }

    private fun loadLocalSnakeImage() {
        // Fallback: Load a snake image from drawable resources
        // You can add a snake image to your drawable folder and use it here
        try {
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.snake)
            cameraPreview.setImageBitmap(bitmap)
        } catch (e: Exception) {
            // If no local image available, show placeholder
            cameraPreview.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray))
        }
    }

    private fun setupClickListeners() {
        // Camera capture button
        captureButton.setOnClickListener {
            if (checkCameraPermission()) {
                openCamera()
            } else {
                requestCameraPermission()
            }
        }

        // Upload photo card
        uploadPhotoCard.setOnClickListener {
            openGallery()
        }

        // Select photo button
        selectPhotoButton.setOnClickListener {
            openGallery()
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(packageManager) != null) {
            cameraLauncher.launch(cameraIntent)
        } else {
            Toast.makeText(this, "Camera not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        if (galleryIntent.resolveActivity(packageManager) != null) {
            galleryLauncher.launch(galleryIntent)
        } else {
            Toast.makeText(this, "Gallery not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun processSnakeIdentification(bitmap: Bitmap) {
        // TODO: Implement snake identification logic here
        // This could involve:
        // 1. Sending the image to a ML model
        // 2. Making an API call to identify the snake
        // 3. Showing results in a new activity or dialog

        Toast.makeText(this, "Processing snake identification...", Toast.LENGTH_SHORT).show()

        // For now, just show a placeholder message
        // Later you can navigate to a results activity or show a dialog
        // startActivity(Intent(this, IdentificationResultActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel any ongoing coroutines to prevent memory leaks
        CoroutineScope(Dispatchers.IO).cancel()
    }
}