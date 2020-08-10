package com.tutor.tutordot.MyPage.Server

import com.google.gson.JsonObject
import com.tutor.tutordot.MyPage.Server.MyPageRequestToServer.service
import retrofit2.Call
import retrofit2.http.*
interface MypageInterface {

        //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
        //@GET("/lecture")
        //fun classListRequest() : Call<ClassListResponse>

        @GET("/lecture")
        fun classListRequest(@Header("jwt") header: String) : Call<ClassListResponse>



        //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
        @PUT("/user/profile")
        fun profileEditRequest(@Header("jwt") header: String, @Body body : ProfileEditRequest) : Call<ProfileEditResponse>

        //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
        @PUT("/lecture")
        fun myAddRequest(@Header("jwt") header: String, @Body body : MyAddRequest) : Call<MyAddResponse>

        //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
        @GET("/lecture/invitation/1")
        fun inviteRequest(@Header("jwt") header: String) : Call<InviteResponse>

        //@Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NzA1Njg4MywiZXhwIjoxNTk4MjY2NDgzLCJpc3MiOiJvdXItc29wdCJ9.ltbLv_xIZGpjf9L10d2TZ0jn6mCk8RTyn6PADpr7EgE")
        @POST("/lecture/invitation")
        fun requestConnect(@Header("jwt") header: String, @Body body : ConnectRequest) : Call<ConnectResponse>

//        @GET("/lecture")
//        fun progressRequest() : Call<ProgressResponse>
//
//        @PUT("/diary/hw/:did")
//        fun logModiRequest(@Body body : LogModiRequest) : Call<LogModiResponse>
}