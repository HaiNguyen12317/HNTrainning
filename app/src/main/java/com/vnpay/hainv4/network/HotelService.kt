package com.vnpay.hainv4.network

import com.vnpay.hainv4.model.Item
import com.vnpay.hainv4.model.Model
import retrofit2.Call
import retrofit2.http.GET

interface HotelService {

    @GET("62297d6fe022c5f0e5a8")
     fun getAllHotel(): Call<Model>
}