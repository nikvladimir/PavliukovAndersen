package com.example.pavliukovexamples.retrofit

import com.example.pavliukovexamples.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private fun httpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.baseNewsUrl)
//        .client(httpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: NewsAPIInterface = retrofit.create(NewsAPIInterface::class.java)
}