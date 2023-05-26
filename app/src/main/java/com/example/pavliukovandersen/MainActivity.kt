package com.example.pavliukovandersen

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.RecyclerView
import com.example.pavliukovandersen.databinding.ActivityMainBinding
import kotlin.random.Random

val GEOMETRIC_SHAPES = listOf(
    "circle", "oval", "triangle", "square", "rectangle",
    "rhombus", "kite", "hexagon", "octagon", "arrow"
)

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

    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val messageForAlert = getRandomElementWithIndexFromList(GEOMETRIC_SHAPES)
        alertDialogBuilder.setTitle("Alert")
        alertDialogBuilder.setMessage(messageForAlert)

        alertDialogBuilder.setPositiveButton("OK") { _, _ -> }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun getRandomElementWithIndexFromList(list: List<String>): String {
        val randomIndex = Random.nextInt(0, list.size)
        val randomElement = list[randomIndex]
        return "$randomIndex ($randomElement)"
    }
}