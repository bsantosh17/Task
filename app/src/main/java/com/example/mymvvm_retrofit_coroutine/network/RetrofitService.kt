package com.example.mymvvm_retrofit_coroutine.network

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.Coin
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("coins")
    suspend fun getAllCoin() : Response<List<Coin>>

    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                   // .baseUrl("https://jsonplaceholder.typicode.com/")
                    .baseUrl("https://api.coinpaprika.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}