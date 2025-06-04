package com.it.shka.courseapp

import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("api/cours/curs")
    fun getData(): Call<List<Course>>
}