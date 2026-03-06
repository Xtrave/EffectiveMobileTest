package com.example.effectivemobiletest.main.data.remote

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val json = context.assets
            .open("courses.json")
            .bufferedReader()
            .use { it.readText() }

        return Response.Builder()
            .code(200)
            .message(json)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .body(
                json.toByteArray()
                    .toResponseBody("application/json".toMediaType())
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}