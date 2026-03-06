package com.example.effectivemobiletest.main.presentation

import com.example.effectivemobiletest.main.data.CourseResponse

sealed class CourseItem {
    data class CourseRow(
        val course: CourseResponse,
        val imageRes: Int
    ) : CourseItem()
}