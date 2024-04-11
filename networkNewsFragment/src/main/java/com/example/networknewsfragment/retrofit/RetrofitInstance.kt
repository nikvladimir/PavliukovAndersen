package com.example.networknewsfragment.retrofit

import com.example.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.baseNewsUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: NewsAPIInterface = retrofit.create(NewsAPIInterface::class.java)
}