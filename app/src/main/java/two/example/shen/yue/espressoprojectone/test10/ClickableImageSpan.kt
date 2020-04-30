package two.example.shen.yue.espressoprojectone.test10

import android.graphics.drawable.Drawable
import android.text.style.ImageSpan
import android.view.View

/**
 * Author: Queen
 * Date: 2020/4/28 10:41 AM
 * Describe: ClickableImageSpan
 */
abstract class ClickableImageSpan(drawable: Drawable) : ImageSpan(drawable) {

    abstract fun onClick(view: View)

}