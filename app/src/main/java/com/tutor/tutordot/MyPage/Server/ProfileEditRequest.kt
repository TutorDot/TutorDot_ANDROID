package com.tutor.tutordot.MyPage.Server

data class ProfileEditRequest(
    val userName: String,
    val role: String,
    val intro: String,
    val profileUrl: String
)