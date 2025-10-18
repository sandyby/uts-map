package com.example.sawitku.activities

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    private var isReadyToProceed = false
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        enableEdgeToEdge()

        splashScreen.setKeepOnScreenCondition { !isReadyToProceed }
        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            val splashView: View = splashScreenViewProvider.view
            splashView.animate().translationY(-splashView.height.toFloat() / 2)
                .alpha(0f)
                .setDuration(350L)
                .withEndAction {
                    splashScreenViewProvider.remove()
                }.start()
        }
        simAppInit()
    }

    private fun simAppInit() {
        lifecycleScope.launch {
            delay(1000L)
            isReadyToProceed = true
            startInitPage()
        }
    }

    private fun startInitPage() {
        startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
        finish()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}