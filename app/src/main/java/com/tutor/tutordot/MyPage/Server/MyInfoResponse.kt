package com.tutor.tutordot.MyPage.Server

data class MyInfoResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: MyInfoData?
)
data class MyInfoData(
    val userName: String,
    val role: String,
    val intro : String,
    val profilUrl: String
)
