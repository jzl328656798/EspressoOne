package two.example.shen.yue.espressoprojectone.test12

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test12_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.adapter.Test12Activity1Adapter

/**
 * Author: Queen
 * Date: 2020/4/20 2:21 PM
 * Describe: TODO
 */
class Test12Activity1 : BaseActivity() {

    private var adapter: Test12Activity1Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test12_1)

        initView()

        btn.setOnClickListener { list_view.setSelection(26) }
    }

    private fun initView() {
        setAdapter(getData())
    }

    private fun setAdapter(list: MutableList<String>) {
        if (adapter == null) {
            adapter = Test12Activity1Adapter(this, list, R.layout.item_test12_activity1_adapter)
            list_view.adapter = adapter
        } else {
            adapter?.setData(list)
        }
    }


    private fun getData(): MutableList<String> {
        val list = mutableListOf<String>()
        for (i in 0..50) {
            list.add(i.toString())
        }
        return list
    }

}