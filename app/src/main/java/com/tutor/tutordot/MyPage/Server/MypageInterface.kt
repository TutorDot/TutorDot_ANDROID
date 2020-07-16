package com.tutor.tutordot.MyPage.Server

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PUT

interface MypageInterface {
        @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")
        @GET("/lecture")
        fun classListRequest() : Call<ClassListResponse>

        @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")
        @PUT("/user/profile")
        fun profileEditRequest(@Body body : ProfileEditRequest) : Call<ProfileEditResponse>

//        @GET("/lecture")
//        fun progressRequest() : Call<ProgressResponse>
//
//        @PUT("/diary/hw/:did")
//        fun logModiRequest(@Body body : LogModiRequest) : Call<LogModiResponse>
}