package com.example.mapapplication.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mapapplication.R
import com.example.mapapplication.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)

        binding.about.setOnClickListener {
            val directions = SettingsFragmentDirections.actionSettingsFragmentToAboutFragment()
            findNavController().navigate(directions)
        }

        binding.changePassword.setOnClickListener {
            val directions = SettingsFragmentDirections.actionSettingsFragmentToChangePasswordFragment()
            findNavController().navigate(directions)
        }

        binding.editProfile.setOnClickListener {
            val directions = SettingsFragmentDirections.actionSettingsFragmentToEditProfileFragment()
            findNavController().navigate(directions)
        }
    }



}