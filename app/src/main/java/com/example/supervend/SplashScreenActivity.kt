package com.example.supervend

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.HandlerCompat.postDelayed
import pl.droidsonroids.gif.GifImageView

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val splashScreenTime = 5000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.splash_screen)

        val sp = getSharedPreferences("settings", MODE_PRIVATE)

        if (sp.getBoolean("darkMode",
                this.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES)){
            val myEdit = sp.edit()
            myEdit.putBoolean("darkMode", true)
            myEdit.apply()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else{
            val myEdit = sp.edit()
            myEdit.putBoolean("darkMode", false)
            myEdit.apply()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Animations
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val botAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        // Hooks
        val gif = findViewById<GifImageView>(R.id.logoGIF)
        val logoName = findViewById<TextView>(R.id.logoTXT)
        gif.animation = topAnim
        logoName.animation = botAnim

        // Go to next activity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Onboarding::class.java)
            startActivity(intent)
            finish()
        }, splashScreenTime)
    }
}