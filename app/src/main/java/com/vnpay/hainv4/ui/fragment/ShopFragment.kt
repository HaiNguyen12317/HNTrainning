package com.vnpay.hainv4.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vnpay.hainv4.Const.Const
import com.vnpay.hainv4.databinding.FragmentShopBinding
import com.vnpay.hainv4.ui.activity.LoginActivity

class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater)

        return binding.root
    }
}