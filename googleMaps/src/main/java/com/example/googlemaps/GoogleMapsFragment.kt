package com.example.googlemaps

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
import com.example.googlemaps.databinding.T5FragmentGoogleMapsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class GoogleMapsFragment : Fragment() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: T5FragmentGoogleMapsBinding

    private val mapReadyCallback = OnMapReadyCallback { googleMap ->

        checkLocationServicesEnabled()

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
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

                            val cLLat = currentLocation.latitude
                            val cLLong = currentLocation.longitude
                            val radiusInDegrees = 10 / 111.32


                            val marker1 =
                                LatLng(cLLat + radiusInDegrees / 2, cLLong + radiusInDegrees / 2)
                            val marker2 =
                                LatLng(cLLat - radiusInDegrees / 2, cLLong + radiusInDegrees / 2)
                            val marker3 =
                                LatLng(cLLat - radiusInDegrees / 2, cLLong - radiusInDegrees / 2)
                            val marker4 =
                                LatLng(cLLat + radiusInDegrees / 2, cLLong - radiusInDegrees / 2)
                            val marker5 =
                                LatLng(cLLat + radiusInDegrees / 4, cLLong - radiusInDegrees / 4)

                            googleMap.addMarker(
                                MarkerOptions().position(currentLocation).title("You are here")
                            )
                            googleMap.addMarker(MarkerOptions().position(marker1).title("Marker 1"))
                            googleMap.addMarker(MarkerOptions().position(marker2).title("Marker 2"))
                            googleMap.addMarker(MarkerOptions().position(marker3).title("Marker 3"))
                            googleMap.addMarker(MarkerOptions().position(marker4).title("Marker 4"))
                            googleMap.addMarker(MarkerOptions().position(marker5).title("Marker 5"))

                            googleMap.animateCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    currentLocation,
                                    12f
                                )
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
    ): View {
        binding = T5FragmentGoogleMapsBinding.inflate(layoutInflater)
        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync(mapReadyCallback)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        binding.mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        binding.mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
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
