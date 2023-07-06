package com.example.pavliukovandersen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class T2PlayListFilterActivity : AppCompatActivity() {

    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.t2_activity_play_list_filter)

        db = DBHelper(this)

    }
}
