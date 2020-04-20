package two.example.shen.yue.espressoprojectone.test7

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test7_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/4/10 3:56 PM
 * Describe: Test7Activity1
 */
class Test7Activity1:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test7_1)

        test7_btn1.setOnClickListener { test7_view1.setData() }
        test7_btn2.setOnClickListener { test7_view1.setData1() }

    }

}