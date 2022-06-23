package com.vnpay.hainv4.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vnpay.hainv4.databinding.FragmentNotifyBinding


class NotifyFragment : Fragment() {
    private lateinit var binding: FragmentNotifyBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotifyBinding.inflate(inflater)
        return binding.root
    }
}