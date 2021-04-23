package com.bosch.smartshopping.activities

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.bosch.smartshopping.navigators.WelcomeNavigator
import com.bosch.smartshopping.service.SmartShoppingService
import com.bosch.smartshopping.viewmodel.WelcomeViewModel
import com.bosch.smartshopping.R
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeActivity : AppCompatActivity() , WelcomeNavigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        welcomeViewModel.setNavigator(this)
        updateViewModelData()
        setListeners()
    }

    private val TAG = WelcomeActivity::class.java.name
    private val serviceIntent : Intent by lazy {
        Intent(
            this,
            SmartShoppingService::class.java
        )
    }
    private val boundServiceIntent : Intent by lazy {
        Intent(
            this,
            SmartShoppingService::class.java
        )
    }
    private var service: SmartShoppingService? = null
    private val welcomeViewModel: WelcomeViewModel<WelcomeNavigator> by viewModel()


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun readMessageFromService( message: String) {
        Log.d(TAG, "Update from service : $message")
        welcomeViewModel.messageFRomService.postValue(message)
        welcomeViewModel.saveDataLocally(message)
    }

    private fun updateViewModelData() {
        welcomeViewModel.screenName.value = "MainActivity"
    }

    private fun setListeners() {
        welcomeViewModel.screenName.observe(this,
            Observer {
                Log.d(this.componentName.className, "screen $it")
            })
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        EventBus.getDefault().register(this)
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
    }

    override fun uploadData() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(
            serviceIntent
        )
        else startService(serviceIntent)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        stopService(serviceIntent)
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
        Log.d(TAG, "onStop")
    }
}