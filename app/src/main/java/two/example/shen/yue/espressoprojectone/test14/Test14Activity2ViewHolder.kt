package two.example.shen.yue.espressoprojectone.test14

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/5/19 10:52 AM
 * Describe: Test14Activity2ViewHolder
 */
class Test14Activity2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var imageView: ImageView? = null

    init {
        imageView = itemView.findViewById(R.id.image_view)
    }

    fun setImageResource(imageId: Int) {
        imageView?.setImageResource(imageId)
    }

}