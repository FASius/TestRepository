package com.example.mapapplication.checkins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mapapplication.model.checkin.CheckIn
import com.example.mapapplication.model.checkin.CheckInListener
import com.example.mapapplication.model.checkin.CheckInManager

class CheckInListViewModel(
    private val manager: CheckInManager
) : ViewModel() {

    private val _checkIns = MutableLiveData<List<CheckIn>>()
    val checkIns: LiveData<List<CheckIn>> = _checkIns

    private val listener: CheckInListener = {
        _checkIns.value = it
    }

    init {
        loadCheckIns()
    }


    fun getCheckInById(id: Long): CheckIn = manager.getCheckInById(id)

    fun generateId(): Long {
        var maxId = 0L
        checkIns.value?.forEach{
            if (it.id >= maxId)
                maxId = it.id + 1
        }
        return maxId
    }

    private fun loadCheckIns(){
        manager.addListener(listener)
        manager.notifyListener()
    }

    override fun onCleared() {
        super.onCleared()
        manager.removeListener(listener)
    }

    fun createCheckIn(checkIn: CheckIn){
        manager.createCheckIn(checkIn)
    }

    fun editCheckIn(checkIn: CheckIn){
        manager.editCheckIn(checkIn)
    }

    fun deleteCheckIn(checkIn: CheckIn){
        manager.deleteCheckIn(checkIn)
    }

}