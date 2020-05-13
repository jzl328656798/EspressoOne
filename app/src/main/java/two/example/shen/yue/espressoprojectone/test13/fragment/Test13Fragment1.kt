package two.example.shen.yue.espressoprojectone.test13.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_test13_1.*
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.test13.bean.Test13Activity1Bean
import two.example.shen.yue.espressoprojectone.test13.model.Test13FragmentViewModel

/**
 * Author: Queen
 * Date: 2020/5/13 4:52 PM
 * Describe: Test13Fragment1
 */
class Test13Fragment1 : Fragment() {

    private lateinit var model: Test13FragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test13_1, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(Test13FragmentViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_test13_fragment_1.setOnClickListener {
            Log.i("queen","Test13Fragment1")
            val bean = Test13Activity1Bean()
            bean.name = "123456789"
            model.select(bean)
        }
    }
}