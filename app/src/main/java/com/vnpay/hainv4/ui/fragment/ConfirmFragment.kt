package com.vnpay.hainv4.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.vnpay.hainv4.databinding.FragmentConfirmBinding
import com.vnpay.hainv4.define.GenericTextWatcher
import com.vnpay.hainv4.ui.activity.MainActivity


class ConfirmFragment : Fragment() {
    private lateinit var binding : FragmentConfirmBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmBinding.inflate(inflater)
        val editText = arrayListOf<EditText>(binding.edtOtp1,binding.edtOtp2,binding.edtOtp3,binding.edtOtp4,binding.edtOtp5,binding.edtOtp6)
        binding.edtOtp1.addTextChangedListener(GenericTextWatcher(binding.edtOtp1,editText))
        binding.edtOtp2.addTextChangedListener(GenericTextWatcher(binding.edtOtp2,editText))
        binding.edtOtp3.addTextChangedListener(GenericTextWatcher(binding.edtOtp3,editText))
        binding.edtOtp4.addTextChangedListener(GenericTextWatcher(binding.edtOtp4,editText))
        binding.edtOtp5.addTextChangedListener(GenericTextWatcher(binding.edtOtp5,editText))
        binding.edtOtp6.addTextChangedListener(GenericTextWatcher(binding.edtOtp6,editText))

        binding.btnContinue.setOnClickListener {
            if (binding.edtOtp1.text.isEmpty()||binding.edtOtp2.text.isEmpty()||binding.edtOtp3.text.isEmpty()||binding.edtOtp4.text.isEmpty()||binding.edtOtp5.text.isEmpty()||binding.edtOtp6.text.isEmpty()){
                binding.tvError.visibility = View.VISIBLE
                binding.edtOtp1.text.clear()
                binding.edtOtp2.text.clear()
                binding.edtOtp3.text.clear()
                binding.edtOtp4.text.clear()
                binding.edtOtp5.text.clear()
                binding.edtOtp6.text.clear()
            }else{
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
            }

        }
        return binding.root
    }
}