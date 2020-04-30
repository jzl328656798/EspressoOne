package two.example.shen.yue.espressoprojectone.test10

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
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

        val str = "这是￥200元一段测试数据这是一段测试数据这是一段测试数据这是一段字这是一段字"
        //setDrawableInTxt(tv_span, str, R.mipmap.restore_default)
        setEndDrawable(tv_span, R.mipmap.restore_default, str, true)

    }


    private fun setEndDrawable(textView: TextView, drawableId: Int, text: String, addImg: Boolean = false) {

        val spannableString = SpannableString(text)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#0099EE"))
        val start = text.indexOf("￥")
        val end = text.indexOf("元")
        spannableString.setSpan(colorSpan, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()

        if (addImg) {
            val drawable = resources.getDrawable(drawableId, null)
            val paint = textView.paint
            val fontMetrics = paint.fontMetrics
            val textHeight = fontMetrics.bottom - fontMetrics.top
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

}