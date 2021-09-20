package com.xulin.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.min

/**
 * 圆 Drawable
 *
 * @author xulin
 */
class CircleDrawable() : Drawable() {

    private val paint = Paint()

    private lateinit var rect: RectF

    private var cx = 0f
    private var cy = 0f
    private var radius = 0f

    init {
        paint.isAntiAlias = true
        paint.isDither = true
    }

    constructor(color: Int) : this() {
        paint.color = color
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        rect = RectF(
            left.toFloat(),
            top.toFloat(),
            right.toFloat(),
            bottom.toFloat()
        )
        val w = rect.right - rect.left
        val h = rect.bottom - rect.top
        cx = rect.left + w / 2f
        cy = rect.top + h / 2f
        radius = min(w, h) / 2f
    }

    override fun draw(canvas: Canvas) {
        canvas.drawCircle(cx, cy, radius, paint)
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