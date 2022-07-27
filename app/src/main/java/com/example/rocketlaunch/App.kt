package com.example.rocketlaunch

import android.app.Application
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import java.io.InputStream

class App : Application() {
    companion object {
        private const val TAG = "App"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "App onCreate")
        Glide.get(this).registry.replace(
            GlideUrl::class.java,
            InputStream::class.java, OkHttpUrlLoader.Factory(OkHttpSingleton.getInstance())
        )
    }
}