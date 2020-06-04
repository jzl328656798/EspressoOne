package two.example.shen.yue.espressoprojectone.test15

import android.net.Uri
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import kotlinx.android.synthetic.main.activity_test15_5.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/5/29 1:28 PM
 * Describe: Test15Activity
 */
class Test15Activity5 : BaseActivity(), OnPageChangeListener, OnLoadCompleteListener, OnPageErrorListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test15_5)


        ActivityCompat.requestPermissions(
            this,
            arrayOf("android.permission.READ_EXTERNAL_STORAGE"),
            10
        );


        pdf.fromAsset("test001.pdf")
            .defaultPage(0)
            .onPageChange(this)
            .enableAnnotationRendering(true)
            .onLoad(this)
            .scrollHandle(DefaultScrollHandle(this))
            .spacing(10) // in dp
            .onPageError(this)
            .load()
    }

    override fun onPageChanged(page: Int, pageCount: Int) {
        log("onPageChanged")
    }

    override fun loadComplete(nbPages: Int) {
        log("loadComplete")
    }

    override fun onPageError(page: Int, t: Throwable?) {
        log("onPageError")
    }


}