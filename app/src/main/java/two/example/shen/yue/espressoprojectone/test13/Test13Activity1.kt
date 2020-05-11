package two.example.shen.yue.espressoprojectone.test13

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_test13_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.databinding.ActivityTest131Binding
import two.example.shen.yue.espressoprojectone.test13.bean.Test13Activity1Bean

/**
 * Author: Queen
 * Date: 2020/4/20 2:21 PM
 * Describe: TODO
 */
class Test13Activity1 : BaseActivity() {

    private val user = Test13Activity1Bean()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test13_1)

        val binding =
            DataBindingUtil.setContentView<ActivityTest131Binding>(this, R.layout.activity_test13_1)

        binding.user = user


        btn.setOnClickListener {
            user.name = "test"
            user.age = "999"
            binding.user = user
        }
    }

}