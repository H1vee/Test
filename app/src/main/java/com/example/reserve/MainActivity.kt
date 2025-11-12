package com.example.reserve

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLogo()
    }

    private fun setupLogo() {
        val tvLogo = findViewById<TextView>(R.id.tvLogo)
        val logoText = "Резерв+"
        val spannable = SpannableString(logoText)

        // Зробити "+" оранжевим
        val orangeColor = ContextCompat.getColor(this, R.color.orange_bright)
        spannable.setSpan(
            ForegroundColorSpan(orangeColor),
            6, // позиція "+"
            7, // кінець "+"
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        tvLogo.text = spannable
    }
}