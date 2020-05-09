package two.example.shen.yue.espressoprojectone.test11

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test11_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/4/20 2:21 PM
 * Describe: TODO
 */
class Test11Activity1 : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test11_1)


        btn1.setOnClickListener {

            log("100dp=${100f.dp2px()}")

            log(iv_test.left)
            log(iv_test.right)
            log(iv_test.top)
            log(iv_test.bottom)

            log(iv_test.x)
            log(iv_test.y)

            log(iv_test.translationX)
            log(iv_test.translationY)
        }
        btn2.setOnClickListener {
            iv_test.translationX = 100f
            log(iv_test.translationX)
        }
        btn3.setOnClickListener { }
        btn4.setOnClickListener { }
    }

}