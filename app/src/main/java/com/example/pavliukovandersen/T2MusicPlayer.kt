package com.example.pavliukovandersen

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.SharedPreferences
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class T2MusicPlayer : Fragment() {

    private var musicService: MusicService? = null
    private var isBound = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MusicService.LocalBinder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task2_music_player, container, false)

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

    override fun onStart() {
        super.onStart()
        Intent(requireActivity(), MusicService::class.java).also { intent ->
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