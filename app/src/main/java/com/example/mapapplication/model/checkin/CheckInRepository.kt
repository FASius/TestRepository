package com.example.mapapplication.model.checkin

interface CheckInRepository {

    fun deleteCheckIn(checkIn: CheckIn)

    fun createCheckIn(checkIn: CheckIn)

    fun editCheckIn(checkIn: CheckIn)

}