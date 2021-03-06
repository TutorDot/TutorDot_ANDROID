package com.tutor.tutordot.Notice.Server

import com.tutor.tutordot.ClassLog.Server.DiaryResponse
import com.tutor.tutordot.ClassLog.Server.LectureResponse
import com.tutor.tutordot.ClassLog.Server.LogResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface NoticeInterface {
    @GET("/notice")
    fun noticeRequest(@Header("jwt") header: String) : Call<NoticeResponse>

    @GET("/lecture/name")
    fun lectureRequest(@Header("jwt") header: String) : Call<LectureResponse>

    @GET("/notice/{lid}")
    fun lecnotiRequest(@Header("jwt") header: String, @Path("lid") path: String) : Call<LecnotiResponse>
}