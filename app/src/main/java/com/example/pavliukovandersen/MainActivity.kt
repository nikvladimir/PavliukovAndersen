package com.example.pavliukovandersen

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.random.Random

val GEOMETRIC_SHAPES = listOf(
    "circle", "oval", "triangle", "square", "rectangle",
    "rhombus", "kite", "hexagon", "octagon", "semicircle"
)

class MainActivity : AppCompatActivity() {

    private lateinit var alertButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentContainerText, TextViewFragment())
        fragmentTransaction.replace(R.id.fragmentContainerEditText, EditTextFragment())
        fragmentTransaction.commit()

        alertButton = findViewById(R.id.alertButton)
        alertButton.setOnClickListener { showAlertDialog() }
    }

    private fun showAlertDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        val messageForAlert = getRandomElementWithIndexFromList(GEOMETRIC_SHAPES)
        alertDialogBuilder.setTitle("Alert")
        alertDialogBuilder.setMessage(messageForAlert)

        alertDialogBuilder.setPositiveButton("OK") { dialog, which ->
            // Positive button clicked
            // Perform any action here
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun getRandomElementWithIndexFromList(list: List<String>): String {
        val randomIndex = Random.nextInt(0, list.size)
        val randomElement = list[randomIndex]
        return "$randomIndex ($randomElement)"
    }
}