package com.xulin.drawable

import android.graphics.*
import android.graphics.drawable.Drawable

/**
 * 圆角 Drawable
 *
 * @author xulin
 */
class RoundRectDrawable() : Drawable() {

    private val paint = Paint()

    private val path = Path()

    private lateinit var rect: RectF

    private var radius: FloatArray = floatArrayOf(
        0f, 0f,
        0f, 0f,
        0f, 0f,
        0f, 0f
    )

    init {
        paint.isAntiAlias = true
        paint.isDither = true
    }

    constructor(color: Int, radius: FloatArray) : this() {
        this.radius = radius
        paint.color = color
    }

    constructor(color: Int, radius: Float) : this(
        color, floatArrayOf(
            radius, radius,
            radius, radius,
            radius, radius,
            radius, radius
        )
    )

    constructor(color: Int) : this(
        color, floatArrayOf(
            0f, 0f,
            0f, 0f,
            0f, 0f,
            0f, 0f
        )
    )

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        rect = RectF(
            left.toFloat(),
            top.toFloat(),
            right.toFloat(),
            bottom.toFloat()
        )
    }

    override fun draw(canvas: Canvas) {
        path.addRoundRect(
            rect,
            radius,
            Path.Direction.CW
        )
        canvas.drawPath(path, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
        invalidateSelf()
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }
}