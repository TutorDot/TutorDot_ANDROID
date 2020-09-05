package com.tutor.tutordot.Notice.Server

data class LectureResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<LectureSomeData>
)

data class LectureSomeData(
    val lid : Int,
    val lectureName : String
)