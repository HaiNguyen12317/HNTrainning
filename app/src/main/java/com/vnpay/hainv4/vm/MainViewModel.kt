package com.vnpay.hainv4.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnpay.hainv4.database.AccountDatabase
import com.vnpay.hainv4.manager.HotelManager
import com.vnpay.hainv4.model.*
import com.vnpay.hainv4.network.HotelClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainViewModel(application: Application) : AndroidViewModel(application) {

    init {
        getAllHotel()
    }

    private val accountDao = AccountDatabase.getInstance(application).getAccountDao()

    suspend fun getAllAccount(): List<Account> {
        return accountDao.getAllAccount()
    }

    suspend fun addAccount(account: Account) {
        accountDao.insertAccount(account)
    }

    private fun getHotels(): LiveData<ArrayList<Hotel>> = HotelManager.listHotel
    val listHotel: LiveData<ArrayList<Hotel>>
        get() = getHotels()

    private fun getItems(): LiveData<ArrayList<Item>> = HotelManager.listItem
    val item: LiveData<ArrayList<Item>>
        get() = getItems()


    private fun getAllHotel() {
        viewModelScope.launch {
            var hotels: Call<Model> = HotelClient.invoke().getAllHotel()
            hotels.enqueue(object : Callback<Model> {
                override fun onResponse(call: Call<Model>, response: Response<Model>) {
                    Log.d("anhhai","response: ${response.code()}, ${response.body()}")
                    if (response.isSuccessful) {
                        response.body().let {
                            if (it != null) {
                                HotelManager.model = it
                                HotelManager.getAllHotel()
                                Log.d("anhhai", "vm: ${HotelManager.allHotel.size}")
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Model>, t: Throwable) {
                    Log.d("AGT", "CALL API ERROR anhhai $t")
                    viewModelScope.launch {
                        hotels = HotelClient.invoke().getAllHotel()
                    }
                }
            })
        }

    }
}

