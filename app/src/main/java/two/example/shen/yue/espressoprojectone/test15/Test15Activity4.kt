package two.example.shen.yue.espressoprojectone.test15

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebSettings
import kotlinx.android.synthetic.main.activity_test15_4.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/5/29 1:28 PM
 * Describe: Test15Activity
 */
class Test15Activity4 : BaseActivity() {


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test15_4)

        val webSettings: WebSettings = web_view.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowFileAccess = true
        webSettings.allowFileAccessFromFileURLs = true
        webSettings.allowUniversalAccessFromFileURLs = true


        val url = Test15Utils.encodeURIComponent("http://cms.haizr.com/upload/file/2020/03/21/01067349342.pdf")

        web_view.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file=$url")
    }


}