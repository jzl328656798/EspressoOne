package two.example.shen.yue.espressoprojectone.animation

import android.animation.Animator
import android.animation.ValueAnimator

/**
 * Author: Queen
 * Date: 2020/5/18 2:06 PM
 * Describe: QueenValueAnim
 */
class QueenValueAnim : QueenAnim() {

    override var animator: Animator = ValueAnimator()

    private val valueAnimator
        get() = animator as ValueAnimator

    var valueList: Any? = null
        set(value) {
            field = value
            value?.let {
                when (it) {
                    is FloatArray -> valueAnimator.setFloatValues(*it)
                    is IntArray -> valueAnimator.setIntValues(*it)
                    else -> throw IllegalArgumentException("unsupported value type")
                }
            }
        }

    var action: ((Any) -> Unit)? = null
        set(value) {
            field = value
            valueAnimator.addUpdateListener { valueAnimator ->
                valueAnimator.animatedValue.let { value?.invoke(it) }
            }
        }

    var repeatCount
        get() = 0
        set(value) {
            valueAnimator.repeatCount = value
        }

    var repeatMode
        get() = ValueAnimator.RESTART
        set(value) {
            valueAnimator.repeatMode = value
        }

    override fun reverse() {
        valueList?.let {
            when (it) {
                is FloatArray -> {
                    it.reverse()
                    valueAnimator.setFloatValues(*it)
                }
                is IntArray -> {
                    it.reverse()
                    valueAnimator.setIntValues(*it)
                }
                else -> throw IllegalArgumentException("unsupported type of value")
            }
        }
    }

    override fun toBeginning() {}

}