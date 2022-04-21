package com.example.mapapplication.checkins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapapplication.model.UnableToFindCheckIn
import com.example.mapapplication.model.checkin.CheckIn
import com.example.mapapplication.model.checkin.CheckInManager

class CheckInDetailsViewModel(
    private val checkInManager: CheckInManager
): ViewModel() {

    private val _checkInDetails = MutableLiveData<CheckIn>()
    val checkInDetails:LiveData<CheckIn> = _checkInDetails

    fun loadCheckIn(id: Long){
        if (_checkInDetails.value != null) return
        try {
            _checkInDetails.value = checkInManager.getCheckInById(id)
        } catch (e:UnableToFindCheckIn) {
            e.printStackTrace()
        }
    }

    fun delete(){
        // TODO
    }

    fun edit() {
        // TODO
    }

}