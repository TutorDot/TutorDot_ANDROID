package com.tutor.tutordot.MyPage.Server.ClassList

data class ClassListResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<ClassListData>
)
data class ClassListData(
    val lectureId: Int,
    val lectureName: String,
    val profileUrls: List<String>
)