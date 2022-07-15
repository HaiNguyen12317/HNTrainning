package com.vnpay.hainv4.ui.fragment

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.vnpay.hainv4.Const.Const
import com.vnpay.hainv4.base.BaseFragment
import com.vnpay.hainv4.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {
    private lateinit var sharedPreferences: SharedPreferences
    override fun initView() {
        sharedPreferences = requireContext().getSharedPreferences(LoginFragment.SHARED_PREF,
            MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        val isDarkMode = sharedPreferences.getBoolean(Const.DARK_MODE,false)
        binding.switch1.isChecked = isDarkMode
        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean(Const.DARK_MODE,true)
                editor.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean(Const.DARK_MODE,false)
                editor.apply()
            }
        }
    }
}


