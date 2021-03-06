package com.tutor.tutordot.MyPage.Server

data class ClassListResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<ClassListData>
)
data class ClassListData(
    val lectureId: Int,
    val lectureName: String,
    val color: String,
    val profileUrls: List<ProfileData>
)
data class ProfileData(
    val profileUrl: String
)
