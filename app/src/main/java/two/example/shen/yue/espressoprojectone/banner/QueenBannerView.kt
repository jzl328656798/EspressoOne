package two.example.shen.yue.espressoprojectone.banner

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/5/19 3:24 PM
 * Describe: QueenBannerView
 */
class QueenBannerView : LinearLayout {

    private val bannerView by lazy { findViewById<QueenBannerRecyclerView>(R.id.banner_view) }
    private val indicator by lazy { findViewById<QueenBannerIndicator>(R.id.indicator) }

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private fun initView(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.widget_queen_banner_view, this, true)


    }

    fun initData(list: MutableList<QueenBannerData>) {
        bannerView.layoutManager = QueenBannerLinearLayoutManager(context, HORIZONTAL, false)
        bannerView.adapter = QueenBannerAdapter(list)
        indicator.setNumber(list.size)
        bannerView.setPageChangeListener(object : QueenBannerPageChangeListener() {
            override fun onPageSelection(position: Int) {
                Log.i("queen", "position:$position")
                indicator.setPosition(position)
            }
        })

        bannerView.startPlay()

    }

}