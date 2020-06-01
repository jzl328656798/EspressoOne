package two.example.shen.yue.espressoprojectone.test15

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test15_2.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.event.QueenEventBus
import two.example.shen.yue.espressoprojectone.event.QueenSubscribe
import two.example.shen.yue.espressoprojectone.event.QueenThreadMode


/**
 * Author: Queen
 * Date: 2020/5/29 1:28 PM
 * Describe: Test15Activity
 */
class Test15Activity2 : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test15_2)

        btn.setOnClickListener { startActivity(Intent(this, Test15Activity3::class.java)) }


        QueenEventBus.register(this)
    }

    @QueenSubscribe(QueenThreadMode.MAIN)
    fun test(bean: Test15Bean1) {
        log("test:$bean")
    }


    @QueenSubscribe(QueenThreadMode.MAIN)
    fun test1(bean: Test15Bean1) {
        log("test1:$bean")
    }

}