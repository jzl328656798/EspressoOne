package two.example.shen.yue.espressoprojectone.test10

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_test10_2.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/4/20 2:21 PM
 * Describe: TODO
 */
class Test10Activity2 : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test10_2)
//        val str = "这是￥200元一段测试数据这是测试数据这是测试数据这是一段测试数据这是一段测试数据这是一段字这是一段字"
        val str = "这是￥200元一段测试数据这是测试段字这是一段字"
        tv_span.text = str

        btn.setOnClickListener {
            //setDrawableInTxt(tv_span, str, R.mipmap.restore_default)
//            setEndDrawable(tv_span, R.mipmap.restore_default, str, true)
            setEndDrawable2(tv_span, R.mipmap.restore_default, str)
        }

        btn1.setOnClickListener { tv_span.text = str }

        btn3.setOnClickListener {
            val height = tv_span.height
            Log.i("queen", "height:${height}")
        }

    }


    private fun setEndDrawable(textView: TextView, drawableId: Int, text: String, addImg: Boolean = false) {

        val spannableString = SpannableString(text)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#0099EE"))
        val start = text.indexOf("￥")
        val end = text.indexOf("元")
        spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        textView.text = spannableString

        if (addImg) {
            val drawable = resources.getDrawable(drawableId, null)
            val paint = textView.paint
            val fontMetrics = paint.fontMetrics
            val textHeight = fontMetrics.bottom - fontMetrics.top
            Log.i("queen", "textHeight:${textHeight}")
            val textWidth = textHeight * 54 / 16
            drawable.setBounds(0, 0, textWidth.toInt(), textHeight.toInt())
            val span = SpannableString("恢复默认")
            val imageSpan = object : ClickableImageSpan(drawable) {
                override fun onClick(view: View) {
                    Log.i("queen", "sssssssssssssssssssss")
                }
            }
            span.setSpan(imageSpan, 0, 4, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            textView.append(span)
            textView.movementMethod = ClickableMovementMethod.getInstance()
        }
    }

    private fun setEndDrawable1(textView: TextView, drawableId: Int, text: String) {

        val spannableString = SpannableString(text)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#0099EE"))
        val start = text.indexOf("￥")
        val end = text.indexOf("元")
        spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        val drawable = resources.getDrawable(drawableId, null)
        val paint = textView.paint
        val fontMetrics = paint.fontMetrics
        val textHeight = fontMetrics.bottom - fontMetrics.top
//        val textHeight = fontMetrics.descent - fontMetrics.ascent
        Log.i("queen", "textHeight:${textHeight}")

        Log.i("queen", "1   intrinsicHeight:${drawable.intrinsicHeight}")

        val textWidth = textHeight * 54 / 16
        drawable.setBounds(0, 0, textWidth.toInt(), textHeight.toInt())

        Log.i("queen", "2   intrinsicHeight:${drawable.intrinsicHeight}")

        val imageSpan = object : ClickableImageSpan(drawable) {
            override fun onClick(view: View) {
                Log.i("queen", "sssssssssssssssssssss")
            }
        }
        spannableString.setSpan(imageSpan, text.length - 5, text.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        textView.text = (spannableString)
        textView.movementMethod = ClickableMovementMethod.getInstance()

    }


    private fun setEndDrawable2(textView: TextView, drawableId: Int, text: String) {

        val spannableString = SpannableString(text)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#0099EE"))
        val start = text.indexOf("￥")
        val end = text.indexOf("元")
        spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

//        val imageSpan = object :CenterImageSpan(this,drawableId, ImageSpan.ALIGN_BASELINE){
//            override fun onClick(view: View?) {
//                Log.i("queen","xxxxxxxxxxxxxxxxxx")
//            }
//        }
        val drawable = resources.getDrawable(drawableId, null)
        drawable.setBounds(0,0,drawable.minimumWidth,drawable.minimumHeight)
        val imageSpan = object :CenterImageSpan(drawable){
            override fun onClick(view: View?) {
                Log.i("queen","xxxxxxxxxxxxxxxxxx")
            }
        }
        spannableString.setSpan(imageSpan, text.length - 5, text.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        textView.text = (spannableString)
        textView.movementMethod = ClickableMovementMethod.getInstance()

    }

}