package com.vnpay.hainv4.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.vnpay.hainv4.model.Image


class IndicatorAdapter(private val listImage: List<Image>,private val context: Context) : PagerAdapter() {
    override fun getCount(): Int = listImage.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

//    override fun instantiateItem(container: View, position: Int): Any {
//        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
////        val view: View = layoutInflater.inflate(R.layout.item, null)
////        val imageView: ImageView = view.findViewById(R.id.image_view)
////        binding.imageView.setImageResource(images.get(position))
////        val viewPager = container as ViewPager
////        viewPager.addView(view, 0)
//        return
//
//    }



}