package two.example.shen.yue.espressoprojectone.test2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_test2_3.*
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/3/23 4:39 PM
 * Describe: Test2Activity3
 */
class Test2Activity3 : AppCompatActivity() {

    private var b = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2_3)
        tv_describe.text = "订阅行程报告后可以查\n看SC最新行程报告及\n历史行程报告"
        tv_subscribe.setOnClickListener {
            if (b) {
                b = !b
                test1()
            }
        }
        iv_back.setOnClickListener {
            if (!b) {
                b = !b
                test4()
            }
        }
        tv_test_sc1.setOnClickListener { test5() }
        tv_test_sc2.setOnClickListener { test5() }
        tv_test_sc3.setOnClickListener { test5() }
    }

    private fun test5() {
        val intent = Intent(this, Test2Activity2::class.java)
        val pairTv1 = androidx.core.util.Pair<View, String>(tv_test_sc1, "simple text view1")
        val pairTv2 = androidx.core.util.Pair<View, String>(tv_test_sc2, "simple text view2")
        val pairTv3 = androidx.core.util.Pair<View, String>(tv_test_sc3, "simple text view3")
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairTv1, pairTv2, pairTv3)
        startActivity(intent, optionsCompat.toBundle())
    }

    private fun test4() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.test4)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                ll_test2.animation = null
                ll_test2.visibility = View.GONE
                test2()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        ll_test2.startAnimation(animation)
    }

    private fun test3() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.test3)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                ll_test2.animation = null
            }

            override fun onAnimationStart(animation: Animation?) {
                ll_test2.visibility = View.VISIBLE
            }

        })
        ll_test2.startAnimation(animation)
    }

    private fun test2() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.test2)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                ll_test1.animation = null
            }

            override fun onAnimationStart(animation: Animation?) {
                ll_test1.visibility = View.VISIBLE
            }

        })
        ll_test1.startAnimation(animation)
    }

    private fun test1() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.test1)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                ll_test1.animation = null
                ll_test1.visibility = View.GONE
                test3()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        ll_test1.startAnimation(animation)
    }
}