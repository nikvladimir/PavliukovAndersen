package com.example.pavliukovandersen

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.database.Cursor
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class T2MusicPlayerFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: ImageButton
    lateinit var dbh: DBHelper
    private lateinit var newArray: ArrayList<T2DataPlayList>

    private var musicService: T2MusicPlayerService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as T2MusicPlayerService.LocalBinder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onResume() {
        super.onResume()
        displayUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task2_music_player, container, false)

        recyclerView = view.findViewById(R.id.recycler)
        button = view.findViewById(R.id.filterButton)

        button.setOnClickListener {
            val intent = Intent(requireActivity(), T2PlayListFilterActivity::class.java)
            startActivity(intent)
        }

        dbh = DBHelper(requireActivity().applicationContext)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        displayUser()

        val playButton = view.findViewById<Button>(R.id.play_button)
        val pauseButton = view.findViewById<Button>(R.id.pause_button)
        val stopButton = view.findViewById<Button>(R.id.stop_button)

        playButton.setOnClickListener {
            if (isBound) {
                musicService?.playMusic()
            }
        }

        pauseButton.setOnClickListener {
            if (isBound) {
                musicService?.pauseMusic()
            }
        }
        stopButton.setOnClickListener {
            if (isBound) {
                requireActivity().unbindService(connection)
                isBound = false
            }
        }
        return view
    }

    private fun displayUser() {
        var newCursor: Cursor? = dbh.gettext()
        newArray = ArrayList()
        while (newCursor!!.moveToNext()) {
            val artistName = newCursor.getString(0)
            val songName = newCursor.getString(1)
            val genre = newCursor.getString(2)
            val fileName = newCursor.getString(3)
            newArray.add(T2DataPlayList(artistName, songName, genre))
        }
        recyclerView.adapter = T2PlayListAdapter(newArray)
    }

    override fun onStart() {
        super.onStart()
        Intent(requireActivity(), T2MusicPlayerService::class.java).also { intent ->
            requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
//        if (isBound) {
//            requireActivity().unbindService(connection)
//            isBound = false
//        }
    }
}