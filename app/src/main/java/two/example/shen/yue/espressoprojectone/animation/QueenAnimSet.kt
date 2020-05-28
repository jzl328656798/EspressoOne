package two.example.shen.yue.espressoprojectone.animation

import android.animation.Animator
import android.animation.AnimatorSet

/**
 * Author: Queen
 * Date: 2020/5/18 1:34 PM
 * Describe: AnimSet
 */
class QueenAnimSet : QueenAnim() {

    override var animator: Animator = AnimatorSet()

    private val animatorSet get() = animator as AnimatorSet

    private val animList by lazy { mutableListOf<QueenAnim>() }

    private var isAtStartPoint: Boolean = true

    private var hasReverse: Boolean = false

    fun anim(animCreation: QueenValueAnim.() -> Unit): QueenAnim =
        QueenValueAnim().apply(animCreation).also { it.addListener() }.also { animList.add(it) }

    fun objectAnim(action: QueenObjectAnim.() -> Unit): QueenAnim =
        QueenObjectAnim().apply(action).also { it.setPropertyValueHolder() }.also { it.addListener() }.also { animList.add(it) }

    fun start() {
        if (animatorSet.isRunning) return
        animList.takeIf { hasReverse }?.forEach { anim -> anim.reverse() }
            .also { hasReverse = false }
        if (animList.size == 1) animatorSet.play(animList.first().animator)
        if (isAtStartPoint) {
            animatorSet.start()
            isAtStartPoint = false
        }
    }

    override fun reverse() {
        if (animatorSet.isRunning) return
        animList.takeIf { !hasReverse }?.forEach { anim -> anim.reverse() }.also { hasReverse = true }
        if (!isAtStartPoint) {
            animatorSet.start()
            isAtStartPoint = true
        }
    }

    override fun toBeginning() {
        animList.forEach { it.toBeginning() }
    }

    fun getAnim(index: Int) = animList.takeIf { index in 0 until animList.size }?.let { it[index] }

    fun cancel() {
        animatorSet.cancel()
    }

    infix fun QueenAnim.before(anim: QueenAnim): QueenAnim {
        animatorSet.play(animator).before(anim.animator).let { this.builder = it }
        return anim
    }

    infix fun QueenAnim.with(anim: QueenAnim): QueenAnim {
        if (builder == null) builder = animatorSet.play(animator).with(anim.animator)
        else builder?.with(anim.animator)
        return anim
    }

}

fun animSet(creation: QueenAnimSet.() -> Unit) = QueenAnimSet().apply { creation() }.also { it.addListener() }