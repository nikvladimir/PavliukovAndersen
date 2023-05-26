package com.example.pavliukovandersen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.GravityCompat
import com.example.pavliukovandersen.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.displayed_screen_fl, ViewPagerLesson1())
            .commit()

        binding.apply {
            navigationMenu.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.Lesson1 -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, ViewPagerLesson1()
                    ).commit()

                    R.id.Lesson2 -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, ViewPagerLesson2()
                    ).commit()

                    R.id.Lesson3 -> fragmentManager.beginTransaction().replace(
                        R.id.displayed_screen_fl, ViewPagerLesson3()
                    ).commit()
                }
                activityMainDrawer.closeDrawer(GravityCompat.START)
                true
            }
        }
    }
}