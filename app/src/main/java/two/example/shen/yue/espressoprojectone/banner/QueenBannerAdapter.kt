package two.example.shen.yue.espressoprojectone.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/5/19 3:36 PM
 * Describe: QueenBannerAdapter
 */
class QueenBannerAdapter(val list: MutableList<QueenBannerData>) : RecyclerView.Adapter<QueenBannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueenBannerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_test14_activity2_adapter, parent, false)
        return QueenBannerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun onBindViewHolder(holder: QueenBannerViewHolder, position: Int) {
        holder.setImageResource(list[position % list.size].imgId)
    }
}