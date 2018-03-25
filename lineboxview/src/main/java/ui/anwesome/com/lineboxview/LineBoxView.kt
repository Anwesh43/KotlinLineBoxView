package ui.anwesome.com.lineboxview

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.graphics.Paint
import android.graphics.Canvas

/**
 * Created by anweshmishra on 26/03/18.
 */
class LineBoxView(ctx : Context) : View(ctx) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas : Canvas) {

    }
    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}