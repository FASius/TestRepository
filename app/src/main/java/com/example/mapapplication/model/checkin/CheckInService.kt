package com.example.mapapplication.model.checkin

typealias CheckInListener = (checkIns: List<CheckIn>) -> Unit

class CheckInService: CheckInInterface {

    private val checkIns = mutableListOf<CheckIn>()
    private val listeners = mutableSetOf<CheckInListener>()

    override fun load(){
        repeat(100){
            checkIns.add(CheckIn("123", "123"))
        }
        notifyChanges()
    }

    override fun delete(checkIn: CheckIn) {
        checkIns.remove(checkIn)
        notifyChanges()
    }

    override fun create(checkIn: CheckIn) {
        checkIns.add(checkIn)
        notifyChanges()
    }

    override fun edit(checkIn: CheckIn, date:String, locate:String) {
        val index = checkIns.indexOf(checkIn)
        checkIns[index] = CheckIn(locate, date)
        notifyChanges()
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