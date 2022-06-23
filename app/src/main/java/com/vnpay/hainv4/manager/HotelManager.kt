package com.vnpay.hainv4.manager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vnpay.hainv4.model.Hotel
import com.vnpay.hainv4.model.Item
import com.vnpay.hainv4.model.Model

object HotelManager {
    var item = arrayListOf<Item>()
    var allHotel = arrayListOf<Hotel>()
    var model: Model? = null


    private var allHotelLiveData = MutableLiveData<ArrayList<Hotel>>()
    val listHotel: LiveData<ArrayList<Hotel>>
        get() = allHotelLiveData

    private var allItem = MutableLiveData<ArrayList<Item>>()
    val listItem: LiveData<ArrayList<Item>>
        get() = allItem

    private var thanhhoa = arrayListOf<Hotel>()
    private var decu = arrayListOf<Hotel>()
    private var noibat = arrayListOf<Hotel>()


    private var hotel = arrayListOf<Hotel>()

    private fun getThanhHoaHotel(): List<Hotel> {
        val list = model?.hotelX?.thanhhoa
        list?.forEach {
            allHotel.addAll(it.hotel)
            thanhhoa.addAll(it.hotel)
        }
        return thanhhoa
    }


    private fun getDecuHotel(): List<Hotel> {
        val list = model?.hotelX?.decu
        list?.forEach {
            allHotel.addAll(it.hotel)
            decu.addAll(it.hotel)
        }
        return decu
    }

    private fun getNoibatHotel(): List<Hotel> {
        val list = model?.hotelX?.noibat
        list?.forEach {
            allHotel.addAll(it.hotel)
            noibat.addAll(it.hotel)
        }
        return noibat
    }

    fun getAllHotel() {
        getDecuHotel()
        getNoibatHotel()
        getThanhHoaHotel()
        allHotelLiveData.postValue(allHotel)
        allItem.postValue(item)
    }
}