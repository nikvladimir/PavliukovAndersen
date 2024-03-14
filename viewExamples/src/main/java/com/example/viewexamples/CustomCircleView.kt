package com.example.viewexamples

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes


class CustomCircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var circleRadius = 0f
    private var circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        context.withStyledAttributes(attrs, R.styleable.CustomCircleView) {
            circleRadius = getDimension(R.styleable.CustomCircleView_circleRadius, 0f)
        }

        circlePaint.color = ContextCompat.getColor(context, android.R.color.background_dark)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width / 2f, height / 2f, circleRadius, circlePaint)
    }


}