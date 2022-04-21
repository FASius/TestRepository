package com.example.mapapplication.checkins

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mapapplication.R
import com.example.mapapplication.Repositories
import com.example.mapapplication.databinding.FragmentCheckInEditBinding
import com.example.mapapplication.model.checkin.CheckIn
import com.example.mapapplication.utils.viewModelCreator


class CheckInEditFragment : Fragment(R.layout.fragment_check_in_edit) {

    private lateinit var binding: FragmentCheckInEditBinding
    private val viewModel: CheckInListViewModel by viewModelCreator {
        CheckInListViewModel(Repositories.checkInsRepository)
    }

    private lateinit var checkIn:CheckIn

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckInEditBinding.bind(view)
        prepareInfo()
        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.saveButton.setOnClickListener {
            onSaveButton()
        }
    }

    private fun prepareInfo(){
        val bundle = arguments ?: return
        val id = CheckInEditFragmentArgs.fromBundle(bundle).id
        checkIn = viewModel.getCheckInById(id)
        binding.dateTextView.text = checkIn.date
        binding.descriptionEditText.setText(checkIn.description)
        binding.locationTextView.text = checkIn.locate
    }

    private fun onSaveButton(){
        val date = binding.dateTextView.text.toString()
        val locate = binding.locationTextView.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val newCheckIn = CheckIn(checkIn.id, locate, date, description, checkIn.location)
        viewModel.editCheckIn(newCheckIn)

        findNavController().popBackStack()
    }
}