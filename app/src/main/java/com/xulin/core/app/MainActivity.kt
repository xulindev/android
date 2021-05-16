package com.xulin.core.app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xulin.display.extension.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(Container(this))
    }

    class Container(context: Context?, attrs: AttributeSet? = null) : View(context, attrs) {

        val paint = Paint()

        val w by lazy {
            360f.dp
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            paint.color = 0xff00ff00.toInt()
            canvas?.drawRect(0f, 0f, w, w, paint)
            paint.color = 0xffffff00.toInt()
            canvas?.drawRect(w, w, w + w, w + w, paint)
        }
    }
}