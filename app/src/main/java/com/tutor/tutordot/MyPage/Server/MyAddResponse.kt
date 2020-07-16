package com.tutor.tutordot.MyPage.Server

data class MyAddResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: MyInfoData?
)