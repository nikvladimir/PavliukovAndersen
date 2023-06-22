package com.example.pavliukovandersen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class T2ViewPlayListFilter : AppCompatActivity() {

    private lateinit var name: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var save: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.t2_play_list_filter_view)

        name = findViewById(R.id.textedit1)
        phone = findViewById(R.id.textedit2)
        save = findViewById(R.id.button)
        db = DBHelper(this)
        
    }
}