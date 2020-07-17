package two.example.shen.yue.espressoprojectone.test16

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.jem.liquidswipe.base.LiquidSwipeLayout
import com.jem.liquidswipe.clippathprovider.LiquidSwipeClipPathProvider
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/7/17 1:30 PM
 * Describe: Test16Activity2PagerAdapter
 */

class Test16Activity2PagerAdapter (private val context: Context,private val liquidSwipeClipPathProviders: Array<LiquidSwipeClipPathProvider>):PagerAdapter(){
    override fun isViewFromObject(view: View, any: Any): Boolean {
        return any == view
    }

    override fun getCount(): Int {
        return titleArray.count()*20
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        container.removeView(any as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(context).inflate(R.layout.fragment_test16_2_dummy, container, false)

        layout.setBackgroundColor(backgroundColorArray[(position % titleArray.count())])

        layout.findViewById<LottieAnimationView>(R.id.lottieAnimationView).setAnimation(
            resourceArray[(position % titleArray.count())]
        )
        layout.findViewById<LottieAnimationView>(R.id.lottieAnimationView).repeatCount =
            LottieDrawable.INFINITE
        layout.findViewById<LottieAnimationView>(R.id.lottieAnimationView).repeatMode =
            LottieDrawable.REVERSE
        layout.findViewById<LottieAnimationView>(R.id.lottieAnimationView).playAnimation()

        layout.findViewById<TextView>(R.id.fragment_textview).text =
            titleArray[(position % titleArray.count())]

        (layout as? LiquidSwipeLayout)?.clipPathProvider = liquidSwipeClipPathProviders[(position % titleArray.count())]

        container.addView(layout)
        return layout
    }

}