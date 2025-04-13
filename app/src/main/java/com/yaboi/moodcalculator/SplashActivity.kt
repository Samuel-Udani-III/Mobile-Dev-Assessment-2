package com.yaboi.moodcalculator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Find the splash logo ImageView
        val logo = findViewById<ImageView>(R.id.splashLogo)

        // Set the initial alpha to 0 to make the logo invisible
        logo.alpha = 0f

        // Animate the logo to fade in over 800ms
        logo.animate().alpha(1f).setDuration(800).start()

        // Delay for 2 seconds before transitioning to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Start MainActivity
            startActivity(Intent(this, MainActivity::class.java))
            // Finish the splash screen activity so user can't go back to it
            finish()
        }, 2000) // Delay of 2 seconds
    }
}
