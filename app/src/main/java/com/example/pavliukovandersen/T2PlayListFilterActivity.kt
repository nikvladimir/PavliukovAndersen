package com.example.pavliukovandersen

import android.R.layout.simple_spinner_item
import android.R.layout.simple_spinner_dropdown_item
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pavliukovandersen.databinding.T2ActivityPlayListFilterBinding


class T2PlayListFilterActivity : AppCompatActivity() {

    private lateinit var dbh: DBHelper
    private lateinit var artistSpinner: Spinner
    private lateinit var genreSpinner: Spinner
    private lateinit var binding: T2ActivityPlayListFilterBinding
    private lateinit var artistArray: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = T2ActivityPlayListFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbh = DBHelper(this)

        artistSpinner = binding.spinnerArtist
        genreSpinner = binding.spinnerGenre

        val artistAdapter = ArrayAdapter(this, simple_spinner_item, getArtistList())
        val genreAdapter = ArrayAdapter(this, simple_spinner_item, getGenreList())

        artistAdapter.setDropDownViewResource(simple_spinner_dropdown_item)
        genreAdapter.setDropDownViewResource(simple_spinner_dropdown_item)

        artistSpinner.adapter = artistAdapter
        genreSpinner.adapter = genreAdapter

        artistSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext, selectedItem, Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun getArtistList(): ArrayList<String> {
        val cursor: Cursor? = dbh.queryArtist()
        artistArray = ArrayList()
        while (cursor!!.moveToNext()) {
            val artistName = cursor.getString(0)
            artistArray.add(artistName)
        }
        cursor.close()
        return artistArray
    }

    private fun getGenreList(): ArrayList<String> {
        val cursor: Cursor? = dbh.queryGenre()
        artistArray = ArrayList()
        while (cursor!!.moveToNext()) {
            val artistName = cursor.getString(0)
            artistArray.add(artistName)
        }
        cursor.close()
        return artistArray
    }
}

