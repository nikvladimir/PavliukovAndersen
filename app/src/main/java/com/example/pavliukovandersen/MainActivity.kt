package com.example.pavliukovandersen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import com.example.pavliukovandersen.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var nightModeSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.displayed_screen_fl, T2MusicPlayer())
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
                    R.id.Lesson1 -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, T1ViewPagerTvEtRv()
                    ).commit()

                    R.id.Lesson2 -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, T2MusicPlayer()
                    ).commit()

                    R.id.Lesson3 -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, ViewPagerTask3()
                    ).commit()
                }
                activityMainDrawer.closeDrawer(GravityCompat.START)
                true
            }
        }
    }
}