package two.example.shen.yue.espressoprojectone.test4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_test4_c.*
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/27 1:58 PM
 * Describe: Test4FragmentC
 */
class Test4FragmentC : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test4_c, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startAnimate()
    }

    private fun startAnimate() {
        sc_r_v3.startAnimate(88)
        sc_r_v4.startAnimate(88)
        sc_r_v5.startAnimate(200f)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            startAnimate()
        }
    }
}