package two.example.shen.yue.espressoprojectone.test13.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_test13_2.*
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.test13.model.Test13FragmentViewModel

/**
 * Author: Queen
 * Date: 2020/5/13 4:52 PM
 * Describe: Test13Fragment1
 */
class Test13Fragment2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_test13_2, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = ViewModelProvider(this).get(Test13FragmentViewModel::class.java)
        model.selected.observe(this, Observer{ bean ->
            Log.i("queen","Test13Fragment2")
            tv_test13_fragment_2.text = bean.name
        })
    }

}