package two.example.shen.yue.espressoprojectone.test6

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_test6_2.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/4/2 9:26 AM
 * Describe: Test6Activity1
 */
class Test6Activity2 : BaseActivity() {

    private val mc by lazy { MediaController(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test6_2)
        video_view.setMediaController(mc)
        video_view.setVideoURI(Uri.parse("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8"))
        video_view.start()


    }


}