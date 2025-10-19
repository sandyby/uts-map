package com.example.sawit.activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.sawit.R
import com.example.sawit.fragments.FieldsFragment
import com.example.sawit.fragments.HomeFragment
import com.example.sawit.fragments.PredictionsFragment
import com.example.sawit.fragments.ProfileFragment
import com.example.sawit.fragments.TopHeaderFragment
import com.google.android.material.progressindicator.CircularProgressIndicator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {
    private lateinit var bottomBar: AnimatedBottomBar
    private lateinit var topHeaderFragment: TopHeaderFragment
    private lateinit var homeFragment: HomeFragment
    private lateinit var fieldsFragment: FieldsFragment
    private lateinit var activitiesFragmment: ActivitiesFragment
    private lateinit var predictionsFragment: PredictionsFragment
    private lateinit var profileFragment: ProfileFragment

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

        topHeaderFragment = TopHeaderFragment()
        homeFragment = HomeFragment()
        fieldsFragment = FieldsFragment()
        activitiesFragmment = ActivitiesFragment()
        predictionsFragment = PredictionsFragment()
        profileFragment = ProfileFragment()

        show(homeFragment)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
        lifecycleScope.launch {
            showLoading(true)
            supportFragmentManager.beginTransaction().replace(R.id.fl_top_header, topHeaderFragment)
                .commit()

            delay(2500)

            supportFragmentManager.executePendingTransactions()
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            showLoading(false)
        }
        topHeaderFragment.setTopHeaderFragmentTitle("Dashboard")

        AnimatedBottomBar.Badge(
            text = "99",
            backgroundColor = Color.RED,
            textColor = Color.GREEN,
            textSize = 12 // in pixels
        )

        bottomBar = findViewById(R.id.abb_bottom_bar)

//        bottomBar.setBadgeAtTabIndex(
//            1,
//            AnimatedBottomBar.Badge(
//                text = "99",
//                backgroundColor = Color.RED,
//                textColor = Color.WHITE,
//                textSize = 12
//            )
//        )

//        bottomBar.setOnTabSelectListener { _, newTab, _ ->
//            when (newTab.id)
//        }

        bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener {
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {
                when (newTab.id) {
                    R.id.tab_home -> {
                        show(homeFragment)
                        topHeaderFragment.setTopHeaderFragmentTitle("Dashboard")
                    }

                    R.id.tab_fields -> {
                        show(fieldsFragment)
                        topHeaderFragment.setTopHeaderFragmentTitle("Fields")
                    }

                    R.id.tab_activity -> {
                        show(activitiesFragmment)
                        topHeaderFragment.setTopHeaderFragmentTitle("Activities")
                    }

                    R.id.tab_predict -> {
                        show(predictionsFragment)
                        topHeaderFragment.setTopHeaderFragmentTitle("Predictions")
                    }

                    R.id.tab_profile -> {
                        show(profileFragment)
                        topHeaderFragment.setTopHeaderFragmentTitle("Profile")
                    }
                }
            }

            override fun onTabReselected(index: Int, tab: AnimatedBottomBar.Tab) {
                //
            }
        })

    }

    private fun showLoading(show: Boolean) {
        val overlay = findViewById<View>(R.id.cpi_loading_overlay)
        val progress = findViewById<CircularProgressIndicator>(R.id.cpi_loading_indicator)

        overlay.visibility = if (show) View.VISIBLE else View.GONE
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }


    private fun show(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_in_left)
        transaction.replace(R.id.fl_scroll_view_content, fragment).commit()
    }
}