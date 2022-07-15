package com.vnpay.hainv4.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.vnpay.hainv4.databinding.FragmentForgotPasswordBinding
import com.vnpay.hainv4.define.GenericTextWatcher
import com.vnpay.hainv4.model.Account
import com.vnpay.hainv4.ui.activity.MainActivity
import com.vnpay.hainv4.vm.MainViewModel
import com.vnpay.hainv4.vm.MainViewModelFactory
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPassFragment : Fragment() {
    private lateinit var binding : FragmentForgotPasswordBinding
    private val viewModel : MainViewModel by viewModels {
        MainViewModelFactory(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater)
        val editText = arrayListOf<EditText>(binding.edtOtp1,binding.edtOtp2,binding.edtOtp3,binding.edtOtp4,binding.edtOtp5,binding.edtOtp6)
        binding.edtOtp1.addTextChangedListener(GenericTextWatcher(binding.edtOtp1,editText))
        binding.edtOtp2.addTextChangedListener(GenericTextWatcher(binding.edtOtp2,editText))
        binding.edtOtp3.addTextChangedListener(GenericTextWatcher(binding.edtOtp3,editText))
        binding.edtOtp4.addTextChangedListener(GenericTextWatcher(binding.edtOtp4,editText))
        binding.edtOtp5.addTextChangedListener(GenericTextWatcher(binding.edtOtp5,editText))
        binding.edtOtp6.addTextChangedListener(GenericTextWatcher(binding.edtOtp6,editText))
//        val phone = text.toInt()
//        lifecycleScope(Dispatchers.IO){
//            val account:Account = viewModel.getAccountByPhone(phone)as Account
//        }

        binding.btnContinue1.setOnClickListener {
            val useName = binding.edtUseName.text.toString()
            lifecycleScope.launch(Dispatchers.IO) {
                val account = viewModel.getAccountByName(useName)
                if (account == null){
                    lifecycleScope.launch(Dispatchers.Main) {

                        binding.tvErrorUserName.visibility = View.VISIBLE
                    }
                }else {
                        lifecycleScope.launch(Dispatchers.Main) {
                            binding.lnOtp.visibility = View.VISIBLE
                            if (binding.edtOtp1.text.isEmpty()||binding.edtOtp2.text.isEmpty()||binding.edtOtp3.text.isEmpty()||binding.edtOtp4.text.isEmpty()||binding.edtOtp5.text.isEmpty()||binding.edtOtp6.text.isEmpty()){
//                               000
                                binding.edtOtp1.text.clear()
                                binding.edtOtp2.text.clear()
                                binding.edtOtp3.text.clear()
                                binding.edtOtp4.text.clear()
                                binding.edtOtp5.text.clear()
                                binding.edtOtp6.text.clear()
                                binding.edtOtp1.addTextChangedListener(GenericTextWatcher(binding.edtOtp1,editText))
                            }else{
                               binding.btnContinue1.setOnClickListener {
                                   val password = account.password
                                   binding.tvPassword.text = password
                                   binding.tvPassword.visibility = View.VISIBLE
                                   binding.tvSignPassword.visibility = View.VISIBLE
                               }
                            }
                    }
                }
            }
//            if (viewMod)

        }
        return binding.root
    }
}