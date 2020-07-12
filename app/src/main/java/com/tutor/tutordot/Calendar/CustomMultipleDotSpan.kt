package com.tutor.tutordot.Calendar

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan
import com.prolificinteractive.materialcalendarview.spans.DotSpan.DEFAULT_RADIUS

class CustomMultipleDotSpan : LineBackgroundSpan {
    private val radius : Float
        private var color = IntArray(0)

        constructor(){
            this.radius = DEFAULT_RADIUS
            this.color[0] = 0
    }

    constructor(color:Int){
        this.radius = DEFAULT_RADIUS
        this.color[0] = 0
    }

    constructor(radius:Float){
        this.radius = radius
        this.color[0] = 0
    }

    constructor(radius:Float, color:IntArray){
        this.radius = radius
        this.color = color
    }

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        text: CharSequence,
        start: Int,
        end: Int,
        lineNumber: Int
    ) {
        val total = if(color.size>2)3 else color.size
        var leftMost = (total -1) * -12

        for(i in 0 until total){
            val oldColor = paint.color
            if(color[i] != 0){
                paint.color = color[i]
            }
            canvas.drawCircle(((left + right) / 2 - leftMost).toFloat(), bottom + radius, radius, paint)
            paint.color = oldColor
            leftMost += 24
        }
    }
}