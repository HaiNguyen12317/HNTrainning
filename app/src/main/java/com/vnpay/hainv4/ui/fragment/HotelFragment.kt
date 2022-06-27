package com.vnpay.hainv4.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vnpay.hainv4.adapter.HotelAdapter
import com.vnpay.hainv4.adapter.ParentItemAdapter
import com.vnpay.hainv4.databinding.FragmentHomeBinding
import com.vnpay.hainv4.databinding.FragmentHotelBinding
import com.vnpay.hainv4.network.HotelClient
import com.vnpay.hainv4.vm.MainViewModel
import com.vnpay.hainv4.vm.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelFragment : Fragment() {
    private lateinit var binding: FragmentHotelBinding
    private val viewModel : MainViewModel by viewModels {
        MainViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHotelBinding.inflate(inflater)


        val adapter = ParentItemAdapter()
        binding.parentRecyclerview.layoutManager = GridLayoutManager(requireContext(),3)
//        binding.parentRecyclerview.adapter = adapter
        viewModel.item.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    adapter.differ.submitList(it)
                }
            }
        }
        val adapter2 = HotelAdapter()
        binding.parentRecyclerview.adapter= adapter2
        viewModel.listHotels.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){

                    adapter2.differ.submitList(it)

            }
        }
        return binding.root
    }

}