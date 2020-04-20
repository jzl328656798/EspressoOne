package two.example.shen.yue.espressoprojectone.test9

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/4/20 2:35 PM
 * Describe: Test9Adapter1
 */
class Test9Adapter1(var context: Context, var list: ArrayList<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: Test9Adapter1ViewHolder
        val view: View
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_test9_video_view, null)
            viewHolder = Test9Adapter1ViewHolder()
            viewHolder.jcVideoPlayer = view?.findViewById(R.id.video_player)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as Test9Adapter1ViewHolder
        }

        viewHolder.jcVideoPlayer?.setUp(list[position], 1)

        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}