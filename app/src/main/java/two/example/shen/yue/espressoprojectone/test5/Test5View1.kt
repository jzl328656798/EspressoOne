package two.example.shen.yue.espressoprojectone.test5

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/31 4:53 PM
 * Describe: Test5View1
 */
class Test5View1 : View {

    var progress = 50f
    val paint = Paint()
    private val arcRectF = RectF()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {
        paint.color = context.resources.getColor(R.color.colorAccent, null)
        paint.isAntiAlias = true
        paint.strokeWidth = 10f

        arcRectF.set(0f, 0f, 500f, 500f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(arcRectF, 135f, progress * 2.7f, true, paint);
    }

}