package two.example.shen.yue.espressoprojectone.test2

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/25 2:18 PM
 * Describe: Test2FragmentAdapterB
 */
class Test2FragmentAdapterB : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return convertView ?: View.inflate(parent?.context, R.layout.item_test2_fragment_b, null)
    }

    override fun getItem(position: Int): Any {
        return "null"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return 10
    }
}