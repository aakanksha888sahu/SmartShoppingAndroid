package com.bosch.smartshopping.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.bosch.smartshopping.service.`interface`.UploadRepo
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus

open class BaseService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }


}
