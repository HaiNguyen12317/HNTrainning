package com.vnpay.hainv4.ui.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.vnpay.hainv4.R
import com.vnpay.hainv4.databinding.ActivityMainBinding
import com.vnpay.hainv4.ui.fragment.LoginFragment.Companion.KEY_USERNAME
import com.vnpay.hainv4.ui.fragment.LoginFragment.Companion.SHARED_PREF
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

//        val service = MyFireBaseService()
//        service.sendNotification("{\n" +
//                "  \"message\":{\n" +
//                "    \"token\":\"bk3RNwTe3H0:CI2k_HHwgIpoDKCIZvvDMExUdFQ3P1...\",\n" +
//                "    \"notification\":{\n" +
//                "      \"title\":\"Portugal vs. Denmark\",\n" +
//                "      \"body\":\"great match!\"\n" +
//                "    }\n" +
//                "  }\n" +
//                "}")



        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_hos_main_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.giftFragment,R.id.notifyFragment,R.id.shopFragment),
            binding.drawerMain
        )
//        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)

        preferences = applicationContext.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE)

        val username = preferences.getString(KEY_USERNAME, "")

        binding.tvUsername.text =  "$username"

        binding.tvLogout.setOnClickListener {

            val editor = preferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.drawer_nav_menu,menu)
        return true
    }
    override fun onBackPressed() {
        if (binding.drawerMain.isDrawerOpen(GravityCompat.END)) {
            binding.drawerMain.closeDrawer(GravityCompat.END)
        } else {
            super.onBackPressed()
        }
    }
}


