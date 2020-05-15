package two.example.shen.yue.espressoprojectone.test13

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_test13_3.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.test13.bean.Test13Activity1Bean
import two.example.shen.yue.espressoprojectone.test13.fragment.Test13Fragment1
import two.example.shen.yue.espressoprojectone.test13.fragment.Test13Fragment2
import two.example.shen.yue.espressoprojectone.test13.model.Test13FragmentViewModel

/**
 * Author: Queen
 * Date: 2020/5/13 4:51 PM
 * Describe: Test13Activity3
 */
class Test13Activity3 : BaseActivity() {

    private lateinit var model: Test13FragmentViewModel

    private val fragment1 = Test13Fragment1()
    private val fragment2 = Test13Fragment2()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test13_3)

        switchFragment1(fragment1)
        switchFragment2(fragment2)

        model = ViewModelProvider(this).get(Test13FragmentViewModel::class.java)

        btn1.setOnClickListener {
            val bean = Test13Activity1Bean()
            bean.name = "xxxxxxxxx"
            model.select(bean)
        }

    }


    private fun switchFragment1(targetFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_test13_1, targetFragment, targetFragment::class.java.name)
        transaction.commit()
    }

    private fun switchFragment2(targetFragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_test13_2, targetFragment, targetFragment::class.java.name)
        transaction.commit()
    }
}