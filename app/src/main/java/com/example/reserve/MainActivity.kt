package com.example.reserve

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLogo()
        setupMarquee()
        setupMenuButton()
    }

    private fun setupLogo() {
        val tvLogo = findViewById<TextView>(R.id.tvLogo)
        val tvLogoPlus = findViewById<TextView>(R.id.tvLogoPlus)

        tvLogo.text = "Резерв"
        tvLogoPlus.text = "+"
    }

    private fun setupMarquee() {
        val tvMarquee = findViewById<TextView>(R.id.tvMarquee)
        tvMarquee.isSelected = true
    }

    private fun setupMenuButton() {
        val btnMenu = findViewById<ImageView>(R.id.btnMenu)
        btnMenu.setOnClickListener {
            showBottomSheetMenu()
        }
    }

    private fun showBottomSheetMenu() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.bottom_sheet_menu, null)

        view.findViewById<android.view.View>(R.id.btnViewDocument).setOnClickListener {
            bottomSheetDialog.dismiss()
            // TODO: Добавить функционал
        }

        view.findViewById<android.view.View>(R.id.btnDownloadPDF).setOnClickListener {
            bottomSheetDialog.dismiss()
            // TODO: Добавить функционал
        }

        view.findViewById<android.view.View>(R.id.btnRefreshDocument).setOnClickListener {
            // Оновити документ
            bottomSheetDialog.dismiss()
            // TODO: Добавить функционал
        }

        view.findViewById<android.view.View>(R.id.btnExtendedData).setOnClickListener {
            // Розширені дані з реєстру
            bottomSheetDialog.dismiss()
            // TODO: Добавить функционал
        }

        view.findViewById<android.view.View>(R.id.btnRequestDeferment).setOnClickListener {
            // Подати запит на відстрочку
            bottomSheetDialog.dismiss()
            // TODO: Добавить функционал
        }

        view.findViewById<android.view.View>(R.id.btnMedicalDirection).setOnClickListener {
            // Направлення на ВЛК
            bottomSheetDialog.dismiss()
            // TODO: Добавить функционал
        }

        view.findViewById<android.view.View>(R.id.btnUpdateContacts).setOnClickListener {
            // Уточнити контактні дані
            bottomSheetDialog.dismiss()
            // TODO: Добавить функционал
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}