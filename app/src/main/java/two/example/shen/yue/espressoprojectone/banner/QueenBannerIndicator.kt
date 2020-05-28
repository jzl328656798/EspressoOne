package two.example.shen.yue.espressoprojectone.banner

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/5/19 4:09 PM
 * Describe: Indicator
 */
class QueenBannerIndicator : View {
    private var number = 0
    private var position = 0
    private val paint = Paint()
    private var selectColor = Color.RED
    private var unSelectColor = Color.BLACK
    private var radius = 10f
    private var space = 20f

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        paint.style = Paint.Style.FILL
        paint.isAntiAlias = true

        selectColor = context.resources.getColor(R.color.colorAccent, null)
        unSelectColor = context.resources.getColor(R.color.colorPrimaryDark, null)

        radius = 10f.dp2px()
        space = 20f.dp2px()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val startPosition = width / 2 - (radius * 2 * number + space * (number - 1)) / 2
        canvas?.save()
        for (i in 0 until number) {
            if (i == position) {
                paint.color = selectColor
            } else {
                paint.color = unSelectColor
            }
            canvas?.drawCircle(startPosition + radius * (2 * i + 1) + i * space, (height / 2).toFloat(), radius, paint)
        }
        canvas?.restore()
    }

    fun setNumber(number: Int) {
        this.number = number
    }

    fun setPosition(position: Int) {
        this.position = position % number
        invalidate()
    }

    private fun Float.sp2px(): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return this * fontScale + 0.5f
    }

    private fun Float.dp2px(): Float {
        val fontScale = context.resources.displayMetrics.density
        return this * fontScale + 0.5f
    }
}