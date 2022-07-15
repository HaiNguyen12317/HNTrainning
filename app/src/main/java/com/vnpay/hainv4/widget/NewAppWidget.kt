package com.vnpay.hainv4.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.RemoteViews
import com.vnpay.hainv4.Const.Const
import com.vnpay.hainv4.R
import com.vnpay.hainv4.ui.activity.LoginActivity

/**
 * Implementation of App Widget functionality.
 */
class NewAppWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
//        View.OnClickListener {
//            when (it.id) {
//                R.id.appwidget_text -> {
//                    Log.d("anhhai","click")
//                    val goToDetails = Intent(context?.applicationContext, LoginActivity::class.java)
//                    goToDetails.putExtra(Const.REGISTER, true)
//                    goToDetails.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                    context.startActivity(goToDetails)
//                }
//            }
//        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
//        View.OnClickListener {
//            when (it.id) {
//                R.id.appwidget_text -> {
                    Log.d("anhhai","click")
                    val goToDetails = Intent(context?.applicationContext, LoginActivity::class.java)
                    goToDetails.putExtra(Const.REGISTER, true)
                    goToDetails.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context?.startActivity(goToDetails)
                }
//            }
//        }
//    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val remoteViews = RemoteViews(context.packageName, R.layout.new_app_widget)
    val intent = Intent(context.applicationContext, LoginActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    intent.putExtra(Const.REGISTER, true)
    context.startActivity(intent)
    val pendingIntent: PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        PendingIntent.getActivity(
            context.applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    } else TODO("VERSION.SDK_INT < M")


    remoteViews.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent)
    remoteViews.setOnClickFillInIntent(R.id.appwidget_text,intent)
    appWidgetManager.updateAppWidget(appWidgetId, remoteViews)


}