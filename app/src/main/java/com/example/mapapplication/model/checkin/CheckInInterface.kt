package com.example.mapapplication.model.checkin

interface CheckInInterface {

    fun delete(checkIn: CheckIn)

    fun create(checkIn: CheckIn)

    fun edit(checkIn: CheckIn, date: String, locate:String)

    fun load()
}