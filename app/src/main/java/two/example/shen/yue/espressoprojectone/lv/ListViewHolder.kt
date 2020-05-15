package two.example.shen.yue.espressoprojectone.lv

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

/**
 * Author: Queen
 * Date: 2020/5/9 2:35 PM
 * Describe: ListViewHolder
 */
@Suppress("UNCHECKED_CAST")
class ListViewHolder(context: Context, layoutId: Int, parent: ViewGroup) {

    private val sparseArray by lazy { SparseArray<View>() }
    private val convertView: View = LayoutInflater.from(context).inflate(layoutId, parent, false)

    init {
        convertView.tag = this
    }

    fun <T : View> getView(viewId: Int): T {
        var view = sparseArray.get(viewId)
        if (view == null) {
            view = convertView.findViewById(viewId)
            sparseArray.put(viewId, view)
        }
        return view as T
    }

    fun getConvertView(isNotNull: Boolean = true, parent: ViewGroup): View {
        if (!isNotNull) {
            var height = parent.height
            val width = parent.width
            if (parent is ListView) {
                val headerCount = parent.headerViewsCount
                var headerHeight = 0
                for (i in 0 until headerCount) {
                    val header = parent.getChildAt(i)
                    headerHeight += header.height
                }
                height = height - parent.top - headerHeight
            }
            convertView.layoutParams = ViewGroup.LayoutParams(width, height)
        }
        return convertView
    }
}