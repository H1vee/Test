package com.example.reserve

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvMarquee: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private var bottomSheetDialog: BottomSheetDialog? = null
    private var updateDialog: AlertDialog? = null

    companion object {
        private const val PREFS_NAME = "ReservePrefs"
        private const val KEY_LAST_UPDATE = "last_update_time"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        tvMarquee = findViewById(R.id.tvMarquee)


        tvMarquee.ellipsize = TextUtils.TruncateAt.MARQUEE
        tvMarquee.marqueeRepeatLimit = -1
        tvMarquee.isSingleLine = true
        tvMarquee.isSelected = true


        updateMarqueeText()
        val btnMenu = findViewById<View>(R.id.btnMenu)
        btnMenu.setOnClickListener {
            showBottomSheet()
        }
    }
    private fun showBottomSheet() {
        bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_menu, null)
        bottomSheetDialog?.setContentView(view)

        val btnRefreshDocument = view.findViewById<View>(R.id.btnRefreshDocument)
        btnRefreshDocument.setOnClickListener {
            bottomSheetDialog?.dismiss()
            showUpdateDialog()
        }

        bottomSheetDialog?.show()
    }

    private fun showUpdateDialog() {
        val cardDocument = findViewById<View>(R.id.cardDocument)
        val dialogView = layoutInflater.inflate(R.layout.dialog_update_document, null)

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        builder.setCancelable(false)

        updateDialog = builder.create()
        updateDialog?.window?.apply {
            setBackgroundDrawableResource(android.R.color.transparent)

            setLayout(
                (cardDocument.width * 0.85).toInt(),
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        val btnUpdate = dialogView.findViewById<View>(R.id.btnUpdate)
        val btnCancel = dialogView.findViewById<View>(R.id.btnCancel)

        btnUpdate.setOnClickListener {
            updateDialog?.dismiss()
            simulateDocumentGeneration()
        }

        btnCancel.setOnClickListener {
            updateDialog?.dismiss()
        }

        updateDialog?.show()

        updateDialog?.window?.setLayout(
            (cardDocument.width * 0.85).toInt(),
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    private fun simulateDocumentGeneration() {
        Handler(Looper.getMainLooper()).postDelayed({
            updateDocument()
        }, 500)
    }

    private fun updateDocument() {
        val currentTime = System.currentTimeMillis()

        // Зберігаємо час оновлення
        sharedPreferences.edit()
            .putLong(KEY_LAST_UPDATE, currentTime)
            .apply()

        // Оновлюємо текст на екрані
        updateMarqueeText()

        // Показуємо повідомлення про успішне оновлення
        android.widget.Toast.makeText(
            this,
            R.string.document_updated_toast,
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }


    private fun updateMarqueeText() {
        val lastUpdateTime = sharedPreferences.getLong(KEY_LAST_UPDATE, System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("HH:mm | dd.MM.yyyy", Locale("uk"))
        val formattedDateTime = dateFormat.format(Date(lastUpdateTime))

        val baseText = "На обліку  •  Документ оновлено о $formattedDateTime     "
        val marqueeText = buildString {
            repeat(7) {
                append(baseText)
            }
        }

        tvMarquee.text = marqueeText
    }

    override fun onResume() {
        super.onResume()
        updateMarqueeText()
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomSheetDialog?.dismiss()
        updateDialog?.dismiss()
    }
}