package com.vnpay.hainv4.ui.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vnpay.hainv4.Const.Const
import com.vnpay.hainv4.databinding.FragmentRegisterBinding
import com.vnpay.hainv4.model.Account
import com.vnpay.hainv4.network.HotelService
import com.vnpay.hainv4.vm.MainViewModel
import com.vnpay.hainv4.vm.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var preferences: SharedPreferences
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
                } else {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val account = viewModel.getAccountByName(userName)

                        if (account == null) {

                           lifecycleScope.launch(Dispatchers.IO) {
                               viewModel.addAccount(
                                   Account(
                                       userName = userName,
                                       password = password,
                                       phone = phone.toInt()
                                   )
                               )
                           }
                            lifecycleScope.launch(Dispatchers.Main) {
                                Log.d("anhhai", "Dang ki thanh cong")
                                Toast.makeText(
                                    requireContext(),
                                    "Đăng kí thành công",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val editor = preferences.edit()
                                editor.putString("USERNAME",userName)
                                editor.apply()
                                val action =
                                    RegisterFragmentDirections.actionRegisterFragmentToConfirmFragment()
                                findNavController().navigate(action)
                            }
                        }else{
                        lifecycleScope.launch(Dispatchers.Main) {
                                Toast.makeText(
                                    requireContext().applicationContext,
                                    "Tên tài khoản đã được sử dụng",
                                    Toast.LENGTH_SHORT
                                ).show()
                                binding.edtUseName
                        }}
                }
            }
        } else {
            Toast.makeText(
                requireContext().applicationContext,
                "Chưa chấp nhận thỏa thuận",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
    return binding.root
}
}