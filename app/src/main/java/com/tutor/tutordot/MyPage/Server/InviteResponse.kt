package com.tutor.tutordot.MyPage.Server

data class InviteResponse(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: List<InviteData>
)

data class InviteData(
    val code: String
)