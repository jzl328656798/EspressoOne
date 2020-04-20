package two.example.shen.yue.espressoprojectone.test4

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
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
class Test4ScNewestReportView1 : View {

    private val annulusWidth = 8f
    private val lineWidth = 1f
    //圆点半径
    private val dotRadius = 2f
    private val dotPaint = Paint()

    private val textPaint = Paint()
    private val numberPaint = Paint()
    private val speedPaint = Paint()
    private val imaginaryLinePaint = Paint()

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

    private val usedLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var usedLineAx = 0f
    private var usedLineAy = 0f
    private var usedLineBx = 0f
    private var usedLineBy = 0f
    private var usedLineCx = 0f
    private var usedLineCy = 0f

    //可用半径
    private var usableRadius = 0f
    private val usableRectF = RectF()
    private val usablePaint = Paint()
    //使用半径
    private var usedRadius = 0f
    private val usedRectF = RectF()
    private val usedPaint = Paint()

    private var text = "100"


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {

        dotPaint.color = context.resources.getColor(R.color.color_E9ECEF, null)
        dotPaint.isAntiAlias = true

        speedPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        speedPaint.isAntiAlias = true
        speedPaint.isFakeBoldText = true
        speedPaint.textSize = 18f.sp2px()

        imaginaryLinePaint.color = context.resources.getColor(R.color.color_F0F2F4, null)
        imaginaryLinePaint.style = Paint.Style.STROKE
        imaginaryLinePaint.isAntiAlias = true
        imaginaryLinePaint.strokeWidth = 1f.dp2px()
        imaginaryLinePaint.pathEffect = DashPathEffect(floatArrayOf(5f.dp2px(), 10f.dp2px()), 0f)

        totalDistanceLinePaint.setARGB(255, 233, 236, 239)
        totalDistanceLinePaint.isAntiAlias = true
        totalDistanceLinePaint.strokeWidth = lineWidth.sp2px()
        totalDistanceLinePaint.style = Paint.Style.STROKE

        totalDistancePaint.setARGB(255, 233, 236, 239)
        totalDistancePaint.isAntiAlias = true
        totalDistancePaint.strokeWidth = annulusWidth.sp2px()
        totalDistancePaint.style = Paint.Style.STROKE

        usablePaint.setARGB(255, 181, 163, 109)
        usablePaint.isAntiAlias = true
        usablePaint.strokeWidth = annulusWidth.sp2px()
        usablePaint.style = Paint.Style.STROKE

        usedPaint.color = context.resources.getColor(R.color.color_0FEEA2, null)
        usedPaint.isAntiAlias = true
        usedPaint.strokeWidth = annulusWidth.sp2px()
        usedPaint.style = Paint.Style.STROKE

        usedLinePaint.color = context.resources.getColor(R.color.color_0FEEA2, null)
        usedLinePaint.isAntiAlias = true
        usedLinePaint.strokeWidth = lineWidth.sp2px()
        usedLinePaint.style = Paint.Style.STROKE

        textPaint.color = context.resources.getColor(R.color.color_C8C8C8, null)
        textPaint.isAntiAlias = true
        textPaint.textSize = 12f.sp2px()

        numberPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        numberPaint.isAntiAlias = true
        numberPaint.isFakeBoldText = true
        numberPaint.textSize = 16f.sp2px()

    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        initData()
    }

    private fun initData() {
        mileageCenterX = width / 2f
        mileageCenterY = height / 2f

        totalDistanceRadius = width / 6f

        usableRadius = totalDistanceRadius + annulusWidth.dp2px()
        usedRadius = usableRadius + annulusWidth.dp2px()

        //mileageCenterY = usedRadius + 2 * annulusWidth.dp2px()

        totalDistanceRectF.set(mileageCenterX - totalDistanceRadius, mileageCenterY - totalDistanceRadius,
                mileageCenterX + totalDistanceRadius, mileageCenterY + totalDistanceRadius)

        totalDistanceLineAx = mileageCenterX - totalDistanceRadius + 2 * annulusWidth.dp2px()
        totalDistanceLineAy = mileageCenterY - totalDistanceRadius + annulusWidth.dp2px()

        totalDistanceLineBx = totalDistanceLineAx - 60f.dp2px()
        totalDistanceLineBy = totalDistanceLineAy

        totalDistanceLineCx = totalDistanceLineBx - 15f.dp2px()
        totalDistanceLineCy = totalDistanceLineBy + 15f.dp2px()

        usableRectF.set(mileageCenterX - usableRadius, mileageCenterY - usableRadius,
                mileageCenterX + usableRadius, mileageCenterY + usableRadius)

        usedRectF.set(mileageCenterX - usedRadius, mileageCenterY - usedRadius,
                mileageCenterX + usedRadius, mileageCenterY + usedRadius)

        usedLineAx = mileageCenterX
        usedLineAy = mileageCenterY - usedRadius
        usedLineBx = usedLineAx + 40f.dp2px()
        usedLineBy = usedLineAy
        usedLineCx = usedLineBx + 40f.dp2px()
        usedLineCy = usedLineBy

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawArc(totalDistanceRectF, 360f, 360f, false, totalDistancePaint)

        canvas.drawLine(totalDistanceLineAx, totalDistanceLineAy, totalDistanceLineBx, totalDistanceLineBy, totalDistanceLinePaint)
        canvas.drawLine(totalDistanceLineBx, totalDistanceLineBy, totalDistanceLineCx, totalDistanceLineCy, totalDistanceLinePaint)

        dotPaint.color = context.resources.getColor(R.color.color_E9ECEF, null)
        canvas.drawCircle(totalDistanceLineCx, totalDistanceLineCy, dotRadius.dp2px(), dotPaint)

        canvas.drawText("${text}km", totalDistanceLineCx - measureTextWidth(numberPaint, "${text}km") / 2, mileageCenterY, numberPaint)
        canvas.drawText("行驶总里程", totalDistanceLineCx - measureTextWidth(numberPaint, "${text}km") / 2, mileageCenterY + 20f.dp2px(), textPaint)





        canvas.drawText("$text min", mileageCenterX - measureTextWidth(numberPaint, "$text min") / 2, mileageCenterY, numberPaint)
        canvas.drawText("行驶总耗时", mileageCenterX - measureTextWidth(textPaint, "行驶总耗时") / 2, mileageCenterY + 20f.dp2px(), textPaint)



        canvas.drawArc(usableRectF, -90f, 135f, false, usablePaint)

        canvas.drawArc(usedRectF, -90f, 90f, false, usedPaint)


        canvas.drawLine(usedLineAx, usedLineAy, usedLineBx, usedLineBy, usedLinePaint)
        canvas.drawLine(usedLineBx, usedLineBy, usedLineCx, usedLineCy, usedLinePaint)

        dotPaint.color = context.resources.getColor(R.color.color_0FEEA2, null)
        canvas.drawCircle(usedLineCx, usedLineCy, dotRadius.dp2px(), dotPaint)

        canvas.drawText("${text}km", usedLineCx + 6f.dp2px(), usedLineCy + 6f.dp2px(), numberPaint)

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

    fun setSchedule(value: Int) {
        this.text = value.toString()
        invalidate()
    }

    fun startAnimate(value: Int) {
        val animator = ValueAnimator.ofInt(0, value)
        animator.duration = 500
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            text = curValue.toString()
            invalidate()
        }
        animator.start()
    }

}