package com.example.pavliukovandersen

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
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class T2MusicPlayerFragment : Fragment() {

    private lateinit var dbh: DBHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var tableDataArray: ArrayList<T2DataPlayList>
    private lateinit var launcher: ActivityResultLauncher<Intent>

    private var sortByColumn: String = ""
    private var sortByKey: String = ""
    private var isBound = false
    private var musicService: T2MusicPlayerService? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    sortByColumn = data?.getStringExtra("type") ?: ""
                    sortByKey = data?.getStringExtra("key") ?: ""
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.t2_fragment_music_player, container, false)

        dbh = DBHelper(requireActivity().applicationContext)
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = T2PlayListAdapter(getArrayPlayListFromDB())

        val playButton = view.findViewById<Button>(R.id.play_button)
        val pauseButton = view.findViewById<Button>(R.id.pause_button)
        val stopButton = view.findViewById<Button>(R.id.stop_button)
        val filterButton = view.findViewById<ImageButton>(R.id.filterButton)

        playButton.setOnClickListener { if (isBound) musicService?.playMusic() }
        pauseButton.setOnClickListener { if (isBound) musicService?.pauseMusic() }
        stopButton.setOnClickListener {
            if (isBound) {
                requireActivity().unbindService(connection)
                isBound = false
            }
        }
        filterButton.setOnClickListener {
            val intent = Intent(requireActivity(), T2PlayListFilterActivity::class.java)
            launcher.launch(intent)
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        Intent(requireActivity(), T2MusicPlayerService::class.java).also { intent ->
            requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerView.adapter = T2PlayListAdapter(getArrayPlayListFromDB(sortByColumn, sortByKey))
    }

    private fun getArrayPlayListFromDB(column: String = "", value: String = ""):
            ArrayList<T2DataPlayList> {
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
            tableDataArray.add(T2DataPlayList(artistName, songName, genre))
        }
        cursor.close()
        return tableDataArray
    }


    override fun onStop() {
        super.onStop()
        // it stop music when app is hide
//        if (isBound) {
//            requireActivity().unbindService(connection)
//            isBound = false
//        }
    }
}