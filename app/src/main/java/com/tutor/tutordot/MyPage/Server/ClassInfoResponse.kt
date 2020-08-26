package com.tutor.tutordot.MyPage.Server

data class ClassInfoResponse(
val status: Int,
val success: Boolean,
val message: String,
val data: ClassInfoData?
)
data class ClassInfoData(
    val price: Int,
    val depositCycle: Int,
    val lectureName: String,
    val orgLocation: String,
    val color: String,
    val bank: String,
    val accountNo: String,
    val userName: String,
    val role: String,
    val intro: String,
    val profileUrl: String,
    val schedules: List<ScheduleData>
)
data class ScheduleData(
    val day: String,
    val orgStartTime: String,
    val orgEndTime: String

)