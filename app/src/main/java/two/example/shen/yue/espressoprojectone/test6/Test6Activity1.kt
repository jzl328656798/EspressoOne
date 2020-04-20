package two.example.shen.yue.espressoprojectone.test6

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import kotlinx.android.synthetic.main.activity_test6_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/4/2 9:26 AM
 * Describe: Test6Activity1
 */
class Test6Activity1 : BaseActivity() {

    private val animatorSet = AnimatorSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test6_1)
        iv_back.setOnClickListener { finish() }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            animatorSet.play(getHelperAlphaAnimation())
                    .with(getHelpMoveAnimation())
                    .with(getPieChartAlphaAnimation())
                    .with(getPieChartMoveAnimation())
                    .with(getTotalDistanceAlphaAnimation())
                    .with(getTotalDistanceMoveAnimation())

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
        animatorAlpha.startDelay = 850
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
        animatorAlpha.startDelay = 700
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
        animatorAlpha.startDelay = 980
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
        animatorAlpha.startDelay = 980
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_history_report.layout(left + curValue, top, right + curValue, bottom)
        }
        return animatorAlpha
    }

    private fun getUsedReportAlpha(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 800
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
        animatorAlpha.startDelay = 800
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Int
            tv_used.layout(left + curValue, top, right + curValue, bottom)
        }
        return animatorAlpha
    }


    private fun getHelperAlphaAnimation(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 920
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            ll_helper.alpha = curValue
        }
        return animatorAlpha
    }

    private fun getHelpMoveAnimation(): Animator {
        val top = ll_helper.top + 80
        val bottom = ll_helper.bottom + 80
        val left = ll_helper.left
        val right = ll_helper.right
        val animatorMove = ValueAnimator.ofInt(0, 80)
        animatorMove.duration = 300
        animatorMove.startDelay = 1000
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
            ll_helper.layout(left, top - curValue, right, bottom - curValue)
        }
        return animatorMove
    }

    private fun getPieChartAlphaAnimation(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 300
        animatorAlpha.startDelay = 1250
        animatorAlpha.addUpdateListener {
            val curValue = it.animatedValue as Float
            test6_view1.alpha = curValue
        }
        return animatorAlpha
    }

    private fun getPieChartMoveAnimation(): Animator {
        val top = test6_view1.top + 80
        val bottom = test6_view1.bottom + 80
        val left = test6_view1.left
        val right = test6_view1.right
        val animatorMove = ValueAnimator.ofInt(0, 80)
        animatorMove.duration = 300
        animatorMove.startDelay = 1250
        animatorMove.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                test6_view1.startAnimate(500, 59)
                test6_view2.startAnimate(8765)
            }

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {
                test6_view1.visibility = View.VISIBLE
            }
        })
        animatorMove.addUpdateListener {
            val curValue = it.animatedValue as Int
            test6_view1.layout(left, top - curValue, right, bottom - curValue)
        }
        return animatorMove
    }

    private fun getTotalDistanceAlphaAnimation(): Animator {
        val animatorAlpha = ValueAnimator.ofFloat(0f, 1f)
        animatorAlpha.duration = 800
        animatorAlpha.startDelay = 1300
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
        animatorMove.startDelay = 1300
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
}