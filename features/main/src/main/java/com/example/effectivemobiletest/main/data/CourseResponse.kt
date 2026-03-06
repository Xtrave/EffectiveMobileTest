package com.example.effectivemobiletest.main.data

import com.google.gson.annotations.SerializedName

data class CourseResponse(
    val id: Int,
    val title: String,

    @SerializedName("text")
    val description: String,

    val price: String,

    @SerializedName("rate")
    val rating: String,

    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)
