package two.example.shen.yue.espressoprojectone.banner

import androidx.recyclerview.widget.RecyclerView

/**
 * Author: Queen
 * Date: 2020/5/19 1:09 PM
 * Describe: 页面监听
 */
abstract class QueenBannerPageChangeListener : RecyclerView.OnScrollListener() {

    abstract fun onPageSelection(position: Int)

}