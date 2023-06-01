package com.example.pavliukovandersen

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder

class MusicService : Service() {

    private val binder = LocalBinder()
    private lateinit var player: MediaPlayer
    private var isPaused: Boolean = false

    inner class LocalBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onBind(intent: Intent): IBinder {
        player = MediaPlayer.create(this, R.raw.sonne)
        return binder
    }

    fun playMusic() {
        if (!player.isPlaying) {
            player.start()
            isPaused = false
        }
    }

    fun pauseMusic() {
        if (player.isPlaying) {
            player.pause()
            isPaused = true
        } else if (isPaused) {
            player.start()
            isPaused = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        player.release()
    }
}