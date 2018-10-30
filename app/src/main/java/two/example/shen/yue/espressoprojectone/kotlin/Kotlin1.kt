package two.example.shen.yue.espressoprojectone.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.kotlin1.*
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.view.KotlinToast1

/**
 * Created by queen on 2018/10/15.
 * Author: Queen
 * Date: 2018/10/15
 * Time: 下午1:31
 * Email: zhuolei.jiang@softlinker.com & jiangzhuolei@126.com
 * Describe: TODO
 */
class Kotlin1 :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kotlin1)

        tv_kotlin1.setOnClickListener{changeTextView()}

        btn_kotlin1.setOnClickListener{clickButton()}

        btn_kotlin1_toast1.setOnClickListener{toastTest()}
    }

    private fun changeTextView(){
//        tv_kotlin1.setText(R.string.str_text1)
//        val name = "test"
//        tv_kotlin1.setText(R.string.str_text2)
        tv_kotlin1.text = resources.getString(R.string.xliff_string,"星期一",13)
    }

    private fun clickButton(){
//        tv_kotlin1.setText(R.string.str_btn1)
        val name = "test"
        tv_kotlin1.text = ("R.string.str , $name")
        tv_kotlin1.append("test1")
    }

    private fun toastTest(){
        KotlinToast1.toast1(applicationContext)
//        KotlinToast1.toast1(application)
    }
}