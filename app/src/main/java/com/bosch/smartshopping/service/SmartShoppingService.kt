package com.bosch.smartshopping.service

import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.bosch.smartshopping.constants.Constants
import com.bosch.smartshopping.service.`interface`.UploadRepo
import kotlinx.coroutines.*
import org.greenrobot.eventbus.EventBus

class SmartShoppingService : BaseService(), UploadRepo {

    var TAG = SmartShoppingService::class.java.name
    private var coroutineScope = CoroutineScope(Dispatchers.Default)
    private var exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, "exception occured : $throwable")
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
        Log.d(TAG, "service onBind")
    }


    override fun onCreate() {
        super.onCreate()
        TAG = SmartShoppingService::class.java.name
        Log.d(TAG, "service created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "service onStartCommand")
        val serviceStart = super.onStartCommand(intent, flags, startId)
        val notification = NotificationCompat.Builder(this, Constants.CHANNEL_ID)
            .setContentTitle("smartshopping Service")
            .setContentText("smartshopping service is runnning")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        startForeground(DEMO_SERVICE_ID, notification)
        uploadData()
        return serviceStart
    }

    override fun onDestroy() {
        Log.d(TAG, "Service destroyed")
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        Log.d(TAG, "service on TaskRemoved")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "Service on Unbind")
        return super.onUnbind(intent)
    }

    override fun uploadData() {
        runBlocking {
            coroutineScope.launch {
                var i = 0
                while (i < 10) {
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

const val DEMO_SERVICE_ID = 1
