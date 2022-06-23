package com.vnpay.hainv4.database

import androidx.room.*
import com.vnpay.hainv4.model.Account


@Dao
interface AccountDao {

    @Query("select * from account")
    suspend fun getAllAccount(): List<Account>
//    @Update
//    fun updateAccount(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)

}