package com.tutor.tutordot.MyPage.Server

data class ConnectResponse(
    val status: Int,
    val success: Boolean,
    val message: String
)