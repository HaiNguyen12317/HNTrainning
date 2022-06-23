package com.vnpay.hainv4.ui.fragment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
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
import com.vnpay.hainv4.databinding.FragmentLoginBinding
import com.vnpay.hainv4.manager.HotelManager
import com.vnpay.hainv4.network.HotelClient
import com.vnpay.hainv4.network.HotelService
import com.vnpay.hainv4.ui.activity.MainActivity
import com.vnpay.hainv4.vm.MainViewModel
import com.vnpay.hainv4.vm.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment(){
    companion object {
        const val SHARED_PREF = "SHARED_PREF"

        const val KEY_CHECKBOX = "CHECK_BOX"
        const val KEY_USERNAME = "USERNAME"

    }
    private lateinit var sharedPreferences: SharedPreferences
    private var isRemembered = false

    private lateinit var binding: FragmentLoginBinding
    private val viewModel : MainViewModel by viewModels {
        MainViewModelFactory(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        binding.btnLogin.setOnClickListener {
            val useName = binding.edtUseName.text.toString()
            val password = binding.edtPassword.text.toString()
            val checked = binding.swRemember.isChecked
            val editor = sharedPreferences.edit()
            editor.putString(KEY_USERNAME, useName)
            editor.putBoolean(KEY_CHECKBOX, checked)
            editor.apply()
            if (useName.isNotEmpty()||password.isNotEmpty()){
                lifecycleScope.launch (Dispatchers.IO){
                    val accounts = viewModel.getAllAccount()
                    for (element in accounts){
                        if (element.userName == useName && element.password == password){
                            lifecycleScope.launch (Dispatchers.Main){
                                val action = LoginFragmentDirections.actionLoginFragmentToConfirmFragment()
                                findNavController().navigate(action)
                            }
                        }
                        else{
                            lifecycleScope.launch(Dispatchers.Main) {
                                binding.tvError.visibility = View.VISIBLE
                                binding.edtPassword.text.clear()
                            }
                        }

                    }
                }
            } else
                Toast.makeText(requireContext(),"Nhập tên đăng nhập và mật khẩu",Toast.LENGTH_LONG).show()
        }
        binding.tvRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean(KEY_CHECKBOX, false)
        if (isRemembered) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
}