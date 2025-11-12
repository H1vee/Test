package com.example.reserve

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLogo()
        setupMarquee()
    }

    private fun setupLogo() {
        val tvLogo = findViewById<TextView>(R.id.tvLogo)
        val tvLogoPlus = findViewById<TextView>(R.id.tvLogoPlus)

        // Встановлюємо текст
        tvLogo.text = "Резерв"
        tvLogoPlus.text = "+"
    }

    private fun setupMarquee() {
        val tvMarquee = findViewById<TextView>(R.id.tvMarquee)
        tvMarquee.isSelected = true
    }
}