package com.example.rocketlaunch

import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

object GlideUtils {
    private const val TAG = "GlideUtils"
    fun loadRocketIcon(context: Context, url: String?, imageView: ImageView) {
        if (url == null) {
            Log.d(TAG, "url is null")
            return
        }
        val loadingIcon = CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            centerRadius = 30f
        }
        loadingIcon.start()
        Glide
            .with(context)
            .load(url)
            .placeholder(loadingIcon)
            .into(imageView)
    }
}