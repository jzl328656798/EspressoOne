package two.example.shen.yue.espressoprojectone.adapter

import android.content.Context
import android.widget.TextView
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.lv.ListViewAdapter
import two.example.shen.yue.espressoprojectone.lv.ListViewHolder

/**
 * Author: Queen
 * Date: 2020/5/9 4:08 PM
 * Describe: Test12Activity1Adapter
 */
class Test12Activity1Adapter(context: Context, list: MutableList<String>, layoutId: Int) : ListViewAdapter<String>(context, list, layoutId) {

    override fun setConvertView(viewHolder: ListViewHolder, item: String?, position: Int) {
        viewHolder.getView<TextView>(R.id.tv_text).text = "item: $item"
    }
}