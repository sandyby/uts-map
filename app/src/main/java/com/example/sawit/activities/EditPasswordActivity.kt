package com.example.sawit.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import com.example.sawit.ui.EditPasswordScreen
import com.example.sawit.ui.theme.SawItTheme

class EditPasswordActivity : ComponentActivity() {

    companion object {
        const val EXTRA_OLD_PASSWORD = "EXTRA_OLD_PASSWORD"
        const val EXTRA_NEW_PASSWORD = "EXTRA_NEW_PASSWORD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SawItTheme {
                EditPasswordScreen(
                    onBack = { finish() },
                    onPasswordChanged = { old, new ->
                        Toast.makeText(this, "Password successfully changed!", Toast.LENGTH_LONG)
                            .show()
                        val result = Intent().apply {
                            putExtra(EXTRA_OLD_PASSWORD, old)
                            putExtra(EXTRA_NEW_PASSWORD, new)
                        }
                        setResult(RESULT_OK, result)
                        finish()
                    }
                )
            }
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_edit_password)
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = "Ubah Kata Sandi"
//
//        val etOldPassword = findViewById<EditText>(R.id.et_old_password)
//        val etNewPassword = findViewById<EditText>(R.id.et_new_password)
//        val etConfirmPassword = findViewById<EditText>(R.id.et_confirm_password)
//        val btnChangePassword = findViewById<Button>(R.id.btn_change_password)
//
//        btnChangePassword.setOnClickListener {
//            val oldPass = etOldPassword.text.toString()
//            val newPass = etNewPassword.text.toString()
//            val confirmPass = etConfirmPassword.text.toString()
//
//            if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
//                Toast.makeText(this, "Semua kolom wajib diisi.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if (newPass != confirmPass) {
//                Toast.makeText(this, "Kata Sandi baru dan konfirmasi tidak cocok.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if (newPass.length < 6) {
//                Toast.makeText(this, "Kata Sandi minimal 6 karakter.", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            Toast.makeText(this, "Kata Sandi berhasil diubah!", Toast.LENGTH_LONG).show()
//            finish()
//        }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        finish()
//        return true
//    }
}