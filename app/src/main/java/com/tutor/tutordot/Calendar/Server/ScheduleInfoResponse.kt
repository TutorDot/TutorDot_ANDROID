package com.tutor.tutordot.Calendar.Server

data class ScheduleInfoResponse(
    val status : Int,
    val success : Boolean,
    val message : String,
    val infodata : List<InfoData>
)

data class InfoData(
    val lectureId : Int,
    val color : String,
    val lectureName : String,
    val date : String,
    val startTime : String,
    val endTime : String,
    val location : String
)