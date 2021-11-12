package com.example.genderguesser

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInterface {
//    @GET("?name=Dan")
//    fun getData(): Call<MyData>

    @GET("/")
    fun getGenderName(@Query("name") name: String?): Call<MyData>
}