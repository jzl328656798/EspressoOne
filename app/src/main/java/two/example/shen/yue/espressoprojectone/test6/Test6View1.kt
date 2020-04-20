package two.example.shen.yue.espressoprojectone.test6

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/3/24 4:18 PM
 * Describe: ScNewestReportView
 */
class Test6View1 : View {

    private val annulusWidth = 8f

    private val textPaint = Paint()

    //里程圆心
    private var circleX = 0f
    private var circleY = 0f

    private val circlePaint = Paint()

    //总里程半径
    private var totalDistanceRadius = 0f
    private val totalDistanceRectF = RectF()

    //可用半径
    private var usableRadius = 0f
    private val usableRectF = RectF()

    //使用半径
    private var usedRadius = 0f
    private val usedRectF = RectF()

    private var hour = "0"
    private var min = "0"

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {

        circlePaint.isAntiAlias = true
        circlePaint.strokeWidth = annulusWidth.sp2px()
        circlePaint.style = Paint.Style.STROKE

        textPaint.isAntiAlias = true

    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        initData()
    }

    private fun initData() {
        circleX = width / 2f
        circleY = height / 2f

        totalDistanceRadius = height / 4f
        usableRadius = totalDistanceRadius + annulusWidth.dp2px()
        usedRadius = usableRadius + annulusWidth.dp2px()


        totalDistanceRectF.set(circleX - totalDistanceRadius, circleY - totalDistanceRadius,
                circleX + totalDistanceRadius, circleY + totalDistanceRadius)

        usableRectF.set(circleX - usableRadius, circleY - usableRadius,
                circleX + usableRadius, circleY + usableRadius)

        usedRectF.set(circleX - usedRadius, circleY - usedRadius,
                circleX + usedRadius, circleY + usedRadius)


    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        circlePaint.color = context.resources.getColor(R.color.color_E9ECEF, null)
        canvas.drawArc(totalDistanceRectF, 360f, 360f, false, circlePaint)

        circlePaint.color = context.resources.getColor(R.color.color_B5A36A, null)
        canvas.drawArc(usableRectF, -90f, 115f, false, circlePaint)

        circlePaint.color = context.resources.getColor(R.color.color_0FEEA2, null)
        canvas.drawArc(usedRectF, -90f, 90f, false, circlePaint)

        textPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        textPaint.textSize = 14f.sp2px()

        when {
            "0" == hour -> {
                canvas.drawText("${min}m", circleX - measureTextWidth(textPaint, "${min}m") / 2, circleY - 2f.dp2px(), textPaint)
            }
            "0" == min -> {
                canvas.drawText("${hour}h", circleX - measureTextWidth(textPaint, "${hour}h") / 2, circleY - 2f.dp2px(), textPaint)
            }
            else -> {
                canvas.drawText("${hour}h${min}m", circleX - measureTextWidth(textPaint, "${hour}h${min}m") / 2, circleY - 2f.dp2px(), textPaint)
            }
        }


        textPaint.color = context.resources.getColor(R.color.color_C8C8C8, null)
        textPaint.textSize = 12f.sp2px()
        canvas.drawText("行驶总耗时", circleX - measureTextWidth(textPaint, "行驶总耗时") / 2, circleY + 18f.dp2px(), textPaint)

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

    fun startAnimate(hourValue: Int, minValue: Int) {
        val animatorSet = AnimatorSet()
        val animatorHour = ValueAnimator.ofInt(0, hourValue)
        animatorHour.duration = 500
        animatorHour.addUpdateListener {
            val curValue = it.animatedValue as Int
            hour = curValue.toString()
            invalidate()
        }

        val animatorMin = ValueAnimator.ofInt(0, minValue)
        animatorMin.duration = 500
        animatorMin.addUpdateListener {
            val curValue = it.animatedValue as Int
            min = curValue.toString()
            invalidate()
        }

        animatorSet.play(animatorHour).with(animatorMin)
        animatorSet.start()

    }

}