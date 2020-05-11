package two.example.shen.yue.espressoprojectone.test3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_test3_1.*
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/26 1:31 PM
 * Describe: Test3Activity1
 */
class Test3Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test3_1)
        tv_subscribe.setOnClickListener {
            test2()
        }

    }

    private fun test2() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.test1)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                tv_subscribe.animation = null
                test1()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        tv_subscribe.startAnimation(animation)
    }

    private fun test1() {
        val intent = Intent(this, Test3Activity2::class.java)
        val pairTv1 = androidx.core.util.Pair<View, String>(tv_test, "simple text view1")
        val pairTv2 = androidx.core.util.Pair<View, String>(tv_test, "simple text view2")
        val pairTv3 = androidx.core.util.Pair<View, String>(tv_test, "simple text view3")
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairTv1, pairTv2, pairTv3)
        startActivity(intent, optionsCompat.toBundle())
    }

}