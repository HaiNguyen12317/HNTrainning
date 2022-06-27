package com.vnpay.hainv4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vnpay.hainv4.databinding.ItemChildBinding
import com.vnpay.hainv4.model.Hotel

class HotelAdapter : RecyclerView.Adapter<HotelAdapter.ViewHolder>() {
    private val differUtils = object : DiffUtil.ItemCallback<Hotel>() {
        override fun areItemsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem.name== newItem.name
        }

        override fun areContentsTheSame(oldItem: Hotel, newItem: Hotel): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differUtils)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelAdapter.ViewHolder {
        return HotelAdapter.ViewHolder(ItemChildBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HotelAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
    class ViewHolder(private val binding: ItemChildBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hotel : Hotel){
            binding.hotelName.text = hotel.name.toString()
            binding.hotelAddress.text = hotel.address.toString()
            Glide.with(binding.imgChildItem).load(hotel.image.toString()).centerCrop().into(binding.imgChildItem)
        }
    }
}