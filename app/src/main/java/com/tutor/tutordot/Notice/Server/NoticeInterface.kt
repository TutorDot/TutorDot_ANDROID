package com.tutor.tutordot.Notice.Server

import com.tutor.tutordot.ClassLog.Server.LogResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface NoticeInterface {
    @GET("/notice")
    fun noticeRequest(@Header("jwt") header: String) : Call<NoticeResponse>
}