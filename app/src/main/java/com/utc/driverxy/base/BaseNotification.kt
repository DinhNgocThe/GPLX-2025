package com.apero.utils.base.noticaNotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.utc.driverxy.R
import com.utc.driverxy.presentation.theme.DriverXyColors

abstract class BaseNotification(protected val context: Context) {
    protected val PENDING_INTENT_FLAGS =
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    protected val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    abstract fun getChannelInfo(): NotificationChannelInfo

    abstract fun createNotificationBuilder(): Any

    abstract fun getNotificationId(): Int

    open fun getSmallIcon() = R.drawable.ic_logo

    open fun getIconColor(): Int = DriverXyColors.Primary.Primary1.value.toInt()

    open fun getPriority() = NotificationCompat.PRIORITY_HIGH

    open fun getImportance() = NotificationManager.IMPORTANCE_HIGH

    open fun lockscreenVisibility()  = Notification.VISIBILITY_PUBLIC

    open fun getCategory(): String? = null

    open fun getOngoing() = true

    open fun enableUseChronometer() = false

    open fun getContentIntent(): PendingIntent? = null

    open fun getFullScreenIntent(): PendingIntent? = null

    open fun getSmallRemoteViews(): RemoteViews? = null

    open fun getBigRemoteViews(): RemoteViews? = null

    open fun isVibrate() = true

    open fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelInfo = getChannelInfo()
            val channel = NotificationChannel(
                channelInfo.id,
                channelInfo.name,
                getImportance()
            ).apply {
                description = channelInfo.description
                setShowBadge(true)
                enableLights(true)
                lockscreenVisibility = lockscreenVisibility()
                if(isVibrate()){
                    enableVibration(true)
                    vibrationPattern = longArrayOf(0, 500, 200, 500)
                }
            }
            channel.setSound(null, null)
            notificationManager.createNotificationChannel(channel)
        }
    }

    protected fun drawableToIconCompat(bitmap: Bitmap?): IconCompat? {
        if (bitmap == null) return null
        return IconCompat.createWithBitmap(bitmap)
    }

    protected fun drawableToIcon(bitmap: Bitmap?): Icon? {
        if (bitmap == null) return null
        return Icon.createWithBitmap(bitmap)
    }

//    private fun drawableToBitmap(drawable: Drawable): Bitmap {
//        if (drawable is BitmapDrawable) {
//            drawable.bitmap?.let { return it }
//        }
//
//        val width = if (drawable.intrinsicWidth > 0) drawable.intrinsicWidth else 96
//        val height = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else 96
//
//        val bitmap = createBitmap(width, height)
//        val canvas = Canvas(bitmap)
//        drawable.setBounds(0, 0, canvas.width, canvas.height)
//        drawable.draw(canvas)
//
//        return bitmap
//    }

    open fun show() {
        createNotificationChannel()
        val builder = createNotificationBuilder()
        if (builder is NotificationCompat.Builder) {
            notificationManager.notify(
                getNotificationId(),
                builder.build()
            )
        } else if (builder is Notification.Builder) {
            notificationManager.notify(
                getNotificationId(),
                builder.build()
            )
        }
    }

    open fun createNotification(): Notification {
        createNotificationChannel()
        return when (val builder = createNotificationBuilder()) {
            is NotificationCompat.Builder -> {
                builder.build()
            }

            is Notification.Builder -> {
                builder.build()
            }

            else -> {
                throw IllegalArgumentException("Unknown builder type")
            }
        }
    }

    fun dismissNotification() {
        notificationManager.cancel(getNotificationId())
    }
}

data class NotificationChannelInfo(
    val id: String,
    val name: String,
    val description: String
)

data class CallNotificationInfo(
    val notification: Notification,
    val notificationId: Int
)