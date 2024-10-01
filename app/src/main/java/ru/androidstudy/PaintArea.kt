package ru.androidstudy

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

@SuppressLint("ClickableViewAccessibility")
class PaintArea(context: Context?, attrs: AttributeSet?) : View(context, attrs), View.OnTouchListener {
    private val paint = Paint()
    private val pointArrayList: ArrayList<Point> = ArrayList()

    init {
        this.setOnTouchListener(this)
        paint.color = Color.BLUE
        paint.isAntiAlias = true
        paint.strokeWidth = 0.5F
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val dot = Point()
        dot.x = event!!.x
        dot.y = event.y
        pointArrayList.add(dot)
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        for (point: Point in pointArrayList) {
            canvas!!.drawCircle(point.x, point.y, 10F, paint)
        }
    }

    private class Point {
        var x = 0.0F
        var y = 0.0F
    }

    fun cleanPaintArea() {
        pointArrayList.clear()
        invalidate()
    }

}