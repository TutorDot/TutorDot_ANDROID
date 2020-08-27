package com.tutor.tutordot.Calendar.Server

data class ScheduleEditRequest(
    val date : String,
    val startTime : String,
    val endTime : String,
    val location : String
)