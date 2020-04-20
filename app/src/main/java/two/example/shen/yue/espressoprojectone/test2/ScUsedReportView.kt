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
 * Describe: ScUsedReportView
 */
class ScUsedReportView : View {

    //圆
    private var circleCenterX = 0f
    private var circleCenterY = 0f

    private val travelPaint = Paint()
    private val travelRectF = RectF()
    private var travelRadius = 130f

    private var usageRateTextX = 0f
    private var usageRateTextY = 0f
    private var usageRatePercentX = 0f
    private var usageRatePercentY = 0f

    private val usedPaint = Paint()
    private val usedRectF = RectF()
    private var usedRadius = 140f

    //线
    private var linePaint = Paint()
    //文本
    private var textPaint = Paint()
    //公里数字   柱状图文字
    private var kmNumberPaint = Paint()
    //公里单位   柱状图顶部数字
    private var kmUnitPaint = Paint()

    //使用SC行驶里程
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

    //汽车行驶次数
    private var travelLineAx = 0f
    private var travelLineAy = 0f
    private var travelLineBx = 0f
    private var travelLineBy = 0f
    private var travelLineCx = 0f
    private var travelLineCy = 0f
    private var travelTextTitleX = 0f
    private var travelTextTitleY = 0f
    private var travelKmNumberX = 0f
    private var travelKmNumberY = 0f
    private var travelKmUnitX = 0f
    private var travelKmUnitY = 0f

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
    //矩形画笔
    private val rectanglePaint = Paint()
    private val leftRectangleRectF = RectF()
    private val rightRectangleRectF = RectF()
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


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {

        travelPaint.setARGB(255, 242, 242, 242)
        travelPaint.isAntiAlias = true
        travelPaint.strokeWidth = 10f.sp2px()
        travelPaint.style = Paint.Style.STROKE

        usedPaint.setARGB(255, 214, 214, 214)
        usedPaint.isAntiAlias = true
        usedPaint.strokeWidth = 15f.sp2px()
        usedPaint.style = Paint.Style.STROKE

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

        rectanglePaint.setARGB(255, 217, 219, 226)
        rectanglePaint.isAntiAlias = true
        rectanglePaint.style = Paint.Style.FILL
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
        initData()
    }

    private fun initData() {
        //计算圆心
        circleCenterX = width.toFloat() / 2
        circleCenterY = height.toFloat() / 4

        usageRateTextX = circleCenterX - measureTextWidth(textPaint, "SC使用率") / 2
        usageRateTextY = circleCenterY - 10f
        usageRatePercentX = circleCenterX - measureTextWidth(kmNumberPaint, "25%") / 2
        usageRatePercentY = usageRateTextY + 60f

        //计算汽车行驶次数圆环
        travelRectF.set(circleCenterX - travelRadius, circleCenterY - travelRadius,
                circleCenterX + travelRadius, circleCenterY + travelRadius)

        //计算使用SC行驶圆环
        usedRectF.set(circleCenterX - usedRadius, circleCenterY - usedRadius,
                circleCenterX + usedRadius, circleCenterY + usedRadius)

        //计算使用SC行驶测试
        usedLineAx = circleCenterX + travelRadius
        usedLineAy = circleCenterY - travelRadius
        usedLineBx = usedLineAx + 40f
        usedLineBy = usedLineAy - 80f
        usedLineCx = usedLineBx + 80f
        usedLineCy = usedLineBy

        usedTextTitleX = usedLineCx + 30f
        usedTextTitleY = usedLineCy + 10f
        usedKmNumberX = usedTextTitleX
        usedKmNumberY = usedTextTitleY + 70f
        usedKmUnitX = usedKmNumberX + 30f
        usedKmUnitY = usedKmNumberY

        //计算使用SC行驶测试
        travelLineAx = circleCenterX - travelRadius
        travelLineAy = circleCenterY + travelRadius
        travelLineBx = travelLineAx - 40f
        travelLineBy = travelLineAy + 80f
        travelLineCx = travelLineBx - 80f
        travelLineCy = travelLineBy

        travelTextTitleX = travelLineCx - measureTextWidth(textPaint, "汽车行驶次数") - 30f
        travelTextTitleY = travelLineCy + 10f
        travelKmNumberX = travelTextTitleX
        travelKmNumberY = travelTextTitleY + 70f
        travelKmUnitX = travelKmNumberX + 30f
        travelKmUnitY = travelKmNumberY

        //计算矩形
        leftRectangleRectF.set(80f, circleCenterY + travelRadius * 3f, circleCenterX - 40f, circleCenterY + travelRadius * 3f + 200f)
        rightRectangleRectF.set(circleCenterX + 40f, circleCenterY + travelRadius * 3f, width - 80f, circleCenterY + travelRadius * 3f + 200f)

        //油耗折线
        fuelConsumptionLineAx = 80f
        fuelConsumptionLineAy = circleCenterY + travelRadius * 5f
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
        carSpeedLineAy = circleCenterY + travelRadius * 5f
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

        canvas.drawArc(travelRectF, 360f, 360f, false, travelPaint)
        canvas.drawArc(usedRectF, -90f, 90f, false, usedPaint)

        canvas.drawText("SC使用率", usageRateTextX, usageRateTextY, textPaint)

        canvas.drawText("25%", usageRatePercentX, usageRatePercentY, kmNumberPaint)

        //画SC使用里程折线
        canvas.drawLine(usedLineAx, usedLineAy, usedLineBx, usedLineBy, linePaint)
        canvas.drawLine(usedLineBx, usedLineBy, usedLineCx, usedLineCy, linePaint)

        //画SC使用里程文字
        canvas.drawText("使用SC行驶次数", usedTextTitleX, usedTextTitleY, textPaint)
        canvas.drawText("1", usedKmNumberX, usedKmNumberY, kmNumberPaint)
        canvas.drawText("次", usedKmUnitX, usedKmUnitY, kmUnitPaint)

        //画矩形
        canvas.drawRect(leftRectangleRectF, rectanglePaint)
        canvas.drawRect(rightRectangleRectF, rectanglePaint)

        //画矩形内文字
        canvas.drawText("160KM", leftRectangleRectF.left + (leftRectangleRectF.right - leftRectangleRectF.left - measureTextWidth(kmNumberPaint, "160KM")) / 2,
                leftRectangleRectF.top + (leftRectangleRectF.bottom - leftRectangleRectF.top) / 2, kmNumberPaint)
        canvas.drawText("SC使用总路程", leftRectangleRectF.left + (leftRectangleRectF.right - leftRectangleRectF.left - measureTextWidth(textPaint, "SC使用总路程")) / 2,
                50f + leftRectangleRectF.top + (leftRectangleRectF.bottom - leftRectangleRectF.top) / 2, textPaint)

        canvas.drawText("2H", rightRectangleRectF.left + (rightRectangleRectF.right - rightRectangleRectF.left - measureTextWidth(kmNumberPaint, "2H")) / 2,
                rightRectangleRectF.top + (rightRectangleRectF.bottom - rightRectangleRectF.top) / 2, kmNumberPaint)
        canvas.drawText("SC使用总时长", rightRectangleRectF.left + (rightRectangleRectF.right - rightRectangleRectF.left - measureTextWidth(textPaint, "SC使用总时长")) / 2,
                50f + rightRectangleRectF.top + (rightRectangleRectF.bottom - rightRectangleRectF.top) / 2, textPaint)

        //画汽车行驶次数折线
        canvas.drawLine(travelLineAx, travelLineAy, travelLineBx, travelLineBy, linePaint)
        canvas.drawLine(travelLineBx, travelLineBy, travelLineCx, travelLineCy, linePaint)

        //画汽车行驶次数文字
        canvas.drawText("汽车行驶次数", travelTextTitleX, travelTextTitleY, textPaint)
        canvas.drawText("4", travelKmNumberX, travelKmNumberY, kmNumberPaint)
        canvas.drawText("次", travelKmUnitX, travelKmUnitY, kmUnitPaint)


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