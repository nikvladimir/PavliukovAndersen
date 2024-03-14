package com.example.servicemusicplayer

import android.app.Activity
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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servicemusicplayer.databinding.FragmentMusicPlayerBinding

class MusicPlayerFragment : Fragment() {

    private lateinit var dbh: DBHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var tableDataArray: ArrayList<DataPlayList>
    private lateinit var launcher: ActivityResultLauncher<Intent>

    private var isBound = false
    private var sortByKey: String = ""
    private var sortByColumn: String = ""
    private var musicService: MusicPlayerService? = null
    private lateinit var binding: FragmentMusicPlayerBinding

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as MusicPlayerService.LocalBinder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                sortByColumn = data?.getStringExtra("type") ?: ""
                sortByKey = data?.getStringExtra("key") ?: ""
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMusicPlayerBinding.inflate(layoutInflater)

        dbh = DBHelper(requireActivity().applicationContext)
        recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = PlayListAdapter(getArrayPlayListFromDB())

        val playButton = binding.playButton
        val pauseButton = binding.pauseButton
        val nextButton = binding.nextButton
        val stopButton = binding.stopButton
        val filterButton = binding.filterButton

        playButton.setOnClickListener {
            if (isBound) {
                musicService?.playPauseMusic()
            } else {
                bindMusicPlayerService()
                isBound = true
            }
        }
        pauseButton.setOnClickListener { if (isBound) musicService?.pauseMusic() }
        nextButton.setOnClickListener { if (isBound) musicService?.playNextTrack() }
        stopButton.setOnClickListener {
            if (isBound) {
                musicService?.stopMusic()
            }
//            if (isBound) requireActivity().unbindService(connection)
//            isBound = false
        }
        filterButton.setOnClickListener {
            launcher.launch(Intent(requireActivity(), PlayListFilterActivity::class.java))
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        bindMusicPlayerService()
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter = PlayListAdapter(getArrayPlayListFromDB(sortByColumn, sortByKey))
        bindMusicPlayerService(sortByColumn, sortByKey)
    }

    override fun onStop() {
        super.onStop()
    }

    private fun bindMusicPlayerService(sortByColumn: String = "", sortByKey: String = "") {
        Intent(requireActivity(), MusicPlayerService::class.java).also { intent ->
            intent.putExtra("sortByColumn", sortByColumn)
            intent.putExtra("sortByKey", sortByKey)
            requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun getArrayPlayListFromDB(column: String = "", value: String = ""):
            ArrayList<DataPlayList> {
        val cursor: Cursor? = if (column.isEmpty() && value.isEmpty()) {
            dbh.queryAllPlaylistTable()
        } else {
            dbh.queryPlaylistTableByColumnAndValue(column, value)
        }
        tableDataArray = ArrayList()
        while (cursor!!.moveToNext()) {
            val artistName = cursor.getString(0)
            val songName = cursor.getString(1)
            val genre = cursor.getString(2)
            val fileName = cursor.getString(3)
            tableDataArray.add(DataPlayList(artistName, songName, genre, fileName))
        }
        cursor.close()
        return tableDataArray
    }
}