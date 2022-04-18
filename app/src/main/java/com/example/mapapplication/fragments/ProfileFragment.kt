package com.example.mapapplication.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mapapplication.R
import com.example.mapapplication.databinding.FragmentProfileBinding
import com.example.mapapplication.model.CheckInAdapter
import com.example.mapapplication.model.checkin.CheckInListener
import com.example.mapapplication.model.checkin.CheckInService

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter: CheckInAdapter
    private lateinit var checkInService:CheckInService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        checkInService = CheckInService()
        checkInService.addListener(checkInListener)
        adapter = CheckInAdapter()
        checkInService.load()


        val layoutManager = LinearLayoutManager(requireContext())
        binding.checkinsRecyclerView.layoutManager = layoutManager
        binding.checkinsRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        checkInService.removeListener(checkInListener)
    }

    private val checkInListener:CheckInListener = {
        adapter.checkInsList = it
    }

}