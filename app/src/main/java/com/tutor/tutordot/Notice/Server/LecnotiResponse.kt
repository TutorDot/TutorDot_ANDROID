package com.tutor.tutordot.Notice.Server

class LecnotiResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<LecnotiRData>
)

data class LecnotiRData(
    val noticeDate: String,
    val lectureId: Int,
    val lectureName: String,
    val color: String,
    val noticeType: Int,
    val noticeText: String
)