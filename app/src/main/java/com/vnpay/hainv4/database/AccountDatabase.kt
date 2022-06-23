package com.vnpay.hainv4.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vnpay.hainv4.model.Account


@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountDatabase : RoomDatabase() {

    abstract fun getAccountDao(): AccountDao

    companion object {

        @Volatile
        private var instance: AccountDatabase? = null

        fun getInstance(context: Context): AccountDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                AccountDatabase::class.java,
                "account.db"
            ).fallbackToDestructiveMigration()
                .build()
        }

    }
}