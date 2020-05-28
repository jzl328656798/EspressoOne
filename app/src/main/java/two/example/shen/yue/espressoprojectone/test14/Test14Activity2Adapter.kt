package two.example.shen.yue.espressoprojectone.test14

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/5/19 10:51 AM
 * Describe: Test14Activity2Adapter
 */
class Test14Activity2Adapter(val list: MutableList<Int>) :
    RecyclerView.Adapter<Test14Activity2ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Test14Activity2ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_test14_activity2_adapter, parent, false)
        return Test14Activity2ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun onBindViewHolder(holder: Test14Activity2ViewHolder, position: Int) {
        holder.setImageResource(list[position % list.size])
    }
}