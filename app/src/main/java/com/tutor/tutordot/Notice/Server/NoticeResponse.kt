package com.tutor.tutordot.Notice.Server

data class NoticeResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<NoticeRData>
)
data class NoticeRData(
    val noticeDate: String,
    val lectureId: Int,
    val lectureName: String,
    val color: String,
    val noticeType: Int
)