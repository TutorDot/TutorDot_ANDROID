package com.tutor.tutordot.MyPage.Server

data class MyAddRequest(
    val lectureName: String,
    val color: String,
    val schedules: List<ScehduleData>,
    val orgLocation: String,
    val bank: String,
    val accountNumber: String,
    val totalHours: Int,
    val price: Int
)

data class ScehduleData(
    val day: String,
    val orgStartTime: String,
    val orgEndTime: String
)