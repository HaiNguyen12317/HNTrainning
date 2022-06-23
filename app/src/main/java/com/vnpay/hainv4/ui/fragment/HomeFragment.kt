package com.vnpay.hainv4.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.vnpay.hainv4.adapter.ViewPager2Adapter
import com.vnpay.hainv4.databinding.FragmentHomeBinding
import com.vnpay.hainv4.manager.HotelManager
import com.vnpay.hainv4.model.Image
import com.vnpay.hainv4.vm.MainViewModel
import com.vnpay.hainv4.vm.MainViewModelFactory


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel : MainViewModel by viewModels {
        MainViewModelFactory(requireActivity().application)
    }
    private val listImage = arrayListOf<Image>(
        Image("https://amthucmaison.com/wp-content/uploads/2019/11/5-mam-tang-1-maison-02-dat-tiec-ngon-nam-2019-2.jpg"),
        Image("https://stix.vn/wp-content/uploads/2019/11/banner-web-HYG_930X405.png"),
        Image("https://fvgtravel.com.vn/uploads/images/Year%20End%20Party%202019_FIVITEL%20DANANG%20(Medium).jpg"),
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        Log.d("anhhai","1:${viewModel.listHotel.value}")
        Log.d("anhhai","2:${viewModel.item.value}")
        Log.d("anhhai","3:${HotelManager.allHotel}")

        val viewPager2Adapter = ViewPager2Adapter(listImage)
        binding.vpOne.adapter = viewPager2Adapter

        binding.dot1.setViewPager2(binding.vpOne)
        binding.vpOne.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // This method is triggered when there is any scrolling activity for the current page
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })


        binding.hotel.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHotelFragment()
            findNavController().navigate(action)
        }


        return binding.root
    }
}