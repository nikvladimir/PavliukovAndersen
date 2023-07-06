package com.example.pavliukovandersen

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder


class T2MusicPlayerService : Service() {

    private val binder = LocalBinder()
    private lateinit var player: MediaPlayer
    private var isPaused: Boolean = false
    private lateinit var sharedPreferences: SharedPreferences

    inner class LocalBinder : Binder() {
        fun getService(): T2MusicPlayerService = this@T2MusicPlayerService
    }

    override fun onBind(intent: Intent): IBinder {
        val musicFileName = "eminem_lose_yourself"
        val uriMusicFile = Uri.parse("android.resource://$packageName/raw/$musicFileName")
        player = MediaPlayer.create(this, uriMusicFile)
        sharedPreferences = this.getSharedPreferences("MusicPlayerPref", MODE_PRIVATE)
        val position = sharedPreferences.getInt("position", 0)
        player.seekTo(position)
        return binder
    }

    fun playMusic() {
        if (!player.isPlaying) {
            player.start()
            isPaused = false
            startForegroundService()
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
        val editor = sharedPreferences.edit()
        editor.apply {
            putInt("position", player.currentPosition)
            apply()
        }
        player.apply {
            stop()
            release()
        }
    }

    private fun startForegroundService() {
        createNotificationChannel()

        val notification = Notification.Builder(this, "MusicServiceChannel")
            .setContentTitle("Artist")
            .setContentText("Composition")
            .setSmallIcon(R.drawable.arrow)
            .build()

        startForeground(1, notification)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "MusicServiceChannel", "Music Service", NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}