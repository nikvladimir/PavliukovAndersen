package com.example.pavliukovexamples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.GravityCompat
import com.example.googlemaps.GoogleMapsFragment
import com.example.networknewsfragment.HostNewsListFragment
import com.example.pavliukovexamples.databinding.ActivityMainBinding
import com.example.servicemusicplayer.MusicPlayerFragment
import com.example.viewexamples.ViewPagerTvEtRvFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var nightModeSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = (supportFragmentManager).also {
            it.beginTransaction().replace(
                binding.displayedScreenFL.id, ViewPagerTvEtRvFragment()
            ).commitNow()
        }

        nightModeSwitch = binding.switch1
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        binding.apply {
            navigationMenu.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.VariousViews -> fragmentManager.beginTransaction().replace(
                        binding.displayedScreenFL.id, ViewPagerTvEtRvFragment()
                    ).commit()

                    R.id.MusicPlayer -> fragmentManager.beginTransaction().replace(
                        binding.displayedScreenFL.id, MusicPlayerFragment()
                    ).commit()

                    R.id.NewsList -> fragmentManager.beginTransaction().replace(
                        binding.displayedScreenFL.id, HostNewsListFragment()
                    ).commit()

                    R.id.GoogleMapsView -> fragmentManager.beginTransaction().replace(
                        binding.displayedScreenFL.id, GoogleMapsFragment()
                    ).commit()

                }
                activityMainDrawer.closeDrawer(GravityCompat.START)
                true
            }
        }
    }
}