package two.example.shen.yue.espressoprojectone.test8

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test8_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/4/15 5:07 PM
 * Describe: TODO
 */
class Test8Activity1 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test8_1)

        btn1_test8.setOnClickListener {
            val intent = Intent(this, Test8Activity2::class.java)
//            intent.putExtra("trips_data", bean)
            startActivityForResult(intent, 111)
            overridePendingTransition(0, 0)
        }

    }
}