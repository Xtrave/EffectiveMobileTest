package com.example.effectivemobiletest.main.data.remote

import com.example.effectivemobiletest.main.data.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CoursesApi {

    @GET("courses")
    suspend fun getAllCourses(): Response<CoursesResponse>

}