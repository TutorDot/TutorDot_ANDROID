package com.tutor.tutordot.ClassLog.Server

data class LectureResponse (
    val status : Int,
    val success : Boolean,
    val message : String,
    val data : List<LectureSomeData>
)

data class LectureSomeData(
    val lectureId : Int,
    val lectureName : String
)