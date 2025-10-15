package com.example.sawitku

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

//    val tvRegister = findViewById<TextView>(R.id.tv)
//    val text = "Don’t have an account? Register here"
//    val spannable = SpannableString(text)
//
//    // Define the part you want clickable
//    val startIndex = text.indexOf("Register here")
//    val endIndex = startIndex + "Register here".length
//
//    // Make it clickable
//    val clickableSpan = object : ClickableSpan() {
//        override fun onClick(widget: View) {
//            // TODO: Handle the click (e.g., open registration screen)
//        }
//
//        override fun updateDrawState(ds: TextPaint) {
//            super.updateDrawState(ds)
//            ds.isUnderlineText = false // remove underline
//            ds.color = ContextCompat.getColor(widget.context, R.color.bg_primary_500) // custom color
//            ds.typeface = ResourcesCompat.getFont(widget.context, R.font.lato_bold) // make bold
//        }
//    }

//    spannable.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//    tvRegister.text = spannable
//    tvRegister.movementMethod = LinkMovementMethod.getInstance() // enable clicks
//    tvRegister.highlightColor = Color.TRANSPARENT // remove highlight on click

}