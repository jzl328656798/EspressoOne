package two.example.shen.yue.espressoprojectone.test7

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/4/10 3:57 PM
 * Describe: Test7View1
 */
class Test7View1 : View {

    private val text1 = "您的爱车暂无SC功能"
    private val text2 = "以下为"
    private val text3 = "体验功能"
    private val text4 = "沪ABB233"

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val carPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint()

    private var textHeight = 0f
    private var carHeight = 0f

    private var type = true

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    fun setData() {
        type = false
        invalidate()
    }

    fun setData1() {
        type = true
        invalidate()
    }

    private fun initPaint() {
        textPaint.textSize = 16f.sp2px()
        textPaint.letterSpacing = 0.1f
        textPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        val fontMetrics = textPaint.fontMetrics
        textHeight = fontMetrics.bottom - fontMetrics.top


        carPaint.textSize = 20f.sp2px()
        carPaint.isFakeBoldText = true
        carPaint.letterSpacing = 0.1f
        carPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        val fontMetricsCar = textPaint.fontMetrics
        carHeight = fontMetricsCar.bottom - fontMetricsCar.top

        linePaint.color = context.resources.getColor(R.color.color_99B5A36A, null)
        linePaint.isAntiAlias = true
        linePaint.style = Paint.Style.FILL
        linePaint.strokeWidth = 4f.dp2px()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, textHeight.toInt() * 3)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (type) {
            textPaint.isFakeBoldText = false
            canvas.drawText(text1, 0f, textHeight, textPaint)
            canvas.drawText(text2, 0f, 2f.dp2px() + textHeight * 2, textPaint)
            val text2Width = measureTextWidth(textPaint, text2)
            textPaint.isFakeBoldText = true
            canvas.drawText(text3, text2Width, 2f.dp2px() + textHeight * 2, textPaint)
            val text3Width = measureTextWidth(textPaint, text3)
            canvas.drawLine(text2Width, 2f.dp2px() + textHeight * 2, text2Width + text3Width, 2f.dp2px() + textHeight * 2, linePaint)
        } else {
            canvas.drawText(text4, 0f, carHeight, carPaint)
        }

    }

    private fun measureTextWidth(paint: Paint, text: String): Float {
        return paint.measureText(text)
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