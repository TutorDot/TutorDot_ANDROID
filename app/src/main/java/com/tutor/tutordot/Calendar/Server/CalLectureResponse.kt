package com.tutor.tutordot.Calendar.Server

data class CalLectureResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<LectureSomeData>
)

data class LectureSomeData(
    val lectureId : Int,
    val lectureName : String,
    val color : String
)