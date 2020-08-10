package two.example.shen.yue.espressoprojectone.study.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

/**
 * 瀑布布局
 */
class WaterfallFLowLayout : ViewGroup {

    private val heightList = ArrayList<Int>()

    private val viewList = ArrayList<List<View>>()

    private var list = ArrayList<View>()

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.i("queen", "onLayout")

        var left: Int
        var right: Int
        var top: Int
        var bottom: Int

        var currentTop = 0
        var currentLeft = 0

        viewList.forEachIndexed { index, view ->
            view.forEach { item ->
                val layoutParams = item.layoutParams as MarginLayoutParams
                left = currentLeft + layoutParams.leftMargin
                top = currentTop + layoutParams.topMargin
                right = left + item.measuredWidth
                bottom = top + item.measuredHeight
                Log.i("queen","left:$left")
                Log.i("queen","right:$right")
                Log.i("queen","top:$top")
                Log.i("queen","bottom:$bottom")
                item.layout(left, top, right, bottom)
                currentLeft += item.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin
            }
            currentLeft = 0
            currentTop += heightList[index]
        }
        heightList.clear()
        viewList.clear()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        heightList.clear()
        viewList.clear()
        list.clear()
        Log.i("queen", "onMeasure")

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val widthModel = MeasureSpec.getMode(widthMeasureSpec)
        val heightModel = MeasureSpec.getMode(heightMeasureSpec)

        var measureWidth = 0
        var measureHeight = 0

        var currentLineWidth = 0
        var currentLineHeight = 0

        if (widthModel == MeasureSpec.EXACTLY && heightModel == MeasureSpec.EXACTLY) {
            measureWidth = widthSize
            measureHeight = heightSize
        } else {
            var childWidth: Int
            var childHeight: Int

            for (i in 0 until childCount) {
                val childView = getChildAt(i)
                measureChild(childView, widthMeasureSpec, heightMeasureSpec)
                val layoutParams = childView.layoutParams as MarginLayoutParams
                childWidth =
                    childView.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin
                childHeight =
                    childView.measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin

                if (currentLineWidth + childWidth > widthSize) {
                    Log.i("queen", "222")
                    measureWidth = max(measureWidth, currentLineWidth)
                    measureHeight += currentLineHeight

                    heightList.add(currentLineHeight)
                    viewList.add(list)

                    currentLineWidth = childWidth
                    currentLineHeight = childHeight

                    list = ArrayList()
                    list.add(childView)

                } else {
                    Log.i("queen", "111")
                    currentLineWidth += childWidth
                    currentLineHeight = max(currentLineHeight, childHeight)
                    list.add(childView)
                }

                if (i == childCount - 1) {
                    measureWidth = max(measureWidth, currentLineWidth)
                    measureHeight += currentLineHeight

                    viewList.add(list)
                    heightList.add(currentLineHeight)
                }
            }
        }
        Log.i("queen", "测量布局:$measureWidth   $measureHeight")
        Log.i("queen", "x1=${heightList.size}")
        Log.i("queen", "x2=${viewList.size}")
        Log.i("queen", "x3=${list.size}")
        setMeasuredDimension(measureWidth, measureHeight)
    }
}