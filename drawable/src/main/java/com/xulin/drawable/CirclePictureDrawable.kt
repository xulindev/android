package com.xulin.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.max
import kotlin.math.min

/**
 * 圆图片 Drawable
 *
 * @author xulin
 */
class CirclePictureDrawable() : Drawable() {

    private val paint = Paint()

    private var matrix = Matrix()

    private lateinit var rect: RectF

    private var cx = 0f
    private var cy = 0f
    private var radius = 0f

    /**
     * 图片宽度
     */
    private var bitmapWidth = 0f

    /**
     * 图片高度
     */
    private var bitmapHeight = 0f

    init {
        paint.isAntiAlias = true
        paint.isDither = true
    }

    constructor(bitmap: Bitmap) : this() {
        bitmapWidth = bitmap.width.toFloat()
        bitmapHeight = bitmap.height.toFloat()
        paint.shader = BitmapShader(
            bitmap, Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
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
        val cr = min(w, h)
        cx = rect.left + w / 2f
        cy = rect.top + h / 2f
        radius = cr / 2f
        val scale = max(w / bitmapWidth, h / bitmapHeight)
        matrix.setScale(scale, scale)
        if (w > h) {
            matrix.postTranslate((w - cr) / 2f, 0f)
        } else if (h > w) {
            matrix.postTranslate(0f, (h - cr) / 2f)
        }
        paint.shader.setLocalMatrix(matrix)
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