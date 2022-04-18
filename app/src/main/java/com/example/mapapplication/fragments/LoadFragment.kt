package com.example.mapapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mapapplication.MainActivity
import com.example.mapapplication.MainActivityArgs
import com.example.mapapplication.R
import com.example.mapapplication.databinding.FragmentLoadBinding


class LoadFragment : Fragment(R.layout.fragment_load) {

    private lateinit var binding: FragmentLoadBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoadBinding.bind(view)
        launchMainActivity(false)
    }

    private fun launchMainActivity(isSignedIn : Boolean){
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val args = MainActivityArgs(isSignedIn)
        intent.putExtras(args.toBundle())

        startActivity(intent)
    }
}
