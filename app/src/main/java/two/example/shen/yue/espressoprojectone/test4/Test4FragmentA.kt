package two.example.shen.yue.espressoprojectone.test4

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_test4_a.*
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/27 1:58 PM
 * Describe: Test4FragmentA
 */
class Test4FragmentA : Fragment(), MyOnTouchListener {

    private val animatorSet = AnimatorSet()
    private var isMeasured = true
    private val listener = SVCGestureListener()
    private var mGestureDetector: GestureDetector? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test4_a, container, false)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.i("queen", "hidden:$hidden")
        if (!hidden) {
            test6_view1.startAnimate(456, 59)
            test6_view2.startAnimate(200)
            sc_r_v2.startAnimate(200f)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val observer = ll_helper.viewTreeObserver
        observer.addOnDrawListener {
            if (isMeasured) {
                animatorSet.play(getHelperAlphaAnimation())
                        .with(getHelpMoveAnimation())
                        .with(getPieChartAlphaAnimation())
                        .with(getPieChartMoveAnimation())
                        .with(getHistogramMoveAnimation())
                        .with(getHistogramAlphaAnimation())
                        .with(getHistogramScheduleAnimation())
                        .with(getTotalDistanceAlphaAnimation())
                        .with(getTotalDistanceMoveAnimation())
                animatorSet.start()
                isMeasured = false
            }
        }
        if (requireActivity() is Test4Activity2) {
            (requireActivity() as Test4Activity2).registerMyOnTouchListener(this)
        }

        mGestureDetector = GestureDetector(requireActivity(), listener)
    }

    override fun onTouch(ev: MotionEvent): Boolean {
        //Log.i("queen", "onTouch")
        return mGestureDetector?.onTouchEvent(ev) ?: false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (requireActivity() is Test4Activity2) {
            (requireActivity() as Test4Activity2).unregisterMyOnTouchListener(this)
        }
    }

    private fun getHelperAlphaAnimation(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 220
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            ll_helper.alpha = curValue
        }
        return animatorAlpha
    }

    private fun getHelpMoveAnimation(): Animator {
        val top = ll_helper.top + 80
        val bottom = ll_helper.bottom + 80
        val animatorMove = ValueAnimator.ofInt(0, 80)
        animatorMove.duration = 300
        animatorMove.startDelay = 300
        animatorMove.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                ll_helper.visibility = View.VISIBLE
            }
        })
        animatorMove.addUpdateListener {
            val curValue = it.animatedValue as Int
            ll_helper.layout(ll_helper.left, top - curValue, ll_helper.right, bottom - curValue)
        }
        return animatorMove
    }

    private fun getPieChartAlphaAnimation(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 550
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            test6_view1.alpha = curValue
        }
        return animatorAlpha
    }

    private fun getPieChartMoveAnimation(): Animator {
        val top = test6_view1.top + 80
        val bottom = test6_view1.bottom + 80
        val animatorMove = ValueAnimator.ofInt(0, 80)
        animatorMove.duration = 300
        animatorMove.startDelay = 550
        animatorMove.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                test6_view1.startAnimate(456, 59)
                test6_view2.startAnimate(200)
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                test6_view1.visibility = View.VISIBLE
            }
        })
        animatorMove.addUpdateListener {
            val curValue = it.animatedValue as Int
            test6_view1.layout(test6_view1.left, top - curValue, test6_view1.right, bottom - curValue)
        }
        return animatorMove
    }

    private fun getTotalDistanceAlphaAnimation(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 800
        animatorAlpha.startDelay = 600
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            test6_view2.alpha = curValue
        }
        return animatorAlpha
    }

    private fun getTotalDistanceMoveAnimation(): Animator {
        val top = test6_view2.top + 160
        val bottom = test6_view2.bottom + 160
        val left = test6_view2.left
        val right = test6_view2.right
        val animatorMove = ValueAnimator.ofInt(0, 160)
        animatorMove.interpolator = OvershootInterpolator(5f)
        animatorMove.duration = 800
        animatorMove.startDelay = 600
        animatorMove.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                test6_view2.visibility = View.VISIBLE
            }
        })
        animatorMove.addUpdateListener {
            val curValue = it.animatedValue as Int
            test6_view2.layout(left, top - curValue, right, bottom - curValue)
        }
        return animatorMove
    }


    private fun getHistogramAlphaAnimation(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 980
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            sc_r_v2.alpha = curValue
        }
        return animatorAlpha
    }

    private fun getHistogramMoveAnimation(): Animator {
        val top = sc_r_v2.top + 80
        val bottom = sc_r_v2.bottom + 80
        val animatorMove = ValueAnimator.ofInt(0, 80)
        animatorMove.duration = 300
        animatorMove.startDelay = 980
        animatorMove.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                sc_r_v2.visibility = View.VISIBLE
            }
        })
        animatorMove.addUpdateListener {
            val curValue = it.animatedValue as Int
            sc_r_v2.layout(sc_r_v2.left, top - curValue, sc_r_v2.right, bottom - curValue)
        }
        return animatorMove
    }

    private fun getHistogramScheduleAnimation(): Animator {
        val animator = ValueAnimator.ofFloat(0f, 200f)
        animator.duration = 350
        animator.startDelay = 1150
        animator.addUpdateListener {
            val curValue = it.animatedValue as Float
            sc_r_v2.setSchedule(curValue, curValue, curValue.toInt().toString())
        }
        return animator
    }

}