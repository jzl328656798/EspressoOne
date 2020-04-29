package two.example.shen.yue.espressoprojectone.test1

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatDialog
import android.view.*
import two.example.shen.yue.espressoprojectone.R


class QueenBaseDialog(context: Context?) : AppCompatDialog(context, R.style.Queen_BottomSheet) {


    init {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        initView()
    }

    private fun initView() {
        val container = View.inflate(context, R.layout.queen_dialog, null)
        super.setContentView(container, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setGravity(Gravity.BOTTOM)
        window?.setWindowAnimations(R.style.queen_dialog_style);
        val manager = window?.windowManager
        val defaultDisplay = manager?.defaultDisplay
        val attributes = window?.attributes;
        attributes?.width = defaultDisplay?.width
        window?.attributes = attributes

    }
}