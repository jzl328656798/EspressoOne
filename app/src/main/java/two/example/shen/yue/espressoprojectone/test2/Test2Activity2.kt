package two.example.shen.yue.espressoprojectone.test2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_test2_2.*
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/23 4:39 PM
 * Describe: Test2Activity2
 */
class Test2Activity2 : AppCompatActivity() {

    private val tvList = ArrayList<TextView>()
    private var currentFragment = Fragment()
    private val a = Test2FragmentA()
    private val b = Test2FragmentB()
    private val c = Test2FragmentC()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2_2)
        tvList.add(tv1)
        tvList.add(tv2)
        tvList.add(tv3)
        tv1.setOnClickListener {
            switchTag(tv1)
            switchFragment(a)
        }
        tv2.setOnClickListener {
            switchTag(tv2)
            switchFragment(b)
        }
        tv3.setOnClickListener {
            switchTag(tv3)
            switchFragment(c)
        }
        switchTag(tv1)
        switchFragment(a)
    }

    private fun switchTag(textView: TextView) {
        tvList.forEach {
            if (it == textView) {
                it.textSize = 16f
                it.paint.isFakeBoldText = true
            } else {
                it.textSize = 14f
                it.paint.isFakeBoldText = false
            }
        }
    }

    private fun switchFragment(targetFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (!targetFragment.isAdded) {
            transaction.hide(currentFragment)
            transaction.add(R.id.fragment, targetFragment, targetFragment::class.java.name)
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment
        transaction.commit()
    }

}