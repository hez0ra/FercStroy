// CustomMapView.kt
package com.example.fercstroy

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.yandex.mapkit.mapview.MapView

class CustomMapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MapView(context, attrs, defStyleAttr) {

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Обрабатываем касания карты, чтобы предотвратить скролл родительского ScrollView
        parent.requestDisallowInterceptTouchEvent(true)
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        // Обрабатываем касания карты, чтобы предотвратить скролл родительского ScrollView
        parent.requestDisallowInterceptTouchEvent(true)
        return super.onInterceptTouchEvent(event)
    }
}
