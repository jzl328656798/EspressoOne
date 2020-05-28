package two.example.shen.yue.espressoprojectone.test14

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_test14_2.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.banner.QueenBannerData
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


/**
 * Author: Queen
 * Date: 2020/5/14 4:16 PM
 * Describe: Test14Activity1
 */
class Test14Activity2 : BaseActivity() {

    private val list = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test14_2)

        list.add(R.mipmap.img1)
        list.add(R.mipmap.img2)
        list.add(R.mipmap.img3)
        list.add(R.mipmap.img4)
        list.add(R.mipmap.img5)
        list.add(R.mipmap.img6)

        val layoutManager = SmoothLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recycler_view.layoutManager = layoutManager

        recycler_view.adapter = Test14Activity2Adapter(list)


        val scheduledExecutorService: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
        scheduledExecutorService.scheduleAtFixedRate(Runnable {
            recycler_view.smoothScrollToPosition(
                layoutManager.findFirstVisibleItemPosition() + 1
            )
        }, 2000, 5000, TimeUnit.MILLISECONDS)


        val queenList = mutableListOf<QueenBannerData>()
        queenList.add(QueenBannerData(R.mipmap.img1))
        queenList.add(QueenBannerData(R.mipmap.img2))
        queenList.add(QueenBannerData(R.mipmap.img3))
        queenList.add(QueenBannerData(R.mipmap.img4))
        queenList.add(QueenBannerData(R.mipmap.img5))
        queenList.add(QueenBannerData(R.mipmap.img6))

        queen_banner.initData(queenList)


    }


}