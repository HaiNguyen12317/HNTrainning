package com.vnpay.hainv4.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Hotel(
    @SerializedName("addres")
    val address: String,
    val image: String,
    @PrimaryKey
    val name: String,
    val price: String
):Serializable