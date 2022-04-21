package com.example.mapapplication.model.checkin

import com.example.mapapplication.model.UnableToFindCheckIn

typealias CheckInListener = (checkIns: List<CheckIn>) -> Unit

/*
* TODO remake CheckInRepository to SQLCheckInManager
* */
class CheckInManager():
    CheckInRepository {

    private val checkIns = mutableListOf<CheckIn>()
    private val listeners = mutableSetOf<CheckInListener>()

    fun getCheckInById(id: Long): CheckIn {
        checkIns.forEach{
            if (it.id == id) return it
        }
        throw UnableToFindCheckIn()
    }

    init {
        loadCheckIns()
    }

    fun notifyListener(){
        notifyChanges()
    }

    private fun loadCheckIns(){
        repeat(6){
            checkIns.add(CheckIn(it.toLong(), "123", "123"))
        }
    }

    override fun deleteCheckIn(checkIn: CheckIn) {
        checkIns.remove(checkIn)
        notifyChanges()
    }

    override fun createCheckIn(checkIn: CheckIn) {
        checkIns.add(checkIn)
        notifyChanges()
    }

    override fun editCheckIn(checkIn: CheckIn) {
        for (index in checkIns.indices)
            if (checkIns[index].id == checkIn.id){
                checkIns[index] = checkIn
                notifyChanges()
                return
            }
    }

    fun addListener(listener: CheckInListener){
        listeners.add(listener)
    }

    fun removeListener(listener: CheckInListener){
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(checkIns) }
    }

}