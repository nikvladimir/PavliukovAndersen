package com.example.pavliukovandersen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentContainerText, FirstFragment())
        fragmentTransaction.replace(R.id.fragmentContainerEditText, SecondFragment())
        fragmentTransaction.commit()
    }
}