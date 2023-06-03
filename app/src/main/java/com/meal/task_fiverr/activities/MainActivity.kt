package com.meal.task_fiverr.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.meal.task_fiverr.R
import com.meal.task_fiverr.databinding.ActivityMainBinding
import com.meal.task_fiverr.utils.Commons
import com.meal.task_fiverr.utils.Commons.sendNotification


class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        Commons.genericSelector(binding.btnSayHello)
        Commons.genericSelector(binding.btnSendNotification)

        binding.btnSayHello.setOnClickListener {
            typeText(getString(R.string.hello_world))
        }

        binding.btnSendNotification.setOnClickListener {
            sendNotification()
        }

    }

    private val CHANNEL_ID = "0"
    private fun createNotificationChannel()
    {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            packageName,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }

    private fun sendNotification()
    {
        createNotificationChannel()
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(applicationContext)
    }


    private fun typeText(text: String) {
        var index = 0
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                binding.textView.text = text.substring(0, index++)
                if (index <= text.length) {
                    handler.postDelayed(this, 100)
                }
            }
        }
        handler.postDelayed(runnable, 100)
    }
}