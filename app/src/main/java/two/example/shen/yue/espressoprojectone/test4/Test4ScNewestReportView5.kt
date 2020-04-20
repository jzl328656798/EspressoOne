package two.example.shen.yue.espressoprojectone.test4

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/3/24 4:18 PM
 * Describe: ScNewestReportView
 */
class Test4ScNewestReportView5 : View {

    private val textPaint = Paint()
    private val speedPaint = Paint()
    private val imaginaryLinePaint = Paint()
    private val totalDistancePaint = Paint()
    private val scDistancePaint = Paint()
    private val histogramLinePaint = Paint()

    private var speedSpace = 30f
    private var topSpace = 30f

    private var height1 = 0f
    private var height2 = 0f
    private var text = "0"

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {

        speedPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        speedPaint.isAntiAlias = true
        speedPaint.isFakeBoldText = true
        speedPaint.textSize = 18f.sp2px()

        imaginaryLinePaint.color = context.resources.getColor(R.color.color_F0F2F4, null)
        imaginaryLinePaint.style = Paint.Style.STROKE
        imaginaryLinePaint.isAntiAlias = true
        imaginaryLinePaint.strokeWidth = 1f.dp2px()
        imaginaryLinePaint.pathEffect = DashPathEffect(floatArrayOf(5f.dp2px(), 10f.dp2px()), 0f)

        totalDistancePaint.isAntiAlias = true
        totalDistancePaint.style = Paint.Style.FILL

        scDistancePaint.isAntiAlias = true
        scDistancePaint.style = Paint.Style.FILL

        histogramLinePaint.isAntiAlias = true
        histogramLinePaint.style = Paint.Style.FILL
        histogramLinePaint.strokeWidth = 1f.dp2px()

        textPaint.color = context.resources.getColor(R.color.color_FFFFFF, null)
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = 10f.sp2px()
        textPaint.textAlign = Paint.Align.CENTER

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        drawSpeed(canvas)
        drawSpeedYLine(canvas)
    }

    private fun drawSpeed(canvas: Canvas) {
        speedPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        speedPaint.textSize = 18f.sp2px()
        canvas.drawText("平均车速", 0f, topSpace.dp2px(), speedPaint)
        val left = measureTextWidth(speedPaint, "平均车速")
        speedPaint.textSize = 14f.sp2px()
        canvas.drawText("km/h", left + 4f.dp2px(), topSpace.dp2px(), speedPaint)
    }

    private fun drawSpeedYLine(canvas: Canvas) {
        speedPaint.color = context.resources.getColor(R.color.color_C8C8C8, null)
        speedPaint.textSize = 12f.sp2px()
        val speedWidth = measureTextWidth(speedPaint, "999")

        val list = ArrayList<String>()
        list.add("160")
        list.add("120")
        list.add("80")
        list.add("40")
        list.add("0")

        list.forEachIndexed { index, string ->
            val textY = (topSpace + speedSpace * (index + 1)).dp2px()
            canvas.drawText(string, (speedWidth - measureTextWidth(speedPaint, string)) / 2, textY, speedPaint)
            drawImaginaryLine(canvas, speedWidth + 16f.dp2px(), textY)
        }

        drawSpeedText(canvas, (topSpace + speedSpace * 6f).dp2px())
        drawHistogram(canvas, (topSpace + speedSpace * 5f).dp2px())
        drawHistogramLine(canvas, (topSpace + speedSpace * 5f).dp2px())
    }

    private fun drawHistogram(canvas: Canvas, y: Float) {
        val totalDistanceRectF = RectF()
        totalDistanceRectF.set(width / 2.6f - 5f.dp2px(), y - height1, width / 2.6f + 5f.dp2px(), y)

        val histogramShader = LinearGradient(totalDistanceRectF.left, totalDistanceRectF.top,
                totalDistanceRectF.right, totalDistanceRectF.bottom,
                context.resources.getColor(R.color.color_4DB5A36A, null),
                context.resources.getColor(R.color.color_00B5A36A, null),
                Shader.TileMode.CLAMP)
        totalDistancePaint.shader = histogramShader
        canvas.drawRect(totalDistanceRectF, totalDistancePaint)

        val scDistanceRectF = RectF()
        scDistanceRectF.set(width / 1.5f - 5f.dp2px(), y - height2, width / 1.5f + 5f.dp2px(), y)

        val scShader = LinearGradient(scDistanceRectF.left, scDistanceRectF.top,
                scDistanceRectF.right, scDistanceRectF.bottom,
                context.resources.getColor(R.color.color_4D0FEEA2, null),
                context.resources.getColor(R.color.color_000FEEA2, null),
                Shader.TileMode.CLAMP)
        scDistancePaint.shader = scShader
        canvas.drawRect(scDistanceRectF, scDistancePaint)
    }

    private fun drawHistogramText(canvas: Canvas, text: String, rectF: RectF) {
        val fontMetrics = textPaint.fontMetrics
        val distance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
        val baseline: Float = rectF.centerY() + distance
        canvas.drawText(text, rectF.centerX(), baseline, textPaint)
    }

    private fun drawHistogramLine(canvas: Canvas, y: Float) {
        histogramLinePaint.color = context.resources.getColor(R.color.color_B5A36A, null)
        canvas.drawLine(width / 2.6f - 7f.dp2px(), y - height1, width / 2.6f + 7f.dp2px(), y - height1, histogramLinePaint)

        val totalDistanceRectF = RectF()
        totalDistanceRectF.set(width / 2.6f - 13f.dp2px(), y - height1 - 19f.dp2px(), width / 2.6f + 13f.dp2px(), y - height1 - 3f.dp2px())
        canvas.drawRect(totalDistanceRectF, histogramLinePaint)
        drawHistogramText(canvas, text, totalDistanceRectF)

        histogramLinePaint.color = context.resources.getColor(R.color.color_0FEEA2, null)
        canvas.drawLine(width / 1.5f - 7f.dp2px(), y - height2, width / 1.5f + 7f.dp2px(), y - height2, histogramLinePaint)

        val scDistanceRectF = RectF()
        scDistanceRectF.set(width / 1.5f - 13f.dp2px(), y - height2 - 19f.dp2px(), width / 1.5f + 13f.dp2px(), y - height2 - 3f.dp2px())
        canvas.drawRect(scDistanceRectF, histogramLinePaint)
        drawHistogramText(canvas, text, scDistanceRectF)
    }

    private fun drawImaginaryLine(canvas: Canvas, x: Float, y: Float) {
        val path = Path()
        path.moveTo(x, y - 4f.dp2px())
        path.lineTo(width.toFloat(), y - 4f.dp2px())
        canvas.drawPath(path, imaginaryLinePaint)
    }

    private fun drawSpeedText(canvas: Canvas, y: Float) {
        canvas.drawText("总里程", width / 2.6f - measureTextWidth(speedPaint, "总里程") / 2, y, speedPaint)
        canvas.drawText("使用SC行程", width / 1.5f - measureTextWidth(speedPaint, "使用SC行程") / 2, y, speedPaint)
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

    fun startAnimate(value: Float) {
        val animator = ValueAnimator.ofFloat(0f, value)
        animator.duration = 500
        animator.addUpdateListener {
            val curValue = it.animatedValue as Float

            height1 = curValue
            height2 = curValue
            text = curValue.toInt().toString()

            invalidate()
        }
        animator.start()
    }

}