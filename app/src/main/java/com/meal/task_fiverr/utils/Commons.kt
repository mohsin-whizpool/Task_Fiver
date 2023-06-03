package com.meal.task_fiverr.utils

import android.app.NotificationManager
import android.content.Context
import android.graphics.Point
import android.view.MotionEvent
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.core.app.NotificationCompat
import com.meal.task_fiverr.R

object Commons
{
    fun genericSelector(view: View)
    {
        var p: Point? = null
        view.setOnTouchListener { v: View?, event: MotionEvent ->
            when (event.action)
            {
                MotionEvent.ACTION_DOWN ->
                {
                    val alpha = AlphaAnimation(0.5f, 0.5f)
                    alpha.duration = 0 // Make animation instant
                    alpha.fillAfter = true // Tell it to persist after the animation ends
                    // And then on your layout
                    view.startAnimation(alpha)
                    p = Point(event.x.toInt(), event.y.toInt())
                }

                MotionEvent.ACTION_UP ->
                {
                    val f =
                        Point(event.x.toInt(), event.y.toInt())
                    if (Math.abs(f.x - p!!.x) < 25 && Math.abs(
                            f.x - p!!.x
                        ) < 25
                    ) {
                        view.performClick()
                    }
                    view.clearAnimation()
                }
            }
            true
        }
    }

    fun NotificationManager.sendNotification( applicationContext: Context)
    {
        // Build the notification
        val builder = NotificationCompat.Builder(
            applicationContext,
            "0"
        )
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Fiverr Task")
            .setContentText("This is Fiverr Task Notification")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        // Deliver the notification
        notify(0, builder.build())
    }
}