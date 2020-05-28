package two.example.shen.yue.espressoprojectone.animation

import android.animation.Animator
import android.animation.AnimatorSet
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

/**
 * Author: Queen
 * Date: 2020/5/18 1:35 PM
 * Describe: QueenAnim
 */
abstract class QueenAnim {

    abstract var animator: Animator

    var builder: AnimatorSet.Builder? = null

    var duration
        get() = 300L
        set(value) {
            animator.duration = value
        }

    var interpolator
        get() = LinearInterpolator() as Interpolator
        set(value) {
            animator.interpolator = value
        }


    var delay
        get() = 0L
        set(value) {
            animator.startDelay = value
        }

    var onRepeat: ((Animator) -> Unit)? = null

    var onEnd: ((Animator) -> Unit)? = null

    var onCancel: ((Animator) -> Unit)? = null

    var onStart: ((Animator) -> Unit)? = null

    abstract fun reverse()

    abstract fun toBeginning()

    internal fun addListener() {
        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                animation?.let { onRepeat?.invoke(it) }
            }

            override fun onAnimationEnd(animation: Animator?) {
                animation?.let { onEnd?.invoke(it) }
            }

            override fun onAnimationCancel(animation: Animator?) {
                animation?.let { onCancel?.invoke(it) }
            }

            override fun onAnimationStart(animation: Animator?) {
                animation?.let { onStart?.invoke(it) }
            }

        })
    }

}