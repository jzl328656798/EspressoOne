package two.example.shen.yue.espressoprojectone.test16

import android.os.Bundle
import androidx.databinding.library.BuildConfig
import kotlinx.android.synthetic.main.activity_test16_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/7/14 9:07 AM
 * Describe: biff
 */
class Test16Activity1 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test16_1)
        text_view.text = BuildConfig.VERSION_NAME
        btn_merge.setOnClickListener { merge() }
    }

    private fun merge() {
        text_view.text = stringFromJNI()
    }

    private external fun stringFromJNI(): String

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }

}