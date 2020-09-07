package com.tutor.tutordot.Calendar.Server

data class CalResponse(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<CalData>
)

data class CalData(
    val classId : Int,
    val lectureName : String,
    val color : String,
    val times : Int,
    val hour : Int,
    val location : String,
    val classDate : String,
    val startTime : String,
    val endTime : String
)