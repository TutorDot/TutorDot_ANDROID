package com.tutor.tutordot.StartServer

data class ResponseLogin(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : SomeData?
)

data class SomeData(
    val accessToken : String,
    val role : String
)