package com.example.pavliukovandersen.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIInterface {
    @GET("everything")
    suspend fun queryAPI(
        @Query("q") theme: String,
        @Query("from") date: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String
    ): Response<Articles>
}
