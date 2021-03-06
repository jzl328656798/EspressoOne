package two.example.shen.yue.espressoprojectone.test4

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
class Test4ScNewestReportView4 : View {

    private val annulusWidth = 8f
    private val lineWidth = 1f
    //圆点半径
    private val dotRadius = 2f
    private val dotPaint = Paint()

    private val textPaint = Paint()
    private val numberPaint = Paint()

    //里程圆心
    private var mileageCenterX = 0f
    private var mileageCenterY = 0f
    //总里程半径
    private var totalDistanceRadius = 0f
    private val totalDistanceRectF = RectF()
    private val totalDistancePaint = Paint()
    private val totalDistanceLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var totalDistanceLineAx = 0f
    private var totalDistanceLineAy = 0f
    private var totalDistanceLineBx = 0f
    private var totalDistanceLineBy = 0f
    private var totalDistanceLineCx = 0f
    private var totalDistanceLineCy = 0f

    //使用半径
    private var usedRadius = 0f
    private val usedRectF = RectF()
    private val usedPaint = Paint()

    private var text1 = "0"
    private var text2 = "0"
    private var text3 = "0"


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {

        dotPaint.color = context.resources.getColor(R.color.color_E9ECEF, null)
        dotPaint.isAntiAlias = true

        totalDistanceLinePaint.setARGB(255, 233, 236, 239)
        totalDistanceLinePaint.isAntiAlias = true
        totalDistanceLinePaint.strokeWidth = lineWidth.sp2px()
        totalDistanceLinePaint.style = Paint.Style.STROKE

        totalDistancePaint.setARGB(255, 233, 236, 239)
        totalDistancePaint.isAntiAlias = true
        totalDistancePaint.strokeWidth = annulusWidth.sp2px()
        totalDistancePaint.style = Paint.Style.STROKE

        usedPaint.setARGB(255, 181, 163, 109)
        usedPaint.isAntiAlias = true
        usedPaint.strokeWidth = annulusWidth.sp2px()
        usedPaint.style = Paint.Style.STROKE

        textPaint.color = context.resources.getColor(R.color.color_C8C8C8, null)
        textPaint.isAntiAlias = true
        textPaint.textSize = 12f.sp2px()

        numberPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        numberPaint.isAntiAlias = true
        numberPaint.isFakeBoldText = true
        numberPaint.textSize = 14f.sp2px()

    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        initData()
    }

    private fun initData() {
        mileageCenterX = width / 2f
        mileageCenterY = height / 2f

        totalDistanceRadius = width / 6f

        usedRadius = totalDistanceRadius + annulusWidth.dp2px()

        totalDistanceRectF.set(mileageCenterX - totalDistanceRadius, mileageCenterY - totalDistanceRadius,
                mileageCenterX + totalDistanceRadius, mileageCenterY + totalDistanceRadius)

        totalDistanceLineAx = mileageCenterX - totalDistanceRadius + 2 * annulusWidth.dp2px()
        totalDistanceLineAy = mileageCenterY - totalDistanceRadius + annulusWidth.dp2px()

        totalDistanceLineBx = totalDistanceLineAx - 60f.dp2px()
        totalDistanceLineBy = totalDistanceLineAy

        totalDistanceLineCx = totalDistanceLineBx - 15f.dp2px()
        totalDistanceLineCy = totalDistanceLineBy + 15f.dp2px()

        usedRectF.set(mileageCenterX - usedRadius, mileageCenterY - usedRadius,
                mileageCenterX + usedRadius, mileageCenterY + usedRadius)

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawArc(totalDistanceRectF, 360f, 360f, false, totalDistancePaint)
        canvas.drawLine(totalDistanceLineAx, totalDistanceLineAy, totalDistanceLineBx, totalDistanceLineBy, totalDistanceLinePaint)
        canvas.drawLine(totalDistanceLineBx, totalDistanceLineBy, totalDistanceLineCx, totalDistanceLineCy, totalDistanceLinePaint)
        canvas.drawCircle(totalDistanceLineCx, totalDistanceLineCy, dotRadius.dp2px(), dotPaint)
        canvas.drawText("${text1}km", totalDistanceLineCx - measureTextWidth(numberPaint, "${text1}km") / 2, mileageCenterY, numberPaint)
        canvas.drawText("行驶总里程", totalDistanceLineCx - measureTextWidth(numberPaint, "${text1}km") / 2, mileageCenterY + 20f.dp2px(), textPaint)

        canvas.drawText("${text2}h${text3}m", mileageCenterX - measureTextWidth(numberPaint, "${text2}h${text3}") / 2, mileageCenterY, numberPaint)
        canvas.drawText("SC使用总时长", mileageCenterX - measureTextWidth(textPaint, "SC使用总时长") / 2, mileageCenterY + 20f.dp2px(), textPaint)


        canvas.drawArc(usedRectF, -90f, 135f, false, usedPaint)

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

    fun startAnimate(value: Int) {
        val animator = ValueAnimator.ofInt(0, value)
        animator.duration = 500
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            text1 = (curValue * 1000).toString()
            text2 = (curValue * 10).toString()
            text3 = curValue.toString()
            invalidate()
        }
        animator.start()
    }

}