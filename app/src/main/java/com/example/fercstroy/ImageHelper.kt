package com.example.fercstroy

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream

object ImageHelper {

    fun compressImage(drawable: Drawable): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val bitmap = (drawable as? BitmapDrawable)?.bitmap

        if (bitmap != null) {
            val targetSize = 256 * 1024 // 256 КБ в байтах
            var quality = 100 // Начальное значение качества

            // Бинарный поиск оптимального значения качества
            var low = 0
            var high = 100
            while (low <= high) {
                val mid = (low + high) / 2
                bitmap.compress(Bitmap.CompressFormat.JPEG, mid, outputStream)
                val size = outputStream.size()
                if (size <= targetSize) {
                    quality = mid
                    low = mid + 1
                } else {
                    high = mid - 1
                }
                outputStream.reset()
            }

            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        }

        return outputStream.toByteArray()
    }

    fun compressImage(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        val targetSize = 256 * 1024 // 256 КБ в байтах
        var quality = 100 // Начальное значение качества

        // Бинарный поиск оптимального значения качества
        var low = 0
        var high = 100
        while (low <= high) {
            val mid = (low + high) / 2
            bitmap.compress(Bitmap.CompressFormat.JPEG, mid, outputStream)
            val size = outputStream.size()
            if (size <= targetSize) {
                quality = mid
                low = mid + 1
            } else {
                high = mid - 1
            }
            outputStream.reset()
        }

        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)

        return outputStream.toByteArray()
    }

    fun getDefaultAvatar(context: Context) : Drawable {
        val drawableId = R.drawable.ava
        val drawable = ContextCompat.getDrawable(context, drawableId)
        return drawable!!
    }

}