package com.tutor.tutordot.Calendar.Server

data class ScheduleAddRequest(
    val lectureId : Int,
    val date : String,
    val startTime : String,
    val endTime : String,
    val location : String
)