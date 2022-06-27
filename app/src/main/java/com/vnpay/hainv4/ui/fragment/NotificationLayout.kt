package com.vnpay.hainv4.ui.fragment

import android.app.Activity
import android.os.Bundle
import com.vnpay.hainv4.databinding.LayoutNotificationBinding

class NotificationLayout : Activity() {
    private lateinit var binding: LayoutNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutNotificationBinding.inflate(layoutInflater)
       setContentView(binding.root)
    }
}