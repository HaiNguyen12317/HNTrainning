package com.vnpay.hainv4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class Account(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "user_name")
    val userName: String = "",
    @ColumnInfo(name = "password")
    val password: String = "",
    @ColumnInfo(name = "phone")
    val phone: Int
)
