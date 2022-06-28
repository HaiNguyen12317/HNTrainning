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
import android.view.Menu
import android.view.MenuItem
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import com.vnpay.hainv4.ui.fragment.HomeFragment
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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_hos_main_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.giftFragment, R.id.notifyFragment, R.id.shopFragment),
            binding.drawerMain
        )
//        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
        binding.navPage.setupWithNavController(navController)
        preferences = applicationContext.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        val username = preferences.getString(KEY_USERNAME, "")

        binding.tvUsername.text = "$username"

        binding.tvLogout.setOnClickListener {

            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
        binding.animationView.setOnClickListener {
//            getNotification()
//            Toast.makeText(this, "click", Toast.LENGTH_LONG).show()
            pushNotification()
//            createChannel()
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
        val remoteView = RemoteViews(packageName, R.layout.layout_notification)
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = Notification.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Notifications Demo")
            .setContentText("This is a test notification").setContentIntent(pendingIntent).setAutoCancel(true)
        // Add as notification
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "1",
                "notify",
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setCustomContentView(remoteView)
        }


        manager.notify(1, builder.build())
    }

}

