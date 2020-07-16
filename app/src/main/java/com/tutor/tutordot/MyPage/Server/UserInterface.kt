package com.tutor.tutordot.MyPage.Server

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserInterface {
    @Headers("jwt: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjMsIm5hbWUiOiJTZW95b3VuZyIsImlhdCI6MTU5NDgzMzkwMywiZXhwIjoxNTk2MDQzNTAzLCJpc3MiOiJvdXItc29wdCJ9.E4RNGNpeJENsAOZ5v8W_9tXpZjqKdXypbBZFYOSpZMI")

    @GET("/user/profile")
    fun myInfoRequest() : Call<MyInfoResponse>


}