package two.example.shen.yue.espressoprojectone.banner

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/5/19 3:37 PM
 * Describe: QueenBannerViewHolder
 */
class QueenBannerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var imageView: ImageView? = null

    init {
        imageView = itemView.findViewById(R.id.image_view)
    }

    fun setImageResource(imageId: Int) {
        imageView?.setImageResource(imageId)
    }

}