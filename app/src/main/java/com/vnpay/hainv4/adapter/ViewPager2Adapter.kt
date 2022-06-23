package com.vnpay.hainv4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vnpay.hainv4.databinding.ImageHolderBinding
import com.vnpay.hainv4.model.Image


class ViewPager2Adapter(private val images: List<Image>) : RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {
    private var binding: ImageHolderBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ImageHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size

    class ViewHolder(private val binding: ImageHolderBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(image: Image){
            Glide.with(binding.images).load(image.link).centerCrop().into(binding.images)
        }
    }


}
