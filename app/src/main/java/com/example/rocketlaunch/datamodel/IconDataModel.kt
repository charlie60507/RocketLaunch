package com.example.rocketlaunch.datamodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executor

class IconDataModel {
    fun getRemoteIcon(executor: Executor, src:String, callback: (Bitmap) -> Unit) {
        executor.execute {
            val url = URL(src)
            val connection: HttpURLConnection =
                url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            callback(BitmapFactory.decodeStream(input))
        }
    }
}
