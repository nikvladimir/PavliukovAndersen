package com.example.pavliukovandersen

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class T5GoogleMapsFragment : Fragment() {

    private lateinit var mapView: MapView
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val mapReadyCallback = OnMapReadyCallback { googleMap ->

        checkLocationServicesEnabled()

        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            try {
                googleMap.isMyLocationEnabled = true

                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        location?.let {
                            val currentLocation = LatLng(it.latitude, it.longitude)
                            googleMap.addMarker(
                                MarkerOptions().position(currentLocation).title("You are here")
                            )
                            googleMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(currentLocation, 15f)
                            )
                        }
                    }
            } catch (e: SecurityException) {
                Log.d("ERROR", "$e")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.t5_fragment_google_maps, container, false)
        mapView = rootView.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(mapReadyCallback)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, reload the map or take other actions
            } else {
                // Permission was denied
            }
        }
    }

    private fun checkLocationServicesEnabled() {
        val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGpsEnabled && !isNetworkEnabled) {
            AlertDialog.Builder(requireContext())
                .setMessage("Location Services are disabled. Would you like to enable them?")
                .setPositiveButton("Yes") { _, _ ->
                    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(intent)
                }
                .setNegativeButton("No", null)
                .show()
        }
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}
