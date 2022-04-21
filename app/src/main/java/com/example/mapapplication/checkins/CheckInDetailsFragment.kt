package com.example.mapapplication.checkins

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mapapplication.R
import com.example.mapapplication.Repositories
import com.example.mapapplication.databinding.FragmentCheckInDetailsBinding
import com.example.mapapplication.utils.viewModelCreator
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CheckInDetailsFragment : Fragment(R.layout.fragment_check_in_details), OnMapReadyCallback {

    private lateinit var binding: FragmentCheckInDetailsBinding
    private lateinit var googleMap:GoogleMap
    private lateinit var location:LatLng

    private val viewModel: CheckInListViewModel by viewModelCreator {
        CheckInListViewModel(Repositories.checkInsRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckInDetailsBinding.bind(view)
        val bundle = arguments ?: return
        val checkInId = CheckInDetailsFragmentArgs.fromBundle(bundle).id
        binding.locationTextView.text = checkInId.toString()
        val checkIn = viewModel.getCheckInById(checkInId)
        binding.dateTextView.text = checkIn.date
        binding.locationTextView.text = checkIn.locate
        location = checkIn.location
        if (checkIn.description != "")
            binding.descriptionTextView.text = checkIn.description
        val mapView = binding.mapView.apply {
            onCreate(savedInstanceState)
            onResume()
        }

        mapView.getMapAsync {
            onMapReady(it)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("CheckIn"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

}