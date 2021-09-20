package com.xulin.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.max

/**
 * 圆角图片 Drawable
 *
 * @author xulin
 */
class RoundRectPictureDrawable() : Drawable() {

    private val paint = Paint()

    private val path = Path()

    private var matrix = Matrix()

    private lateinit var rect: RectF

    private var radius: FloatArray = floatArrayOf(
        0f, 0f,
        0f, 0f,
        0f, 0f,
        0f, 0f
    )

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

    constructor(bitmap: Bitmap, radius: FloatArray) : this() {
        this.radius = radius
        bitmapWidth = bitmap.width.toFloat()
        bitmapHeight = bitmap.height.toFloat()
        paint.shader = BitmapShader(
            bitmap, Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
    }

    constructor(bitmap: Bitmap, radius: Float) : this(
        bitmap, floatArrayOf(
            radius, radius,
            radius, radius,
            radius, radius,
            radius, radius
        )
    )

    constructor(bitmap: Bitmap) : this(
        bitmap, floatArrayOf(
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
        val w = rect.right - rect.left
        val h = rect.bottom - rect.top
        val scale = max(w / bitmapWidth, h / bitmapHeight)
        matrix.setScale(scale, scale)
        paint.shader.setLocalMatrix(matrix)
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