package com.example.university_schedule.dto

import java.text.FieldPosition

data class ServerResponse(
    val position : Int,
    val lessonName : String,
    val lessonTime : String,
    val lessonPractics : String
)
