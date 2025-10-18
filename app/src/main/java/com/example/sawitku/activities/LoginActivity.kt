package com.example.sawitku.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sawitku.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import java.security.MessageDigest
import kotlin.apply

class LoginActivity : AppCompatActivity() {
    private lateinit var tietEmail: TextInputEditText
    private lateinit var tietPassword: TextInputEditText

    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var mBtnLogin: MaterialButton

    private var isEmailValid = false
    private var isPasswordValid = false
    private lateinit var tvLoginErrorMsg: TextView
    private lateinit var tvSwitchRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedPrefs = getSharedPreferences("UserSession", MODE_PRIVATE)
        val email = sharedPrefs.getString("email", null)

        if (email != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(R.layout.activity_login)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tilEmail = findViewById(R.id.til_email_field)
        tietEmail = findViewById(R.id.tiet_email_field)

        tilPassword = findViewById(R.id.til_password_field)
        tietPassword = findViewById(R.id.tiet_password_field)

        mBtnLogin = findViewById<MaterialButton>(R.id.mBtn_login)

        tietEmail.addTextChangedListener(EmailWatcher())
        tietPassword.addTextChangedListener(PasswordWatcher())

        tvLoginErrorMsg = findViewById<TextView>(R.id.tv_login_error_msg)

        tvSwitchRegister = findViewById<TextView>(R.id.tv_switchRegister)
        tvSwitchRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        mBtnLogin.setOnClickListener {
            tvLoginErrorMsg.visibility = View.GONE
            val email = tietEmail.text.toString().trim()
            val password = tietPassword.text.toString().trim()
            Log.d("LoginActivity", "$email $password")
            userLogin(email, password)
        }
    }

    private inner class EmailWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            val email = tietEmail.text.toString().trim()
            isEmailValid = when {
                email.isEmpty() -> {
                    tilEmail.error = "E-mail is required"
                    false
                }

                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    tilEmail.error = "Invalid email format"
                    false
                }

                else -> {
                    tilEmail.error = null
                    true
                }
            }
            updateLoginButtonState()
        }
    }

    private inner class PasswordWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            val password = tietPassword.text.toString().trim()
            isPasswordValid = when {
                password.isEmpty() -> {
                    tilPassword.error = "Password is required"
                    false
                }
//                password.length < 8 -> {
//                    tilPassword.error = "Password must be at least 8 characters"
//                    false
//                }
                else -> {
                    tilPassword.error = null
                    true
                }
            }
            updateLoginButtonState()
        }
    }

    private fun updateLoginButtonState() {
        mBtnLogin.isEnabled =
            isEmailValid && isPasswordValid
    }

    private fun hashSHA256String(input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(input.toByteArray())
        return buildString {
            bytes.forEach {
                val i = it.toInt()
                append(HEX_CHARS[i shr 4 and 0x0f])
                append(HEX_CHARS[i and 0x0f])
            }
        }
    }

    private fun userLogin(email: String, password: String) {
        mBtnLogin.isEnabled = false
        val hashedPassword = hashSHA256String(password)

        FirebaseDatabase.getInstance("https://sawit-6876f-default-rtdb.asia-southeast1.firebasedatabase.app/")
            .getReference("users")
            .orderByChild("email")
            .equalTo(email)
            .get()
            .addOnSuccessListener { snapshot ->
                Log.d("LoginActivity", "snapshot: $snapshot")
                if (snapshot.exists()) {

                    snapshot.children.forEach { data ->
                        val storedPassword = data.child("password").getValue(String::class.java)

                        if (storedPassword == hashedPassword) {
                            val sharedPrefs = getSharedPreferences("UserSession", MODE_PRIVATE)
                            sharedPrefs.edit().apply {
                                putString("email", email)
                                putString("uid", data.key)
                                apply()
                            }

                            Log.d("LoginActivity", "tes1")
                            Toast.makeText(this, "Successfully logged in!", Toast.LENGTH_SHORT)
                                .show()

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            Log.d("LoginActivity", "tes2")
                            finish()
                            return@addOnSuccessListener
                        }
                    }
                    Log.d("LoginActivity", "tes3")
                    tvLoginErrorMsg.visibility = View.VISIBLE
                    mBtnLogin.isEnabled = true
                } else {
                    Log.d("LoginActivity", "tes4")
                    tvLoginErrorMsg.visibility = View.VISIBLE
                    mBtnLogin.isEnabled = true
                }
            }
            .addOnFailureListener {
                Log.d("LoginActivity", "Error")
                mBtnLogin.isEnabled = true
            }
    }
}