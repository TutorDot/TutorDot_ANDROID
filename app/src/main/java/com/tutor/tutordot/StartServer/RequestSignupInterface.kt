package com.tutor.tutordot.StartServer

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestSignupInterface{
    @POST("/user/signup")
    fun requestSignup(@Body body : RequestSignup) : Call<ResponseSignup>

    @POST("/user/signin/duplication")
    fun requestDuplicate(@Body body : RequestDuplicate) : Call<ResponseDuplicate>

}