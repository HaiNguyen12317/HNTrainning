package com.vnpay.hainv4.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.vnpay.hainv4.Const.Const
import com.vnpay.hainv4.databinding.ActivityLoginBinding
import com.vnpay.hainv4.ui.fragment.LoginFragment.Companion.SHARED_PREF
import com.vnpay.hainv4.ui.fragment.LoginFragmentDirections
import java.util.prefs.Preferences


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = applicationContext.getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        val isRemembered = sharedPreferences.getBoolean(Const.KEY_REMEMBER, false)
        if (isRemembered) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val isDarkMode = sharedPreferences.getBoolean(Const.DARK_MODE,false)
        if (isDarkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}