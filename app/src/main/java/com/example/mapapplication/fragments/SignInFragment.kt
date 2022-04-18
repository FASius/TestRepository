package com.example.mapapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mapapplication.R
import com.example.mapapplication.databinding.FragmentSignInBinding
import com.example.mapapplication.viewmodel.SignInViewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private val model: SignInViewModel by activityViewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.createAccountButton.setOnClickListener {


            val directions = SignInFragmentDirections.actionSignInFragmentToCreateAccountFragment()
            findNavController().navigate(directions)
        }
        binding.signInButton.setOnClickListener {
            val directions = SignInFragmentDirections.actionSignInFragmentToTabsFragment()
            findNavController().navigate(directions)
        }
    }
}



