package two.example.shen.yue.espressoprojectone.test16

import android.annotation.SuppressLint
import android.os.Bundle
import com.jem.liquidswipe.clippathprovider.LiquidSwipeClipPathProvider
import kotlinx.android.synthetic.main.activity_test16_2.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/7/14 9:07 AM
 * Describe: biff
 */
class Test16Activity2 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test16_2)

        viewpager.adapter = Test16Activity2Adapter(supportFragmentManager)
        val liquidSwipeClipPathProviders = Array(titleArray.count()) {
            LiquidSwipeClipPathProvider()
        }
        viewpager.adapter = Test16Activity2PagerAdapter(this, liquidSwipeClipPathProviders)
        viewpager.setOnTouchListener { _, event ->
            val waveCenterY = event.y
            liquidSwipeClipPathProviders.map {
                it.waveCenterY = waveCenterY
            }
            false
        }
        viewpager.setCurrentItem(titleArray.count() * 10, false)
    }





}