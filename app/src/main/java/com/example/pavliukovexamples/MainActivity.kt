package com.example.pavliukovexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import com.example.pavliukovexamples.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var nightModeSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.displayed_screen_fl, T1ViewPagerTvEtRvFragment())
            .commit()

        nightModeSwitch = binding.switch1
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.apply {
            navigationMenu.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.T1_VariousViews -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, T1ViewPagerTvEtRvFragment()
                    ).commit()

                    R.id.T2_MusicPlayer -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, T2MusicPlayerFragment()
                    ).commit()

                    R.id.T3_NewsList -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, T3NewsListFragment()
                    ).commit()

                    R.id.T5_GoogleMapsView -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, T5GoogleMapsFragment()
                    ).commit()

                }
                activityMainDrawer.closeDrawer(GravityCompat.START)
                true
            }
        }
    }
}