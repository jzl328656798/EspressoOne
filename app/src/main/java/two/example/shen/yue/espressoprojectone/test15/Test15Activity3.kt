package two.example.shen.yue.espressoprojectone.test15

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test15_3.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.event.QueenEventBus


/**
 * Author: Queen
 * Date: 2020/5/29 1:28 PM
 * Describe: Test15Activity
 */
class Test15Activity3 : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test15_3)

        btn.setOnClickListener { QueenEventBus.post(Test15Bean1("Queen", "123456789")) }
    }


}