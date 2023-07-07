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

    private lateinit var player: MediaPlayer
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var trackArtist: String
    private lateinit var trackName: String
    private lateinit var trackFileName: String

    private val binder = LocalBinder()
    private var isPaused: Boolean = true

    inner class LocalBinder : Binder() {
        fun getService(): T2MusicPlayerService = this@T2MusicPlayerService
    }

    override fun onBind(intent: Intent): IBinder {
        trackArtist = intent.getStringExtra("trackArtist") ?: "Unknown"
        trackName = intent.getStringExtra("trackName") ?: "Unknown"
        trackFileName = intent.getStringExtra("trackFileName") ?: "eminem_lose_yourself"

        initMediaPlayer()

        return binder
    }

    fun playMusic() {
        if (isPaused) {
            player.start()
            isPaused = false
            startForegroundService()
        } else {
            player.pause()
            isPaused = true
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

    fun playNextTrack(trackData: T2DataPlayList) {
        trackArtist = trackData.artist
        trackName = trackData.trackName
        trackFileName = trackData.trackFileName

        player.release()
        initMediaPlayer()
        player.start()
    }

    private fun initMediaPlayer() {
        val uriMusicFile = Uri.parse("android.resource://$packageName/raw/$trackFileName")
        player = MediaPlayer.create(this, uriMusicFile)
        sharedPreferences = this.getSharedPreferences("MusicPlayerPref", MODE_PRIVATE)
        val position = sharedPreferences.getInt("position", 0)
        player.seekTo(position)
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
            .setContentTitle(trackArtist)
            .setContentText(trackName)
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