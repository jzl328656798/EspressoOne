package two.example.shen.yue.espressoprojectone.test4

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_test4_2.*
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/3/26 3:11 PM
 * Describe: Test4Activity1
 */
class Test4Activity2 : AppCompatActivity() {

    private val animatorSet = AnimatorSet()

    private val textViewList = ArrayList<TextView>()
    private var indicatorStart = 0f
    private var currentFragment = Fragment()
    private val a = Test4FragmentA()
    private val b = Test4FragmentB()
    private val c = Test4FragmentC()

    private val onTouchListeners = ArrayList<MyOnTouchListener>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test4_2)
        iv_back.setOnClickListener {
            startActivity(Intent(this, Test4Activity1::class.java))
            finish()
        }

        textViewList.add(tv_new_report)
        textViewList.add(tv_history_report)
        textViewList.add(tv_used)
        tv_new_report.setOnClickListener {
            switchTitle(tv_new_report)
            moveIndicator(tv_new_report)
            switchFragment(a)
        }
        tv_history_report.setOnClickListener {
            switchTitle(tv_history_report)
            moveIndicator(tv_history_report)
            switchFragment(b)
        }
        tv_used.setOnClickListener {
            switchTitle(tv_used)
            moveIndicator(tv_used)
            switchFragment(c)
        }
        switchTitle(tv_new_report)
        switchFragment(a)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        //Log.i("queen", "dispatchTouchEvent")
        onTouchListeners.forEach { it.onTouch(ev) }
        return super.dispatchTouchEvent(ev)
    }

    fun registerMyOnTouchListener(myOnTouchListener: MyOnTouchListener) {
        onTouchListeners.add(myOnTouchListener)
    }

    fun unregisterMyOnTouchListener(myOnTouchListener: MyOnTouchListener) {
        onTouchListeners.remove(myOnTouchListener)
    }

    private fun switchTitle(textView: TextView) {
        textViewList.forEach {
            if (it == textView) {
                it.setTextColor(resources.getColor(R.color.color_B5A36A))
                it.paint.isFakeBoldText = true
                it.textSize = 15f
            } else {
                it.setTextColor(resources.getColor(R.color.color_787878))
                it.paint.isFakeBoldText = false
                it.textSize = 14f
            }
        }
    }

    private fun switchFragment(targetFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
//        transaction.setCustomAnimations(
//                R.anim.slide_right_in,
//                R.anim.slide_left_out,
//                R.anim.slide_left_in,
//                R.anim.slide_right_out
//        )
        if (!targetFragment.isAdded) {
            transaction.hide(currentFragment)
            transaction.add(R.id.fragment, targetFragment, targetFragment::class.java.name)
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment
        transaction.commit()
    }

    private fun moveIndicator(textView: TextView) {
        val move = textView.left.toFloat() - (textView.right - textView.left) / 2 + (indicator.right - indicator.left)
        val translateAnim = TranslateAnimation(Animation.ABSOLUTE, indicatorStart, Animation.ABSOLUTE, move,
                Animation.ABSOLUTE, 0f, Animation.ABSOLUTE, 0f)
        translateAnim.duration = 500
        translateAnim.fillAfter = true
        indicator.startAnimation(translateAnim)
        indicatorStart = move
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            animatorSet.play(getNewReportAlpha())
                    .with(getNewReportAlpha())
                    .with(getHistoryReportAlpha())
                    .with(getHistoryReportMove())
                    .with(getUsedReportAlpha())
                    .with(getUsedReportMove())
                    .with(getIndicatorAlpha())
            animatorSet.start()
        }
    }

    private fun getIndicatorAlpha(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 150
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            indicator.alpha = curValue
        }
        animatorAlpha.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                v_line.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                indicator.visibility = View.VISIBLE
            }
        })
        return animatorAlpha
    }

    private fun getNewReportAlpha(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            tv_new_report.alpha = curValue
        }
        animatorAlpha.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                tv_new_report.visibility = View.VISIBLE
                tv_new_report.setTextColor(resources.getColor(R.color.color_B5A36A))
                tv_new_report.paint.isFakeBoldText = true
                tv_new_report.textSize = 15f
            }
        })
        return animatorAlpha
    }

    private fun getHistoryReportAlpha(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 280
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            tv_history_report.alpha = curValue
        }
        animatorAlpha.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                tv_history_report.visibility = View.VISIBLE
            }
        })
        return animatorAlpha
    }

    private fun getHistoryReportMove(): Animator {
        val move = tv_history_report.left - tv_new_report.left
        val left = tv_new_report.left
        val right = tv_new_report.right
        val top = tv_history_report.top
        val bottom = tv_history_report.bottom
        val animatorAlpha = ValueAnimator.ofInt(0, move)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 280
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_history_report.layout(left + curValue, top, right + curValue, bottom)
        }
        return animatorAlpha
    }

    private fun getUsedReportAlpha(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 100
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            tv_used.alpha = curValue
        }
        animatorAlpha.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                tv_used.visibility = View.VISIBLE
            }
        })
        return animatorAlpha
    }

    private fun getUsedReportMove(): Animator {
        val move = tv_used.left - tv_new_report.left
        val left = tv_new_report.left
        val right = tv_new_report.right
        val top = tv_used.top
        val bottom = tv_used.bottom
        val animatorAlpha = ValueAnimator.ofInt(0, move)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 100
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_used.layout(left + curValue, top, right + curValue, bottom)
        }
        return animatorAlpha
    }

}