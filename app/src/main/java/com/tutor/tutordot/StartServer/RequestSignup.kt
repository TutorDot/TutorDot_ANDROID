package com.tutor.tutordot.StartServer

data class RequestSignup(
    val userName : String,
    val email : String,
    val password : String,
    val role : String
)