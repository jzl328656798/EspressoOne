package two.example.shen.yue.espressoprojectone.test13

import android.os.Bundle
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.test13.observer.Test13Activity4Observer

/**
 * Author: Queen
 * Date: 2020/4/20 2:21 PM
 * Describe: Test13Activity4
 */
class Test13Activity4 : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(Test13Activity4Observer())
        setContentView(R.layout.activity_test13_4)
    }

}