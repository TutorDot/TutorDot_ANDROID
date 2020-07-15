package com.tutor.tutordot.ClassLog.Server

data class ProgressResponse(
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<ProgressSomeData>
)

data class ProgressSomeData(
    val times : Int,
    val hour : Int,
    val depositCycle : Int,
    val classDate : String
)