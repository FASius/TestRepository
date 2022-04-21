package com.example.mapapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mapapplication.R
import com.example.mapapplication.databinding.FragmentLoadBinding

/*
* TODO
* */
class LoadFragment : Fragment(R.layout.fragment_load) {

    private lateinit var binding: FragmentLoadBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoadBinding.bind(view)
    }
}
