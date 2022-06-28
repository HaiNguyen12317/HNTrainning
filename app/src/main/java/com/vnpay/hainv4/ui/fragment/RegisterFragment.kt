package com.vnpay.hainv4.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vnpay.hainv4.databinding.FragmentRegisterBinding
import com.vnpay.hainv4.model.Account
import com.vnpay.hainv4.network.HotelService
import com.vnpay.hainv4.vm.MainViewModel
import com.vnpay.hainv4.vm.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: MainViewModel by viewModels() {
        MainViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)
        binding.btnRegister.setOnClickListener {
            val userName = binding.edtUseName.text.toString()
            val password = binding.edtPassword.text.toString()
            val phone = binding.edtPhone.text.toString()
            val check: Boolean = binding.cbGrand.isChecked
            if (check) {
                if (binding.edtUseName.text.isEmpty() || binding.edtPassword.text.isEmpty() || binding.edtPhone.text.isEmpty()) {
                    Toast.makeText(
                        requireContext().applicationContext,
                        "Vui lòng nhập đầy đủ thông tin",
                        Toast.LENGTH_LONG
                    ).show()
                } else{
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.addAccount(
                            Account(userName = userName, password = password, phone = phone.toInt())
                        )
                    }
                    Toast.makeText(requireContext(), "Đăng kí thành công", Toast.LENGTH_LONG).show()
                    val action =
                        RegisterFragmentDirections.actionRegisterFragmentToConfirmFragment()
                    findNavController().navigate(action)
                }
            } else {
                Toast.makeText(
                    requireContext().applicationContext,
                    "Chưa chấp nhận thỏa thuận",
                    Toast.LENGTH_LONG
                )
                    .show()
//            } else {
//                Toast.makeText(
//                    requireContext().applicationContext,
//                    "Vui lòng nhập đầy đủ thông tin",
//                    Toast.LENGTH_LONG
//                )
//            }
            }
        }
        return binding.root
    }
}