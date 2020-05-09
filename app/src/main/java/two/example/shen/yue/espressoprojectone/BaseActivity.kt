package two.example.shen.yue.espressoprojectone

import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Author: Queen
 * Date: 2020/3/30 10:24 PM
 * Describe: BaseActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    fun Float.sp2px(): Float {
        val fontScale = resources.displayMetrics.scaledDensity
        return this * fontScale + 0.5f
    }

    fun Float.dp2px(): Float {
        val fontScale = resources.displayMetrics.density
        return this * fontScale + 0.5f
    }

    fun getLeftDistance() = 30f.dp2px().toInt()

    fun log(message: Any) {
        Log.i("queen", message.toString())
    }

}