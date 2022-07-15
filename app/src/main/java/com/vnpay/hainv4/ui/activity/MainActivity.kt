package com.vnpay.hainv4.ui.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Build.VERSION.SDK
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.vnpay.hainv4.Const.Const

import com.vnpay.hainv4.R
import com.vnpay.hainv4.databinding.ActivityMainBinding
import com.vnpay.hainv4.ui.fragment.LoginFragment


import com.vnpay.hainv4.ui.fragment.LoginFragment.Companion.KEY_REGISTER
import com.vnpay.hainv4.ui.fragment.LoginFragment.Companion.KEY_USERNAME
import com.vnpay.hainv4.ui.fragment.LoginFragment.Companion.SHARED_PREF
import com.vnpay.hainv4.ui.fragment.NotificationLayout
import okhttp3.internal.notify
import java.util.prefs.Preferences


class MainActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = this.applicationContext.getSharedPreferences(SHARED_PREF, MODE_PRIVATE)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_hos_main_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.giftFragment, R.id.notifyFragment, R.id.shopFragment,R.id.settingFragment),
            binding.drawerMain
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
        binding.navPage.setupWithNavController(navController)

        val isDarkMode = preferences.getBoolean(Const.DARK_MODE,false)
        if (isDarkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//            setTheme(R.style.Theme_HNTrainning)
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//            setTheme(R.style.Theme_HNTrainning)
        }

        val username = preferences.getString(KEY_USERNAME, "")
        binding.tvUsername.text = "$username"
        binding.tvLogout.setOnClickListener {
            val editor = preferences.edit()
            editor.putString(Const.USER_NAME,username)
            editor.remove(Const.KEY_REMEMBER)
            editor.putBoolean(Const.KEY_REMEMBER,false)

            editor.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.animationView.setOnClickListener {
            pushNotification()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_nav_menu, menu)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerMain.isDrawerOpen(GravityCompat.END)) {
            binding.drawerMain.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun pushNotification() {
//        val remoteView = RemoteViews(packageName, R.layout.layout_notification)
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = Notification.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getString(R.string.contentTitle))
            .setContentText(getString(R.string.contentText)).setContentIntent(pendingIntent).setAutoCancel(true)
        // Add as notification
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "1",
                getString(R.string.notify),
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = R.color.purple_200
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.project_id)
            manager.createNotificationChannel(notificationChannel)
            builder.setChannelId(notificationChannel.id)
        }
        manager.notify(1, builder.build())
    }


}

