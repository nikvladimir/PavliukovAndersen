package com.example.pavliukovandersen.retrofit

import retrofit2.http.GET

interface NewsAPIInterface {
    @GET("v2/everything?q=software&pageSize=20&apiKey=615ab744de7846a0af74aed746685a98")
    suspend fun getAllNews(): Articles
}