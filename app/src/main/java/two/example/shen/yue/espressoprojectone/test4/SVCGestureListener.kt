package two.example.shen.yue.espressoprojectone.test4

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import kotlin.math.abs

/**
 * Author: Queen
 * Date: 2020/4/23 1:16 PM
 * Describe: TODO
 */
class SVCGestureListener : GestureDetector.OnGestureListener {

    private val FLING_MIN_DISTANCE = 200
    private val FLING_MIN_VELOCITY = 0

    override fun onShowPress(e: MotionEvent?) {
        Log.i("queen", "onShowPress")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.i("queen", "onSingleTapUp")
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.i("queen", "onDown")
        return false
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityY: Float, velocityX: Float): Boolean {
        Log.i("queen", "onFling")
        if (e1.x - e2.x > FLING_MIN_DISTANCE && abs(velocityX) > FLING_MIN_VELOCITY) {
            Log.i("queen","向左手势")
        } else if (e2.x - e1.x > FLING_MIN_DISTANCE && abs(velocityX) > FLING_MIN_VELOCITY) {
            Log.i("queen","向右手势")
        }
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.i("queen", "onScroll")
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.i("queen", "onLongPress")
    }
}