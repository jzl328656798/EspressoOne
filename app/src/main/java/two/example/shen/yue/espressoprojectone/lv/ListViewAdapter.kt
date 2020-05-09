package two.example.shen.yue.espressoprojectone.lv

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Author: Queen
 * Date: 2020/5/9 3:15 PM
 * Describe: ListViewAdapter
 */
abstract class ListViewAdapter<T>(private val context: Context, private val data: MutableList<T>, private val layoutId: Int) : BaseAdapter() {

    var isNotNull = false
    var isAllItemEnable = true
    var showNoDataPage = true
    var nullLayoutId: Int = -1
    var selectPosition = -1

    fun setData(data: MutableList<T>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: MutableList<T>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addFirstItem(item: T) {
        this.data.add(0, item)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    fun getString(resId: Int): String = context.getString(resId)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ListViewHolder
        if (data.isNotEmpty()) {
            viewHolder = ListViewHolder(context, layoutId, parent)
            isNotNull = true
            isAllItemEnable = true
            setConvertView(viewHolder, data[position], position)
        } else {
            viewHolder = ListViewHolder(context, nullLayoutId, parent)
            isNotNull = false
            isAllItemEnable = false
            setConvertView(viewHolder, null, position)
        }
        return if (showNoDataPage) {
            viewHolder.getConvertView(isNotNull, parent)
        } else {
            viewHolder.getConvertView(parent = parent)
        }
    }

    abstract fun setConvertView(viewHolder: ListViewHolder, item: T?, position: Int)

    override fun getItem(position: Int) = data[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = if (data.isNotEmpty()) data.size else 1

    override fun isEnabled(position: Int) = isAllItemEnable
}