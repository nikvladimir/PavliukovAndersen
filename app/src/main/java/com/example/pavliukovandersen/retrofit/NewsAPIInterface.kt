package com.example.pavliukovandersen.retrofit

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIInterface {
    @GET("everything")
    suspend fun test3(
        @Query("q") theme: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String
    ): Articles

}
