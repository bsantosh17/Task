package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

data class Coin(val id: String,
                 val name: String,
                 val symbol: String,
                 val rank: String,
                 val is_new: Boolean,
                 val is_active: Boolean,
                 val type: String,
)