package com.bosch.smartshopping.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.bosch.smartshopping.service.`interface`.UploadRepo
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus

open class MyBaseService : Service() , UploadRepo {

    var TAG = MyBaseService::class.java.name
    private var coroutineScope = CoroutineScope(Dispatchers.Default)
    private var exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "exception occured : $throwable")
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun uploadData() {
        runBlocking {
            coroutineScope.launch {
                var i = 0
                while(i<10) {
                    Thread.sleep(2000L)
                    Log.d(TAG, "Data uploaded $i")
                    EventBus.getDefault().postSticky("Data uploaded for image #$i")
                    i++
                }
                stopSelf()
            }
        }
    }

}
