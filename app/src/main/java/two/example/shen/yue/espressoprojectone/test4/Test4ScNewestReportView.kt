package two.example.shen.yue.espressoprojectone.test4

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
class Test4ScNewestReportView : View {

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

    //可用半径
    private var usableRadius = 0f
    private val usableRectF = RectF()
    private val usablePaint = Paint()
    //使用半径
    private var usedRadius = 0f
    private val usedRectF = RectF()
    private val usedPaint = Paint()


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

        usedPaint.setARGB(255, 0, 239, 164)
        usedPaint.isAntiAlias = true
        usedPaint.strokeWidth = annulusWidth.sp2px()
        usedPaint.style = Paint.Style.STROKE

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

        mileageCenterY = usedRadius + 2 * annulusWidth.dp2px()

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

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawArc(totalDistanceRectF, 360f, 360f, false, totalDistancePaint)
        canvas.drawLine(totalDistanceLineAx, totalDistanceLineAy, totalDistanceLineBx, totalDistanceLineBy, totalDistanceLinePaint)
        canvas.drawLine(totalDistanceLineBx, totalDistanceLineBy, totalDistanceLineCx, totalDistanceLineCy, totalDistanceLinePaint)
        canvas.drawCircle(totalDistanceLineCx, totalDistanceLineCy, dotRadius.dp2px(), dotPaint)
        canvas.drawText("100km", totalDistanceLineCx - measureTextWidth(numberPaint, "100km") / 2, mileageCenterY, numberPaint)
        canvas.drawText("行驶总里程", totalDistanceLineCx - measureTextWidth(numberPaint, "100km") / 2, mileageCenterY + 20f.dp2px(), textPaint)

        canvas.drawText("38 min", mileageCenterX - measureTextWidth(numberPaint, "38 min") / 2, mileageCenterY, numberPaint)
        canvas.drawText("行驶总耗时", mileageCenterX - measureTextWidth(textPaint, "行驶总耗时") / 2, mileageCenterY + 20f.dp2px(), textPaint)



        canvas.drawArc(usableRectF, -90f, 135f, false, usablePaint)

        canvas.drawArc(usedRectF, -90f, 35f, false, usedPaint)

        drawSpeed(canvas)
        drawSpeedYLine(canvas)
    }

    private fun drawSpeed(canvas: Canvas) {
        speedPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        speedPaint.textSize = 18f.sp2px()
        canvas.drawText("平均车速", 0f, mileageCenterY * 2.3f, speedPaint)
        val left = measureTextWidth(speedPaint, "平均车速")
        speedPaint.textSize = 14f.sp2px()
        canvas.drawText("km/h", left + 4f.dp2px(), mileageCenterY * 2.3f, speedPaint)
    }

    private fun drawSpeedYLine(canvas: Canvas) {
        speedPaint.color = context.resources.getColor(R.color.color_C8C8C8, null)
        speedPaint.textSize = 12f.sp2px()
        val speedWidth = measureTextWidth(speedPaint, "999")
        val y = mileageCenterY * 2.7f
        canvas.drawText("160", (speedWidth - measureTextWidth(speedPaint, "160")) / 2, mileageCenterY * 2.7f, speedPaint)
        drawImaginaryLine(canvas, speedWidth + 16f.dp2px(), mileageCenterY * 2.7f)
        canvas.drawText("120", (speedWidth - measureTextWidth(speedPaint, "120")) / 2, mileageCenterY * 3.1f, speedPaint)
        drawImaginaryLine(canvas, speedWidth + 16f.dp2px(), mileageCenterY * 3.1f)
        canvas.drawText("80", (speedWidth - measureTextWidth(speedPaint, "80")) / 2, mileageCenterY * 3.5f, speedPaint)
        drawImaginaryLine(canvas, speedWidth + 16f.dp2px(), mileageCenterY * 3.5f)
        canvas.drawText("40", (speedWidth - measureTextWidth(speedPaint, "40")) / 2, mileageCenterY * 3.9f, speedPaint)
        drawImaginaryLine(canvas, speedWidth + 16f.dp2px(), mileageCenterY * 3.9f)
        canvas.drawText("0", (speedWidth - measureTextWidth(speedPaint, "0")) / 2, mileageCenterY * 4.3f, speedPaint)
        drawImaginaryLine(canvas, speedWidth + 16f.dp2px(), mileageCenterY * 4.3f)

        drawSpeedText(canvas, mileageCenterY * 4.5f)
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

}