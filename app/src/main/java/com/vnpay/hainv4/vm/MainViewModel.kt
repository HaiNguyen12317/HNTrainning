package com.vnpay.hainv4.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.vnpay.hainv4.database.AccountDatabase
import com.vnpay.hainv4.manager.HotelManager
import com.vnpay.hainv4.model.*
import com.vnpay.hainv4.network.HotelClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class MainViewModel(application: Application) : AndroidViewModel(application) {

    init {
//        getAllHotel()
        getHotel()
        Log.d("anhhai","hottt : ${listHotels.value?.size}")
    }

    private val accountDao = AccountDatabase.getInstance(application).getAccountDao()

    suspend fun getAllAccount(): List<Account> {
        return accountDao.getAllAccount()
    }

    suspend fun addAccount(account: Account) {
        accountDao.insertAccount(account)
    }
    suspend fun deleteAccount(string: String){
        accountDao.delete(string)
    }

//    private fun getHotels(): LiveData<ArrayList<Hotel>> = HotelManager.listHotel
//    val listHotel: LiveData<ArrayList<Hotel>>
//        get() = getHotels()

    private fun getItems(): LiveData<ArrayList<Item>> = HotelManager.listItem
    val item: LiveData<ArrayList<Item>>
        get() = getItems()


//    private fun getAllHotel() {
//        viewModelScope.launch {
//            var hotels: Call<Model> = HotelClient.invoke().getAllHotel()
//            val hotel = HotelClient.invoke().getAllHotel()
//            Log.d("anhhai","hotel invoke ${hotel.toString()}")
//            hotels.enqueue(object : Callback<Model> {
//                override fun onResponse(call: Call<Model>, response: Response<Model>) {
//                    if (response.isSuccessful) {
//                        response.body().let {
//                            if (it != null) {
//                                HotelManager.model = it
//                                HotelManager.getAllHotel()
//                                Log.d("anhhai", "vm: ${HotelManager.allHotel.size}")
//                            }
//                        }
//                    }
//                }
//
//                override fun onFailure(call: Call<Model>, t: Throwable) {
//                    Log.d("AGT", "CALL API ERROR anhhai $t")
//                    viewModelScope.launch {
//                        hotels = HotelClient.invoke().getAllHotel()
//                    }
//                }
//            })
//        }
//
//    }

    private fun getHotell(): LiveData<ArrayList<Hotel>> = HotelManager.listHotel
    val listHotels: LiveData<ArrayList<Hotel>>
    get() = getHotell()

    private fun getHotel(){
        viewModelScope.launch {

            var hotels: Call<List<Hotel>> = HotelClient.invoke().getHotel()
            hotels.enqueue(object : Callback<List<Hotel>> {

                override fun onResponse(call: Call<List<Hotel>>, response: Response<List<Hotel>>) {
                    if (response.isSuccessful) {
                        response.body().let {
                            if (it != null) {
                                HotelManager.all = it as ArrayList<Hotel>
                                HotelManager.getAllHotel()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<List<Hotel>>, t: Throwable) {
                    Log.d("Error", "Call API error $t")
                    viewModelScope.launch {
                        hotels = HotelClient.invoke().getHotel()
                    }
                }

            }
            )


        }
//
    }
}

