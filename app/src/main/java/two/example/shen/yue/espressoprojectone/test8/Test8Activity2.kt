package two.example.shen.yue.espressoprojectone.test8

import android.app.Activity
import android.os.Bundle
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/4/15 5:07 PM
 * Describe: TODO
 */
class Test8Activity2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test8_2)



    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}