package two.example.shen.yue.espressoprojectone.test2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_test2_a.*
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/24 3:32 PM
 * Describe: Test2FragmentA
 */
class Test2FragmentA : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test2_a, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        test()
    }

    private fun test() {
        val animation = AnimationUtils.loadAnimation(activity, R.anim.test5)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                page.animation = null
            }

            override fun onAnimationStart(animation: Animation?) {
                page.visibility = View.VISIBLE
            }

        })
        page.startAnimation(animation)
    }
}