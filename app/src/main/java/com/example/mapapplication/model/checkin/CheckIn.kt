package com.example.mapapplication.model.checkin

import com.google.android.gms.maps.model.LatLng

data class CheckIn(
    val id:Long,
    val locate:String,
    val date: String,
    var description: String = "",
    val location:LatLng = LatLng(35.672464894649, 139.75788809439902)
)
