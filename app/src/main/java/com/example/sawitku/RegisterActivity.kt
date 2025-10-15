package com.example.sawitku

import android.content.Intent
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.util.Patterns
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.sawitku.LoginActivity
import com.example.sawitku.models.User
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegisterActivity : AppCompatActivity() {
    private lateinit var tietFullName: TextInputEditText
    private lateinit var tietEmail: TextInputEditText
    private lateinit var tietPassword: TextInputEditText
    private lateinit var tietConfirmPassword: TextInputEditText

    private lateinit var tilFullName: TextInputLayout
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPassword: TextInputLayout
    private lateinit var tilConfirmPassword: TextInputLayout
    private lateinit var mBtnRegister: MaterialButton

    private lateinit var database: DatabaseReference
    private var isButtonEnabled: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tilFullName = findViewById(R.id.til_fullname_field)
        tietFullName = findViewById(R.id.tiet_fullname_field)

        tilEmail = findViewById(R.id.til_email_field)
        tietEmail = findViewById(R.id.tiet_email_field)

        tilPassword = findViewById(R.id.til_password_field)
        tietPassword = findViewById(R.id.tiet_password_field)

        tilConfirmPassword = findViewById(R.id.til_confirm_password_field)
        tietConfirmPassword = findViewById(R.id.tiet_confirm_password_field)

        mBtnRegister = findViewById<MaterialButton>(R.id.mBtn_register)

        val textInputs = listOf(tietFullName, tietEmail, tietPassword, tietConfirmPassword)
        textInputs.forEach { input ->
            input.addTextChangedListener {
                mBtnRegister.isEnabled = allInputValid()
                isButtonEnabled = allInputValid()
            }
        }

//        database = FirebaseDatabase.getInstance().reference
        database =
            FirebaseDatabase.getInstance("https://sawit-6876f-default-rtdb.asia-southeast1.firebasedatabase.app/").reference

        val tvSwitchLogin = findViewById<TextView>(R.id.tv_switchLogin)
        tvSwitchLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        mBtnRegister.setOnClickListener {
            if (validateFields()) {
                val fullName = tietFullName.text.toString().trim()
                val email = tietEmail.text.toString().trim()
                val password = tietPassword.text.toString().trim()

                checkIfUserExistsAndRegister(fullName, email, password)
//                registerUser(fullName, email, password)
                if (!isButtonEnabled) mBtnRegister.isEnabled = false
            }
        }
    }

    private fun allInputValid(): Boolean {
        val fullName = tietFullName.text.toString().trim()
        val email = tietEmail.text.toString().trim()
        val password = tietPassword.text.toString().trim()
        val confirmPassword = tietConfirmPassword.text.toString().trim()

        return fullName.isNotEmpty()
                && email.isNotEmpty()
                && Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && password.isNotEmpty()
                && password.length >= 6
                && confirmPassword == password
    }

    private fun checkIfUserExistsAndRegister(fullName: String, email: String, password: String) {
        val usersRef = database.child("users")

        usersRef.orderByChild("email").equalTo(email)
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    tilEmail.isErrorEnabled = true
                    tilEmail.error = "Email already registered"
                    Toast.makeText(this, "This email is already registered.", Toast.LENGTH_LONG)
                        .show()
                    mBtnRegister.isEnabled = false
                    isButtonEnabled = false
                } else {
                    registerUser(fullName, email, password)
                }
            }
            .addOnFailureListener { error ->
                Log.d("RegisterActivity", error.message.toString())
                Toast.makeText(
                    this,
                    "Something went wrong, please try again later!",
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        val fullName = tietFullName.text.toString().trim()
        val email = tietEmail.text.toString().trim()
        val password = tietPassword.text.toString().trim()
        val confirmPassword = tietConfirmPassword.text.toString().trim()

        listOf(tilFullName, tilEmail, tilPassword, tilConfirmPassword).forEach {
            it.isErrorEnabled = false
            it.error = null
        }

        if (fullName.isEmpty()) {
            tilFullName.isErrorEnabled = true
            tilFullName.error = "Full name required"
            isValid = false
        }

        if (email.isEmpty()) {
            tilEmail.isErrorEnabled = true
            tilEmail.error = "E-mail required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.isErrorEnabled = true
            tilEmail.error = "Invalid e-mail address"
            isValid = false
        }

        if (password.isEmpty()) {
            tilPassword.isErrorEnabled = true
            tilPassword.error = "Password required"
            isValid = false
        } else if (password.length < 6) {
            tilPassword.isErrorEnabled = true
            tilPassword.error = "At least 6 characters"
            isValid = false
        }

        if (confirmPassword != password) {
            tilConfirmPassword.isErrorEnabled = true
            tilConfirmPassword.error = "Passwords don’t match"
            isValid = false
        }
        return isValid
    }

    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
            .getInstance(type)
            .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }

    private fun registerUser(fullName: String, email: String, password: String) {
        val uid = database.child("users").push().key ?: return
        val currentTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()).format(
            Date()
        )

        val hashedPassword = hashString("SHA-256", password)
        val user = User(fullName, email, hashedPassword, currentTime)

        val  mBtnRegister = findViewById<MaterialButton>(R.id.mBtn_register)
        mBtnRegister.isEnabled = false
        CoroutineScope(Dispatchers.IO).launch {
            database.child("users").child(uid).setValue(user)
                .addOnSuccessListener {
                    runOnUiThread {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registration Successful!",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    }
                }
                .addOnFailureListener {
                    runOnUiThread {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Error: ${it.message}",
                            Toast.LENGTH_LONG
                        ).show()
                        mBtnRegister.isEnabled = true
                    }
                }
        }
    }
}