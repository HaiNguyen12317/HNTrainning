package com.vnpay.hainv4.model

import com.google.gson.annotations.SerializedName

data class Growth(
    val year : Int,

    @SerializedName("growth_rate")
    val growthRate: Float
)
