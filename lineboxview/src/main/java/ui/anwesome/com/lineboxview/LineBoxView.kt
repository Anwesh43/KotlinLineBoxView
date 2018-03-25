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
    fun updateFromOtherThread(stopcb : () -> Unit) {
        postInvalidate()
    }
    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
    data class State (var prevScale : Float = 0f, var dir : Int = 0, var j : Int = 0) {
        val scales : Array<Float> = arrayOf(0f, 0f)
        fun update(stopcb : (Float) -> Unit) {
            scales[j] += dir * 0.1f
            if (Math.abs(scales[j] - prevScale) > 1) {
                scales[j] = prevScale + dir
                j += dir
                if (j == scales.size || j == -1) {
                    j -= dir
                    dir = 0
                    stopcb(prevScale)
                }
            }
        }
        fun startUpdating(startcb : () -> Unit) {
            if (dir == 0) {
                dir = 1 - 2 * prevScale.toInt()
                startcb()
            }
        }
    }
    data class LineBox(var i : Int) {
        val state : State = State()
        fun draw(canvas : Canvas, paint : Paint) {
            val w = canvas.width.toFloat()
            val h = canvas.height.toFloat()
            val BOX_SIZE = 2 * Math.min(w, h)/ 3
            val x = - BOX_SIZE/2
            val gap = (BOX_SIZE/2)
            canvas.save()
            canvas.translate(w/2, h/2)
            for (i in 0..1) {
                canvas.save()
                canvas.rotate(90f * i * state.scales[0])
                for (j in 0..2) {
                    var x_now = x + gap * j
                    canvas.drawLine(x_now * state.scales[1], -gap, x_now * state.scales[1], gap, paint)
                }
                canvas.restore()
            }
            canvas.restore()
        }
        fun update(stopcb : (Float) -> Unit) {
            state.update(stopcb)
        }
        fun startUpdating(startcb : () -> Unit) {
            state.startUpdating(startcb)
        }
    }
}