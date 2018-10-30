package two.example.shen.yue.espressoprojectone.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import two.example.shen.yue.espressoprojectone.R

/**
 * Created by queen on 2018/10/29.
 * Author: Queen
 * Date: 2018/10/29
 * Time: 上午10:03
 * Email: zhuolei.jiang@softlinker.com & jiangzhuolei@126.com
 * Describe: TODO
 */
object KotlinToast1 {

    fun toast1(mContext: Context) {

        var view = View.inflate(mContext, R.layout.toast1, null)
        var tv_toast1 = view.findViewById<TextView>(R.id.tv_toast1)
        var params = LinearLayout.LayoutParams(200, 200)
        tv_toast1.layoutParams = params
        var toast = Toast(mContext)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = view

        toast.show()

    }
}