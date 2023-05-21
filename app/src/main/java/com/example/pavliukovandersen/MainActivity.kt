package com.example.pavliukovandersen

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pavliukovandersen.databinding.ActivityMainBinding
import kotlin.random.Random

val GEOMETRIC_SHAPES = listOf(
    "circle", "oval", "triangle", "square", "rectangle",
    "rhombus", "kite", "hexagon", "octagon", "semicircle"
)

class MainActivity : AppCompatActivity() {

    private lateinit var alertButton: Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ShapesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

//        fragmentTransaction.replace(R.id.fragmentContainerText, TextViewFragment())
//        fragmentTransaction.replace(R.id.fragmentContainerEditText, EditTextFragment())
//        fragmentTransaction.commit()
//
//        alertButton = findViewById(R.id.alertButton)
//        alertButton.setOnClickListener { showAlertDialog() }
//
//        recyclerView = findViewById(R.id.recyclerView)
//        adapter = ShapesAdapter(GEOMETRIC_SHAPES)
//
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
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