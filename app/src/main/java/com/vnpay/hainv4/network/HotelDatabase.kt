package com.vnpay.hainv4.network

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vnpay.hainv4.model.Hotel

@Database(entities = [Hotel::class],version = 1,exportSchema = false)
abstract class HotelDatabase : RoomDatabase(){

}