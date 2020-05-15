package two.example.shen.yue.espressoprojectone.test14

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.databinding.ActivityTest141Binding

/**
 * Author: Queen
 * Date: 2020/5/14 4:16 PM
 * Describe: Test14Activity1
 */
class Test14Activity1 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        DataBindingUtil.setContentView<ActivityTest141Binding>(this, R.layout.activity_test14_1)

    }
}