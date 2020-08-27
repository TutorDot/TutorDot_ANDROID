package com.tutor.tutordot.MyPage.Server

data class MyAddRequest(
    val lectureName: String,
    val color: String,
    var schedules: List<ScheduleData>,
    val orgLocation: String,
    val bank: String,
    val accountNumber: String,
    val totalHours: Int,
    val price: Int
)

data class ScheduleData(
    val day: String,
    val orgStartTime: String,
    val orgEndTime: String
)