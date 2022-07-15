package com.vnpay.hainv4.ui.fragment

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vnpay.hainv4.Const.Const
import com.vnpay.hainv4.databinding.FragmentLoginBinding
import com.vnpay.hainv4.ui.activity.MainActivity
import com.vnpay.hainv4.vm.MainViewModel
import com.vnpay.hainv4.vm.MainViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    companion object {
        const val SHARED_PREF = "SHARED_PREF"

        const val KEY_USERNAME = "USERNAME"
        const val KEY_REGISTER = "REGISTER"
        const val KEY_REGISTER2 = "REGISTER2"

    }

    private lateinit var sharedPreferences: SharedPreferences
    private var isRemembered = false


    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(requireActivity().application)
    }

    @SuppressLint("CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREF, MODE_PRIVATE)

        val editor = sharedPreferences.edit()
//        val isRemember = sharedPreferences.getBoolean(Const.KEY_REMEMBER,false)
//        if (!isRemember){
//            binding.swRemember.isChecked = false
//        }
//        binding.swRemember.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                editor.putBoolean(Const.KEY_REMEMBER, true)
//                editor.apply()
//            } else {
//                editor.putBoolean(Const.KEY_REMEMBER, false)
//                editor.apply()
//            }
//        }


        binding.btnLogin.setOnClickListener {
            val useName = binding.edtUseName.text.toString()
            val password = binding.edtPassword.text.toString()
            val checked = binding.swRemember.isChecked
            editor.putString(KEY_USERNAME, useName)
            editor.putBoolean(Const.KEY_REMEMBER, checked)
            editor.apply()
            if (useName.isNotEmpty() || password.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val accounts = viewModel.getAllAccount()
                    for (element in accounts) {
                        if (element.userName == useName && element.password == password) {
                            lifecycleScope.launch(Dispatchers.Main) {
                                val action =
                                    LoginFragmentDirections.actionLoginFragmentToConfirmFragment()
                                findNavController().navigate(action)
                            }
                        } else {
                            lifecycleScope.launch(Dispatchers.Main) {
                                binding.tvError.visibility = View.VISIBLE
                                binding.edtPassword.text.clear()
                            }
                        }
                    }
                    if (accounts.isEmpty()) {
                        lifecycleScope.launch(Dispatchers.Main) {
                            binding.tvError.visibility = View.VISIBLE
                            binding.edtPassword.text.clear()
                        }
                    }
                }
            } else
                Toast.makeText(
                    requireContext(),
                    "Nhập tên đăng nhập và mật khẩu",
                    Toast.LENGTH_LONG
                ).show()
        }

        binding.tvRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }

        val useName = sharedPreferences.getString(Const.USER_NAME, "")
        binding.edtUseName.setText(useName)
//        isRemembered = sharedPreferences.getBoolean(Const.KEY_REMEMBER,false)
//        if (isRemembered){
//            val intent = Intent(requireContext(),MainActivity::class.java)
//            startActivity(intent)
//        }
        binding.tvForgotPassword.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToForgotPassFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }
}
