package com.example.sawit.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sawit.R

class EditProfileActivity : AppCompatActivity() {

    private var currentEmail: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit Profil"

        val etName = findViewById<EditText>(R.id.et_edit_name)
        val etEmail = findViewById<EditText>(R.id.et_edit_email)
        val btnSave = findViewById<Button>(R.id.btn_save_profile)

        val initialName = intent.getStringExtra("EXTRA_INITIAL_NAME") ?: "Nama Pengguna"
        currentEmail = intent.getStringExtra("EXTRA_INITIAL_EMAIL") ?: "email@default.com"

        etName.setText(initialName)
        etEmail.setText(currentEmail)

        btnSave.setOnClickListener {
            val newName = etName.text.toString().trim()

            if (newName.isEmpty()) {
                Toast.makeText(this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Logika Penyimpanan Data (Hanya Nama yang diubah)

            Toast.makeText(this, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()

            val resultIntent = Intent()
            resultIntent.putExtra("EXTRA_NEW_NAME", newName)
            resultIntent.putExtra("EXTRA_NEW_EMAIL", currentEmail) // Mengirim email lama kembali

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}