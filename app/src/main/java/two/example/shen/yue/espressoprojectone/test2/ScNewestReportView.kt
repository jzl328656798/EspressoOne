package two.example.shen.yue.espressoprojectone.test2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View


/**
 * Author: Queen
 * Date: 2020/3/24 4:18 PM
 * Describe: ScNewestReportView
 */
class ScNewestReportView : View {

    //圆
    private val circlePaint = Paint()
    private var circleCenterX = 0f
    private var circleCenterY = 0f
    private var circleRadius = 100f

    //已使用里程
    private val usedPaint = Paint()
    private var usedRadius = 130f
    private val usedRectF = RectF()
    private var usedLineAx = 0f
    private var usedLineAy = 0f
    private var usedLineBx = 0f
    private var usedLineBy = 0f
    private var usedLineCx = 0f
    private var usedLineCy = 0f
    private var usedTextTitleX = 0f
    private var usedTextTitleY = 0f
    private var usedKmNumberX = 0f
    private var usedKmNumberY = 0f
    private var usedKmUnitX = 0f
    private var usedKmUnitY = 0f


    //可用里程
    private val usablePaint = Paint()
    private var usableRadius = 115f
    private val usableRectF = RectF()
    private var usableLineAx = 0f
    private var usableLineAy = 0f
    private var usableLineBx = 0f
    private var usableLineBy = 0f
    private var usableLineCx = 0f
    private var usableLineCy = 0f
    private var usableTextTitleX = 0f
    private var usableTextTitleY = 0f
    private var usableKmNumberX = 0f
    private var usableKmNumberY = 0f
    private var usableKmUnitX = 0f
    private var usableKmUnitY = 0f

    //总里程
    private var totalLineAx = 0f
    private var totalLineAy = 0f
    private var totalLineBx = 0f
    private var totalLineBy = 0f
    private var totalLineCx = 0f
    private var totalLineCy = 0f
    private var totalTextTitleX = 0f
    private var totalTextTitleY = 0f
    private var totalKmNumberX = 0f
    private var totalKmNumberY = 0f
    private var totalKmUnitX = 0f
    private var totalKmUnitY = 0f
    private var consumeHourTitleX = 0f
    private var consumeHourTitleY = 0f
    private var consumeHourKmNumberX = 0f
    private var consumeHourKmNumberY = 0f
    private var consumeHourKmUnitX = 0f
    private var consumeHourKmUnitY = 0f

    //油耗折线
    private var fuelConsumptionLineAx = 0f
    private var fuelConsumptionLineAy = 0f
    private var fuelConsumptionLineBx = 0f
    private var fuelConsumptionLineBy = 0f
    private var fuelConsumptionLineCx = 0f
    private var fuelConsumptionLineCy = 0f
    private var fuelConsumptionTagX = 0f
    private var fuelConsumptionTagY = 0f

    //车速折线
    private var carSpeedLineAx = 0f
    private var carSpeedLineAy = 0f
    private var carSpeedLineBx = 0f
    private var carSpeedLineBy = 0f
    private var carSpeedLineCx = 0f
    private var carSpeedLineCy = 0f
    private var carSpeedTagX = 0f
    private var carSpeedTagY = 0f

    //柱状图画笔
    private val histogramPaint = Paint()
    //油耗总里程柱状图
    private val fuelConsumptionTotalRectF = RectF()
    private var fuelConsumptionTotalTagX = 0f
    private var fuelConsumptionTotalTagY = 0f
    private var fuelConsumptionTotalAmountX = 0f
    private var fuelConsumptionTotalAmountY = 0f
    //油耗使用SC里程柱状图
    private val fuelConsumptionUsedRectF = RectF()
    private var fuelConsumptionUsedTagX = 0f
    private var fuelConsumptionUsedTagY = 0f
    private var fuelConsumptionUsedAmountX = 0f
    private var fuelConsumptionUsedAmountY = 0f
    //车速总里程柱状图
    private val carSpeedTotalRectF = RectF()
    private var carSpeedTotalTagX = 0f
    private var carSpeedTotalTagY = 0f
    private var carSpeedTotalAmountX = 0f
    private var carSpeedTotalAmountY = 0f
    //车速使用SC里程柱状图
    private val carSpeedUsedRectF = RectF()
    private var carSpeedUsedTagX = 0f
    private var carSpeedUsedTagY = 0f
    private var carSpeedUsedAmountX = 0f
    private var carSpeedUsedAmountY = 0f

    //线
    private var linePaint = Paint()
    //文本
    private var textPaint = Paint()
    //公里数字   柱状图文字
    private var kmNumberPaint = Paint()
    //公里单位   柱状图顶部数字
    private var kmUnitPaint = Paint()


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {
        circlePaint.setARGB(255, 242, 242, 242)
        circlePaint.isAntiAlias = true

        usedPaint.setARGB(255, 191, 191, 191)
        usedPaint.isAntiAlias = true
        usedPaint.style = Paint.Style.FILL

        usablePaint.setARGB(255, 214, 214, 214)
        usablePaint.isAntiAlias = true
        usablePaint.style = Paint.Style.FILL

        linePaint.setARGB(255, 166, 166, 166)
        linePaint.isAntiAlias = true
        linePaint.strokeWidth = 2f

        textPaint.setARGB(255, 166, 166, 166)
        textPaint.isAntiAlias = true
        textPaint.textSize = 12f.sp2px()

        kmNumberPaint.setARGB(255, 43, 43, 43)
        kmNumberPaint.isAntiAlias = true
        kmNumberPaint.textSize = 14f.sp2px()

        kmUnitPaint.setARGB(255, 43, 43, 43)
        kmUnitPaint.isAntiAlias = true
        kmUnitPaint.textSize = 12f.sp2px()

        histogramPaint.setARGB(255, 217, 219, 226)
        histogramPaint.isAntiAlias = true
        histogramPaint.style = Paint.Style.FILL
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        initData()
    }

    private fun initData() {
        //计算圆心
        circleCenterX = width.toFloat() / 2
        circleCenterY = height.toFloat() / 4

        //计算可用里程
        usableRectF.set(circleCenterX - usableRadius, circleCenterY - usableRadius,
                circleCenterX + usableRadius, circleCenterY + usableRadius)

        //计算可用里程折线
        usableLineAx = circleCenterX + usableRadius + 50f
        usableLineAy = circleCenterY - 20f
        usableLineBx = usableLineAx + 40f
        usableLineBy = usableLineAy - 80f
        usableLineCx = usableLineBx + 80f
        usableLineCy = usableLineBy
        usableTextTitleX = usableLineCx + 30f
        usableTextTitleY = usableLineCy + 10f
        usableKmNumberX = usableTextTitleX
        usableKmNumberY = usableTextTitleY + 70f
        usableKmUnitX = usableKmNumberX + 60f
        usableKmUnitY = usableKmNumberY

        //计算已使用里程
        usedRectF.set(circleCenterX - usedRadius, circleCenterY - usedRadius,
                circleCenterX + usedRadius, circleCenterY + usedRadius)

        //计算已使用里程折线
        usedLineAx = circleCenterX + 50f
        usedLineAy = circleCenterY - usedRadius - 50f
        usedLineBx = usedLineAx + 40f
        usedLineBy = usedLineAy - 80f
        usedLineCx = usedLineBx + 80f
        usedLineCy = usedLineBy
        usedTextTitleX = usedLineCx + 30f
        usedTextTitleY = usedLineCy + 10f
        usedKmNumberX = usedTextTitleX + 20f
        usedKmNumberY = usedTextTitleY + 70f
        usedKmUnitX = usedKmNumberX + 40f
        usedKmUnitY = usedKmNumberY

        //总里程
        totalLineAx = circleCenterX - usedRadius
        totalLineAy = circleCenterY + usedRadius
        totalLineBx = totalLineAx - 40f
        totalLineBy = totalLineAy + 80f
        totalLineCx = totalLineBx - 80f
        totalLineCy = totalLineBy
        totalTextTitleX = totalLineCx - 200f
        totalTextTitleY = totalLineCy - 100f
        totalKmNumberX = totalTextTitleX
        totalKmNumberY = totalTextTitleY + 70f
        totalKmUnitX = totalKmNumberX + 80f
        totalKmUnitY = totalKmNumberY

        //消耗时间
        consumeHourTitleX = totalLineCx - 200f
        consumeHourTitleY = totalLineCy + 60f
        consumeHourKmNumberX = consumeHourTitleX
        consumeHourKmNumberY = consumeHourTitleY + 70f
        consumeHourKmUnitX = consumeHourKmNumberX + 60f
        consumeHourKmUnitY = consumeHourKmNumberY

        //油耗折线
        fuelConsumptionLineAx = 80f
        fuelConsumptionLineAy = circleCenterY + usableRadius * 4.5f
        fuelConsumptionLineBx = fuelConsumptionLineAx
        fuelConsumptionLineBy = fuelConsumptionLineAy + 300f
        fuelConsumptionLineCx = fuelConsumptionLineBx + circleCenterX - 120f
        fuelConsumptionLineCy = fuelConsumptionLineBy

        val average = (fuelConsumptionLineCx - fuelConsumptionLineBx) / 20

        //油耗总里程柱状图
        fuelConsumptionTotalRectF.set(80f + average * 3, fuelConsumptionLineAy + 300f * 0.1f,
                80f + average * 5, fuelConsumptionLineAy + 300f)
        //油耗使用SC里程柱状图
        fuelConsumptionUsedRectF.set(80f + average * 14, fuelConsumptionLineAy + 300f * 0.3f,
                80f + average * 16, fuelConsumptionLineAy + 300f)

        //车速折线
        carSpeedLineAx = circleCenterX + 40f
        carSpeedLineAy = circleCenterY + usableRadius * 4.5f
        carSpeedLineBx = carSpeedLineAx
        carSpeedLineBy = carSpeedLineAy + 300f
        carSpeedLineCx = width - 80f
        carSpeedLineCy = carSpeedLineBy

        //车速总里程柱状图
        carSpeedTotalRectF.set(carSpeedLineAx + average * 3, carSpeedLineAy + 300f * 0.4f,
                carSpeedLineAx + average * 5, carSpeedLineAy + 300f)
        //车速使用SC里程柱状图
        carSpeedUsedRectF.set(carSpeedLineAx + average * 14, carSpeedLineAy + 300f * 0.2f,
                carSpeedLineAx + average * 16, carSpeedLineAy + 300f)

        //计算平局油耗tag
        fuelConsumptionTagX = 80f + (fuelConsumptionLineCx - fuelConsumptionLineBx - measureTextWidth(kmNumberPaint, "平均油耗（L/KM）")) / 2
        fuelConsumptionTagY = fuelConsumptionLineBy + 130f

        //计算平局车速tag
        carSpeedTagX = carSpeedLineAx + (carSpeedLineCx - carSpeedLineBx - measureTextWidth(kmNumberPaint, "平均车速（KM/H）")) / 2
        carSpeedTagY = carSpeedLineBy + 130f

        //计算平局油耗柱状图tag
        fuelConsumptionTotalTagX = fuelConsumptionTotalRectF.left - measureTextWidth(textPaint, "总行程") / 2 + (fuelConsumptionTotalRectF.right - fuelConsumptionTotalRectF.left) / 2
        fuelConsumptionTotalTagY = fuelConsumptionTotalRectF.bottom + 60f
        fuelConsumptionUsedTagX = fuelConsumptionUsedRectF.left - measureTextWidth(textPaint, "使用SC行程") / 2 + (fuelConsumptionUsedRectF.right - fuelConsumptionUsedRectF.left) / 2
        fuelConsumptionUsedTagY = fuelConsumptionUsedRectF.bottom + 60f

        fuelConsumptionTotalAmountX = fuelConsumptionTotalRectF.left - measureTextWidth(kmUnitPaint, "8") / 2 + (fuelConsumptionTotalRectF.right - fuelConsumptionTotalRectF.left) / 2
        fuelConsumptionTotalAmountY = fuelConsumptionTotalRectF.top - 20f
        fuelConsumptionUsedAmountX = fuelConsumptionUsedRectF.left - measureTextWidth(kmUnitPaint, "7") / 2 + (fuelConsumptionUsedRectF.right - fuelConsumptionUsedRectF.left) / 2
        fuelConsumptionUsedAmountY = fuelConsumptionUsedRectF.top - 20f

        //计算平局车速柱状图tag
        carSpeedTotalTagX = carSpeedTotalRectF.left - measureTextWidth(textPaint, "总行程") / 2 + (carSpeedTotalRectF.right - carSpeedTotalRectF.left) / 2
        carSpeedTotalTagY = carSpeedTotalRectF.bottom + 60f
        carSpeedUsedTagX = carSpeedUsedRectF.left - measureTextWidth(textPaint, "使用SC行程") / 2 + (carSpeedUsedRectF.right - carSpeedUsedRectF.left) / 2
        carSpeedUsedTagY = carSpeedUsedRectF.bottom + 60f


        carSpeedTotalAmountX = carSpeedTotalRectF.left - measureTextWidth(kmUnitPaint, "65") / 2 + (carSpeedTotalRectF.right - carSpeedTotalRectF.left) / 2
        carSpeedTotalAmountY = carSpeedTotalRectF.top - 20f
        carSpeedUsedAmountX = carSpeedUsedRectF.left - measureTextWidth(kmUnitPaint, "85") / 2 + (carSpeedUsedRectF.right - carSpeedUsedRectF.left) / 2
        carSpeedUsedAmountY = carSpeedUsedRectF.top - 20f

    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        canvas.drawCircle(circleCenterX, circleCenterY, circleRadius, circlePaint)

        canvas.drawArc(usableRectF, -90f, 135f, true, usablePaint)

        canvas.drawArc(usedRectF, -90f, 25f, true, usedPaint)

        //画SC使用里程折线
        canvas.drawLine(usedLineAx, usedLineAy, usedLineBx, usedLineBy, linePaint)
        canvas.drawLine(usedLineBx, usedLineBy, usedLineCx, usedLineCy, linePaint)

        //画SC使用里程文字
        canvas.drawText("SC使用里程", usedTextTitleX, usedTextTitleY, textPaint)
        canvas.drawText("2", usedKmNumberX, usedKmNumberY, kmNumberPaint)
        canvas.drawText("KM", usedKmUnitX, usedKmUnitY, kmUnitPaint)

        //画可用里程折线
        canvas.drawLine(usableLineAx, usableLineAy, usableLineBx, usableLineBy, linePaint)
        canvas.drawLine(usableLineBx, usableLineBy, usableLineCx, usableLineCy, linePaint)

        //画可用里程文字
        canvas.drawText("SC可用里程", usableTextTitleX, usableTextTitleY, textPaint)
        canvas.drawText("13", usableKmNumberX, usableKmNumberY, kmNumberPaint)
        canvas.drawText("KM", usableKmUnitX, usableKmUnitY, kmUnitPaint)

        //画总里程和耗时折线
        canvas.drawLine(totalLineAx, totalLineAy, totalLineBx, totalLineBy, linePaint)
        canvas.drawLine(totalLineBx, totalLineBy, totalLineCx, totalLineCy, linePaint)

        //画总里程文字
        canvas.drawText("行驶总里程", totalTextTitleX, totalTextTitleY, textPaint)
        canvas.drawText("100", totalKmNumberX, totalKmNumberY, kmNumberPaint)
        canvas.drawText("KM", totalKmUnitX, totalKmUnitY, kmUnitPaint)

        //画耗时文字
        canvas.drawText("耗时", consumeHourTitleX, consumeHourTitleY, textPaint)
        canvas.drawText("10", consumeHourKmNumberX, consumeHourKmNumberY, kmNumberPaint)
        canvas.drawText("H", consumeHourKmUnitX, consumeHourKmUnitY, kmUnitPaint)

        //画油耗线
        canvas.drawLine(fuelConsumptionLineAx, fuelConsumptionLineAy, fuelConsumptionLineBx, fuelConsumptionLineBy, linePaint)
        canvas.drawLine(fuelConsumptionLineBx, fuelConsumptionLineBy, fuelConsumptionLineCx, fuelConsumptionLineCy, linePaint)

        //画油耗柱状图
        canvas.drawRect(fuelConsumptionTotalRectF, histogramPaint)
        canvas.drawRect(fuelConsumptionUsedRectF, histogramPaint)

        //画车速线
        canvas.drawLine(carSpeedLineAx, carSpeedLineAy, carSpeedLineBx, carSpeedLineBy, linePaint)
        canvas.drawLine(carSpeedLineBx, carSpeedLineBy, carSpeedLineCx, carSpeedLineCy, linePaint)

        //画车速柱状图
        canvas.drawRect(carSpeedTotalRectF, histogramPaint)
        canvas.drawRect(carSpeedUsedRectF, histogramPaint)

        //画油耗柱状图tag
        canvas.drawText("平均油耗（L/KM）", fuelConsumptionTagX, fuelConsumptionTagY, kmNumberPaint)

        //画车速柱状图tag
        canvas.drawText("平均车速（KM/H）", carSpeedTagX, carSpeedTagY, kmNumberPaint)

        canvas.drawText("总行程", fuelConsumptionTotalTagX, fuelConsumptionTotalTagY, textPaint)

        canvas.drawText("使用SC行程", fuelConsumptionUsedTagX, fuelConsumptionUsedTagY, textPaint)

        canvas.drawText("总行程", carSpeedTotalTagX, carSpeedTotalTagY, textPaint)

        canvas.drawText("使用SC行程", carSpeedUsedTagX, carSpeedUsedTagY, textPaint)

        canvas.drawText("8", fuelConsumptionTotalAmountX, fuelConsumptionTotalAmountY, kmUnitPaint)

        canvas.drawText("7", fuelConsumptionUsedAmountX, fuelConsumptionUsedAmountY, kmUnitPaint)

        canvas.drawText("65", carSpeedTotalAmountX, carSpeedTotalAmountY, kmUnitPaint)

        canvas.drawText("85", carSpeedUsedAmountX, carSpeedUsedAmountY, kmUnitPaint)

    }

    private fun measureTextWidth(paint: Paint, text: String): Float {
        return paint.measureText(text)
    }

    private fun Float.sp2px(): Float {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return this * fontScale + 0.5f
    }
}