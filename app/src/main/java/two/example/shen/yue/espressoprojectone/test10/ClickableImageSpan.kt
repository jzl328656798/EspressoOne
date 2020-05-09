package two.example.shen.yue.espressoprojectone.test10

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan
import android.view.View

/**
 * Author: Queen
 * Date: 2020/4/28 10:41 AM
 * Describe: ClickableImageSpan
 */
abstract class ClickableImageSpan(drawable: Drawable) : ImageSpan(drawable) {

    abstract fun onClick(view: View)


    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
//        super.draw(canvas, text, start, end, x, top, y, bottom, paint)
        val b = drawable
        val fm = paint.fontMetricsInt


        val transY = (y + fm.descent + y + fm.ascent) / 2 - b.bounds.bottom / 2
        canvas.save()
        canvas.translate(x, transY.toFloat())
        b.draw(canvas)
        canvas.restore()

    }
}