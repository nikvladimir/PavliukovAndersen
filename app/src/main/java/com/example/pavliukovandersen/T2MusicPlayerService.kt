package com.example.pavliukovandersen

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder


class T2MusicPlayerService : Service() {

    private lateinit var dbh: DBHelper
    private lateinit var trackName: String
    private lateinit var player: MediaPlayer
    private lateinit var trackArtist: String
    private lateinit var trackFileName: String
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var tracks: ArrayList<T2DataPlayList>

    private var currentTrackIndex = 0
    private val binder = LocalBinder()
    private var sortByKey: String = ""
    private var sortByColumn: String = ""

    inner class LocalBinder : Binder() {
        fun getService(): T2MusicPlayerService = this@T2MusicPlayerService
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        sortByColumn = intent.getStringExtra("sortByColumn") ?: ""
        sortByKey = intent.getStringExtra("sortByKey") ?: ""
        trackFileName = intent.getStringExtra("trackFileName") ?: ""

        initMediaPlayer(sortByColumn, sortByKey)
        return binder
    }

    private fun initMediaPlayer(sortByColumn: String = "", sortByKey: String = "") {
        initializeStacksFromDB(sortByColumn, sortByKey)

        trackArtist = tracks[currentTrackIndex].artist
        trackName = tracks[currentTrackIndex].trackName
        trackFileName = tracks[currentTrackIndex].trackFileName

        val uriMusicFile = Uri.parse("android.resource://$packageName/raw/$trackFileName")
        player = MediaPlayer.create(this, uriMusicFile)
        sharedPreferences = this.getSharedPreferences("MusicPlayerPref", MODE_PRIVATE)
        val position = sharedPreferences.getInt("position", 0)
        player.seekTo(position)
    }

    fun playPauseMusic() {
        if (player.isPlaying) {
            player.pause()
        } else {
            player.start()
            startForegroundService()
        }
    }

    fun pauseMusic() {
        if (player.isPlaying) {
            player.pause()
        }
    }

    fun stopMusic() {
        player.stop()
        player.release()
        currentTrackIndex = 0
        initMediaPlayer()
    }

    fun playNextTrack() {
        player.release()
        currentTrackIndex = (currentTrackIndex + 1) % tracks.size
        initMediaPlayer()
        player.start()
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

    private fun initializeStacksFromDB(column: String = "", value: String = ""):
            ArrayList<T2DataPlayList> {
        dbh = DBHelper(this)
        val cursor: Cursor? = if (column.isEmpty() && value.isEmpty()) {
            dbh.queryAllPlaylistTable()
        } else {
            dbh.queryPlaylistTableByColumnAndValue(column, value)
        }
        tracks = ArrayList()
        while (cursor!!.moveToNext()) {
            val artistName = cursor.getString(0)
            val songName = cursor.getString(1)
            val genre = cursor.getString(2)
            val fileName = cursor.getString(3)
            tracks.add(T2DataPlayList(artistName, songName, genre, fileName))
        }
        cursor.close()
        return tracks
    }
}