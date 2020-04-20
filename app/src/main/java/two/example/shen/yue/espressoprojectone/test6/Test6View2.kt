package two.example.shen.yue.espressoprojectone.test6

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import two.example.shen.yue.espressoprojectone.R
import kotlin.math.sin


/**
 * Author: Queen
 * Date: 2020/3/24 4:18 PM
 * Describe: ScNewestReportView
 */
class Test6View2 : View {

    private val annulusWidth = 8f
    private val lineWidth = 1f

    private val textPaint = Paint()

    //里程圆心
    private var circleX = 0f
    private var circleY = 0f
    private var circleRadius = 0f
    private var usableRadius = 0f
    private var usedRadius = 0f

    //圆点
    private val dotRadius = 2f
    private val dotPaint = Paint()

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var totalDistanceLineAx = 0f
    private var totalDistanceLineAy = 0f
    private var totalDistanceLineBx = 0f
    private var totalDistanceLineBy = 0f
    private var totalDistanceLineCx = 0f
    private var totalDistanceLineCy = 0f

    private var totalDistanceTextX = 0f
    private var totalDistanceTextY = 0f

    private var totalDistanceTagX = 0f
    private var totalDistanceTagY = 0f

    private var usedLineAx = 0f
    private var usedLineAy = 0f
    private var usedLineBx = 0f
    private var usedLineBy = 0f
    private var usedLineCx = 0f
    private var usedLineCy = 0f

    private var usedTextX = 0f
    private var usedTextY = 0f

    private var usedTagX = 0f
    private var usedTagY = 0f

    private var usableLineAx = 0f
    private var usableLineAy = 0f
    private var usableLineBx = 0f
    private var usableLineBy = 0f
    private var usableLineCx = 0f
    private var usableLineCy = 0f

    private var usableTextX = 0f
    private var usableTextY = 0f

    private var usableTagX = 0f
    private var usableTagY = 0f

    private var totalDistanceValue = "0"
    private var usedValue = "100"
    private var usableValue = "100"


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {

        dotPaint.isAntiAlias = true

        linePaint.isAntiAlias = true
        linePaint.strokeWidth = lineWidth.sp2px()
        linePaint.style = Paint.Style.STROKE

        textPaint.isAntiAlias = true

    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        initData()
    }

    private fun initData() {
        circleX = width / 2f
        circleY = height / 2f

        circleRadius = height / 4f
        usableRadius = circleRadius + 8f.dp2px()
        usedRadius = usableRadius + 8f.dp2px()

        totalDistanceLineAx = circleX - circleRadius + circleRadius * 0.34f
        totalDistanceLineAy = circleY - circleRadius + circleRadius * 0.2f

        totalDistanceLineBx = totalDistanceLineAx - circleRadius * 1.3f
        totalDistanceLineBy = totalDistanceLineAy

        totalDistanceLineCx = totalDistanceLineBx - circleRadius * 0.4f
        totalDistanceLineCy = totalDistanceLineBy + circleRadius * 0.4f

        totalDistanceTextX = totalDistanceLineCx - circleRadius * 0.4f
        totalDistanceTextY = totalDistanceLineCy + circleRadius * 0.15f + 16f.dp2px()

        totalDistanceTagX = totalDistanceTextX
        totalDistanceTagY = totalDistanceTextY + 20f.dp2px()


        usedLineAx = circleX + sin(Math.PI / 5).toFloat() * usedRadius
        usedLineAy = circleY - sin(Math.PI / 3.33).toFloat() * usedRadius

        usedLineBx = circleX + circleRadius + 8f.dp2px()
        usedLineBy = circleY - circleRadius - 16f.dp2px()

        usedLineCx = circleX + circleRadius * 1.8f
        usedLineCy = usedLineBy

        usedTextX = usedLineCx + 10f.dp2px()
        usedTextY = usedLineCy + 4f.dp2px()

        usedTagX = usedTextX
        usedTagY = usedTextY + 20f.dp2px()



        usableLineAx = circleX + sin(Math.PI / 2.77).toFloat() * usableRadius
        usableLineAy = circleY + sin(Math.PI / 7.2).toFloat() * usableRadius

        usableLineBx = circleX + circleRadius + 20f.dp2px()
        usableLineBy = circleY + circleRadius + 8f.dp2px()

        usableLineCx = circleX + circleRadius * 1.8f
        usableLineCy = usableLineBy

        usableTextX = usableLineCx + 10f.dp2px()
        usableTextY = usableLineCy + 4f.dp2px()

        usableTagX = usableTextX
        usableTagY = usableTextY + 20f.dp2px()


    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (false) {
            val circlePaint = Paint()
            circlePaint.isAntiAlias = true
            circlePaint.strokeWidth = annulusWidth.sp2px()
            circlePaint.style = Paint.Style.STROKE

            val totalDistanceRectF = RectF()
            circleRadius += 8f.dp2px()
            totalDistanceRectF.set(circleX - circleRadius, circleY - circleRadius,
                    circleX + circleRadius, circleY + circleRadius)

            circlePaint.color = context.resources.getColor(R.color.color_E9ECEF, null)
            canvas.drawArc(totalDistanceRectF, 360f, 360f, false, circlePaint)

            circlePaint.color = context.resources.getColor(R.color.color_0FEEA2, null)
            circlePaint.strokeWidth = 1f.sp2px()
            canvas.drawArc(totalDistanceRectF, 360f, 360f, false, circlePaint)
        }

        //以上可删除

        //行驶总里程
        linePaint.color = context.resources.getColor(R.color.color_E9ECEF, null)
        canvas.drawLine(totalDistanceLineAx, totalDistanceLineAy, totalDistanceLineBx, totalDistanceLineBy, linePaint)
        canvas.drawLine(totalDistanceLineBx, totalDistanceLineBy, totalDistanceLineCx, totalDistanceLineCy, linePaint)
        dotPaint.color = context.resources.getColor(R.color.color_E9ECEF, null)
        canvas.drawCircle(totalDistanceLineCx, totalDistanceLineCy, dotRadius.dp2px(), dotPaint)

        textPaint.textSize = 14f.sp2px()
        textPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        textPaint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("${totalDistanceValue}km", totalDistanceTextX, totalDistanceTextY, textPaint)

        textPaint.textSize = 12f.sp2px()
        textPaint.color = context.resources.getColor(R.color.color_C8C8C8, null)
        textPaint.typeface = Typeface.DEFAULT
        canvas.drawText("行驶总里程", totalDistanceTagX, totalDistanceTagY, textPaint)

        //SC使用总里程
        linePaint.color = context.resources.getColor(R.color.color_0FEEA2, null)
        canvas.drawLine(usedLineAx, usedLineAy, usedLineBx, usedLineBy, linePaint)
        canvas.drawLine(usedLineBx, usedLineBy, usedLineCx, usedLineCy, linePaint)
        dotPaint.color = context.resources.getColor(R.color.color_0FEEA2, null)
        canvas.drawCircle(usedLineCx, usedLineCy, dotRadius.dp2px(), dotPaint)

        textPaint.textSize = 14f.sp2px()
        textPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        textPaint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("${usedValue}km", usedTextX, usedTextY, textPaint)

        textPaint.textSize = 12f.sp2px()
        textPaint.color = context.resources.getColor(R.color.color_C8C8C8, null)
        textPaint.typeface = Typeface.DEFAULT
        canvas.drawText("SC使用里程", usedTagX, usedTagY, textPaint)

        //SC可用里程
        linePaint.color = context.resources.getColor(R.color.color_B5A36A, null)
        canvas.drawLine(usableLineAx, usableLineAy, usableLineBx, usableLineBy, linePaint)
        canvas.drawLine(usableLineBx, usableLineBy, usableLineCx, usableLineCy, linePaint)
        dotPaint.color = context.resources.getColor(R.color.color_B5A36A, null)
        canvas.drawCircle(usableLineCx, usableLineCy, dotRadius.dp2px(), dotPaint)

        textPaint.textSize = 14f.sp2px()
        textPaint.color = context.resources.getColor(R.color.color_4B4B4B, null)
        textPaint.typeface = Typeface.DEFAULT_BOLD
        canvas.drawText("${usableValue}km", usableTextX, usableTextY, textPaint)

        textPaint.textSize = 12f.sp2px()
        textPaint.color = context.resources.getColor(R.color.color_C8C8C8, null)
        textPaint.typeface = Typeface.DEFAULT
        canvas.drawText("SC可用里程", usableTagX, usableTagY, textPaint)

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

    fun startAnimate(totalDistanceValue: Int) {
        val animatorSet = AnimatorSet()
        val animatorHour = ValueAnimator.ofInt(0, totalDistanceValue)
        animatorHour.duration = 500
        animatorHour.addUpdateListener {
            val curValue = it.animatedValue as Int
            this.totalDistanceValue = curValue.toString()
            this.usedValue = curValue.toString()
            this.usableValue = curValue.toString()
            invalidate()
        }
        animatorSet.play(animatorHour)
        animatorSet.start()

    }

}