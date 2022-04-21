package com.example.mapapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.mapapplication.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {
    private lateinit var binding:FragmentMapBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var mapView: MapView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
        mapView = binding.mapView.apply {
            onCreate(savedInstanceState)
            onResume()
        }

        mapView.getMapAsync {
            onMapReady(it)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val location = LatLng(0.0, 0.0)
        googleMap.addMarker(
            MarkerOptions()
            .position(location)
            .title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

}