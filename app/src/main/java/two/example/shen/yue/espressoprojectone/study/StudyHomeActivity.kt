package two.example.shen.yue.espressoprojectone.study

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_stydy_home.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.study.activity.WaterfallFLowActivity

class StudyHomeActivity :BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stydy_home)
        btn1.setOnClickListener {
            startActivity(Intent(this, WaterfallFLowActivity::class.java))
        }
    }

}