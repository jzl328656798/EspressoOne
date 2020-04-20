package two.example.shen.yue.espressoprojectone.test5

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import com.gm.sdk.use.AccountManage
import kotlinx.android.synthetic.main.activity_test5_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/3/31 4:51 PM
 * Describe: Test5Activity1
 */
class Test5Activity1 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test5_1)

        btn1.setOnClickListener {
            Thread(Runnable {
                val str = AccountManage.getInstance(this@Test5Activity1).login("13917275427", "Pass1234", "LSGPC54U7EF051911")
                Log.i("queen", "str:${str}")
            }).start()
        }

        btn2.setOnClickListener {
            Thread(Runnable {
                val ticket = AccountManage.getInstance(this@Test5Activity1).ticket
                Log.i("queen", "ticket:${ticket}")
            }).start()
        }
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val animatorSet = AnimatorSet()
            val animator = ValueAnimator.ofFloat(0f, 65f)
            animator.duration = 5000
            animator.startDelay = 1000
            animator.addUpdateListener {
                val curValue = it.animatedValue as Float
                t_5_v_1.progress = curValue
                t_5_v_1.invalidate()
            }

            val top = t_5_v_1.top
            val bottom = t_5_v_1.bottom

            val animator2 = ValueAnimator.ofInt(0, t_5_v_1.height)
            animator2.duration = 5000
            animator2.addUpdateListener {
                val curValue = it.animatedValue as Int
                t_5_v_1.layout(t_5_v_1.left, top + t_5_v_1.height - curValue, t_5_v_1.right, bottom + t_5_v_1.height - curValue)
            }

            animatorSet.play(animator).with(animator2)
            animatorSet.start()
        }
    }
}