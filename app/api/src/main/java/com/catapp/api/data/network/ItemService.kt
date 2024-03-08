package com.catapp.api.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ItemService {

    @GET("cat/{id}")
    suspend fun getItem(@Path("id") id: String): ResponseBody

    @GET("api/tags")
    suspend fun getAllTags(): List<String>
}