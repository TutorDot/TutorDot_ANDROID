package com.tutor.tutordot.Calendar.Server

import com.tutor.tutordot.ClassLog.Server.LectureResponse
import com.tutor.tutordot.ClassLog.Server.LogModiRequest
import com.tutor.tutordot.ClassLog.Server.LogModiResponse
import retrofit2.Call
import retrofit2.http.*

interface CalendarLogResponseInterface{

    //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
    @GET("/calander")
    fun calendarlogRequest(@Header("jwt") header: String) : Call<CalendarLogResponseData>

    @POST("/calander/class")
    fun scheduleAddRequest(@Header("jwt") header: String, @Body body : ScheduleAddRequest) : Call<ScheduleAddResponse>

    @PUT("/calander/class/{cid}")
    fun scheduleEditRequest(@Header("jwt") header: String, @Body body : ScheduleEditRequest, @Path("cid") path:String) : Call<ScheduleEditResponse>

    @DELETE("/calander/class/{cid}")
    fun scheduleDeleteRequest(@Header("jwt") header: String, @Path("cid") path:String): Call<ScheduleAddResponse>

    @GET("/lecture/name")
    fun lectureRequest(@Header("jwt") header: String) : Call<LectureResponse>
}