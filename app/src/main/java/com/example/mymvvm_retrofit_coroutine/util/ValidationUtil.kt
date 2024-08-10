package com.example.mymvvm_retrofit_coroutine.util

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.Coin

object ValidationUtil {

    fun validateMovie(coin: Coin) : Boolean {
        if (coin.name.isNotEmpty() && coin.id.isNotEmpty()) {
            return true
        }
        return false
    }
}