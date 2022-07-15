package com.vnpay.hainv4.network

import com.vnpay.hainv4.model.Growth
import com.vnpay.hainv4.model.Hotel
import retrofit2.Call
import retrofit2.http.GET

interface GrowthService {
    @GET("68c7f3c9f1e91fffec0b")
    fun getGrowth(): Call<List<Growth>>
}