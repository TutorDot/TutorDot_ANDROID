package com.tutor.tutordot.ClassLog.Server

import com.tutor.tutordot.Startpage.myjwt
import retrofit2.Call
import retrofit2.http.*

interface LogResponseInterface{

    //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
    @GET("/diary")
    fun logRequest(@Header("jwt") header: String) : Call<LogResponse>



    //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
    @GET("/diary/bar/{lid}")
    fun progressRequest(@Header("jwt") header: String, @Path("lid") path: String) : Call<ProgressResponse>

    //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
    @GET("/diary/bar/2")
    fun progressRequest2(@Header("jwt") header: String) : Call<ProgressResponse2>

    //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
    @PUT("/diary/hw/{did}")
    fun logModiRequest(@Header("jwt") header: String, @Body body : LogModiRequest, @Path("did") path: String) : Call<LogModiResponse>

    @GET("/lecture/name")
    fun lectureRequest(@Header("jwt") header: String) : Call<LectureResponse>
}