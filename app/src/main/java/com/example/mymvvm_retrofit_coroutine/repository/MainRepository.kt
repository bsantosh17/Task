package com.example.mymvvm_retrofit_coroutine.repository

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.Coin
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.NetworkState
import com.example.mymvvm_retrofit_coroutine.network.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllCoin() : NetworkState<List<Coin>> {
            val response = retrofitService.getAllCoin()
            return if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    NetworkState.Success(responseBody)
                } else {
                    NetworkState.Error(response)
                }
            } else {
                NetworkState.Error(response)
            }
        }

}