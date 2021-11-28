package com.gandergusser.ganderguesser

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/")
    fun getGenderName(@Query("name") name: String?): Call<MyData>
}