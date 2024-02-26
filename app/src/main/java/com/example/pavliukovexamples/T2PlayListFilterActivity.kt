package com.example.pavliukovexamples

import android.R.layout.simple_spinner_item
import android.R.layout.simple_spinner_dropdown_item
import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.pavliukovexamples.databinding.T2ActivityPlayListFilterBinding


class T2PlayListFilterActivity : AppCompatActivity() {

    private lateinit var dbh: DBHelper
    private lateinit var binding: T2ActivityPlayListFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = T2ActivityPlayListFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbh = DBHelper(this)

        var artistSpinner = binding.t2SpinnerArtist
        var genreSpinner = binding.t2SpinnerGenre

        val artistAdapter = ArrayAdapter(this, simple_spinner_item, getArtistList())
        val genreAdapter = ArrayAdapter(this, simple_spinner_item, getGenreList())

        artistAdapter.setDropDownViewResource(simple_spinner_dropdown_item)
        genreAdapter.setDropDownViewResource(simple_spinner_dropdown_item)

        artistSpinner.adapter = artistAdapter
        genreSpinner.adapter = genreAdapter

        artistSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, index: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(index).toString()
                if (selectedItem != "Выберите исполнителя:")
                    goToMainActivity(Constants.PL_COLUMN_1, selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        genreSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                if (selectedItem != "Выберите жанр:")
                    goToMainActivity(Constants.PL_COLUMN_3, selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        val backButton = binding.backButton
        backButton.setOnClickListener() { goToMainActivity() }
    }

    private fun getArtistList(): ArrayList<String> {
        val cursor: Cursor? = dbh.queryArtist()
        val artistsList = arrayListOf("Выберите исполнителя:")
        while (cursor!!.moveToNext()) {
            val artistName = cursor.getString(0)
            artistsList.add(artistName)
        }
        cursor.close()
        return artistsList
    }

    private fun getGenreList(): ArrayList<String> {
        val cursor: Cursor? = dbh.queryGenre()
        val genresList = arrayListOf("Выберите жанр:")
        while (cursor!!.moveToNext()) {
            val artistName = cursor.getString(0)
            genresList.add(artistName)
        }
        cursor.close()
        return genresList
    }

    private fun goToMainActivity(type: String = "", key: String = "") {
        val resultIntent = Intent()
        resultIntent.putExtra("type", type)
        resultIntent.putExtra("key", key)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}

