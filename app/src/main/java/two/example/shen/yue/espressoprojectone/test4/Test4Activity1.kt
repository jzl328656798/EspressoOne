package two.example.shen.yue.espressoprojectone.test4

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import kotlinx.android.synthetic.main.activity_test4_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/3/26 3:11 PM
 * Describe: Test4Activity1
 */
class Test4Activity1 : BaseActivity() {

    private val animatorSet = AnimatorSet()
    private val animatorSetLeave = AnimatorSet()
    private var width = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test4_1)
        tv_subscribe_tag.text = "超级智能\n驾驶行程报告"
        iv_back.setOnClickListener {
            finish()
        }
        tv_subscribe.setOnClickListener {
            animatorSetLeave.play(getLeftLeaveAnimation(ll_sc, 400))
                    .with(getLeftLeaveAnimation(tv_plate_number, 400, 200))
                    .with(getViewHideAnimation(tv_subscribe_tag, 300))
                    .with(getViewHideAnimation(tv_subscribe, 200, 650))
                    .with(getSteeringWheelLeaveAnimation())
                    .with(getSubscribeUpMoveAnimation())
            animatorSetLeave.start()
        }
        val defaultDisplay = windowManager.defaultDisplay
        val point = Point()
        defaultDisplay.getSize(point)
        width = point.x
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            animatorSet.play(getLeftEnterAnimation(ll_sc, 600, tension = 1f))
                    .with(getLeftEnterAnimation(tv_plate_number, 300, 400))
                    .with(getLeftEnterAnimation(tv_subscribe, 300, 500))
                    .with(getSteeringWheelAnimation())
                    .with(getViewShowAnimation(iv_steering_wheel, 600, 400))
                    .with(getViewShowAnimation(tv_subscribe_tag, 300, 600))
                    .with(getViewShowAnimation(iv_steering_wheel_indicate, 300, 700))
                    .with(getViewShowAnimation(iv_steering_wheel_used, 300, 700))
            animatorSet.start()
        }
    }


    /**
     * 获取从左边进入动画
     */
    private fun getLeftEnterAnimation(view: View, duration: Long, startDelay: Long = 0, tension: Float = 0f): Animator {
        val animator = ValueAnimator.ofInt(0, view.width + getLeftDistance())
        animator.duration = duration
        if (startDelay > 0) {
            animator.startDelay = startDelay
        }
        if (tension > 0) {
            animator.interpolator = OvershootInterpolator(tension)
        }
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            view.layout(-view.width + curValue, view.top, curValue, view.bottom)
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {}

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                view.visibility = View.VISIBLE
            }

        })
        return animator
    }

    /**
     * 获取显示动画
     */
    private fun getViewShowAnimation(view: View, duration: Long, startDelay: Long = 0): Animator {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = duration
        if (startDelay > 0) {
            animator.startDelay = startDelay
        }
        animator.addUpdateListener {
            val curValue = it.animatedValue as Float
            view.alpha = curValue
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {}

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                view.visibility = View.VISIBLE
            }

        })
        return animator
    }

    /**
     * 方向盘进入动画
     */
    private fun getSteeringWheelAnimation(): Animator {
        val animator = ValueAnimator.ofInt(0, cl_steering_wheel.width)
        animator.duration = 400
        animator.startDelay = 400
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            cl_steering_wheel.layout(width - curValue, cl_steering_wheel.top, width + cl_steering_wheel.width - curValue, cl_steering_wheel.bottom)
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {}

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                cl_steering_wheel.visibility = View.VISIBLE
            }
        })
        return animator
    }


    /**
     * 获取从左边离开动画
     */
    private fun getLeftLeaveAnimation(view: View, duration: Long, startDelay: Long = 0): Animator {
        val animator = ValueAnimator.ofInt(0, view.width + getLeftDistance())
        animator.duration = duration
        if (startDelay > 0) {
            animator.startDelay = startDelay
        }
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            view.layout(getLeftDistance() - curValue, view.top, view.width + getLeftDistance() - curValue, view.bottom)
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {}

        })
        return animator
    }

    /**
     * 获取隐藏动画
     */
    private fun getViewHideAnimation(view: View, duration: Long, startDelay: Long = 0): Animator {
        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = duration
        if (startDelay > 0) {
            animator.startDelay = startDelay
        }
        animator.addUpdateListener {
            val curValue = it.animatedValue as Float
            view.alpha = 1f - curValue
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                view.visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {}

        })
        return animator
    }


    /**
     * 方向盘离开动画
     */
    private fun getSteeringWheelLeaveAnimation(): Animator {
        val animator = ValueAnimator.ofInt(0, cl_steering_wheel.width)
        animator.duration = 200
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            cl_steering_wheel.layout(width - cl_steering_wheel.width + curValue, cl_steering_wheel.top, width + curValue, cl_steering_wheel.bottom)
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                cl_steering_wheel.visibility = View.INVISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {

            }
        })
        return animator
    }

    /**
     * 获取订阅向上移动动画
     */
    private fun getSubscribeUpMoveAnimation(): Animator {
        val subscribeTop = tv_subscribe.top
        val subscribeBottom = tv_subscribe.bottom
        val animator = ValueAnimator.ofInt(0, subscribeTop - iv_back.bottom)
        animator.duration = 450
        animator.startDelay = 400
        animator.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_subscribe.layout(tv_subscribe.left, subscribeTop - curValue, tv_subscribe.right, subscribeBottom - curValue)
        }
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                startPage()
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {

            }
        })
        return animator
    }


    /**
     * 跳转页面
     */
    private fun startPage() {
        startActivity(Intent(this, Test4Activity2::class.java))
        finish()
        overridePendingTransition(0, 0)
    }

}