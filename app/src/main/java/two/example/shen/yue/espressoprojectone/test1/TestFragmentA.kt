package two.example.shen.yue.espressoprojectone.test1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/23 3:48 PM
 * Describe: TestFragmentA
 */
class TestFragmentA : Fragment() {

    private val fragmentTag = TestFragmentA::class.java.simpleName

    companion object {
        fun newInstance(): TestFragmentA {
            return TestFragmentA()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_a, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val imageView = view?.findViewById<ImageView>(R.id.imageView)
        val textView = view?.findViewById<TextView>(R.id.textView)
        imageView?.setImageResource(R.drawable.image)
//        Picasso.with(activity)
//                .load("https://s3-us-west-1.amazonaws.com/powr/defaults/image-slider2.jpg")
//                .fit()
//                .centerCrop()
//                .into(imageView)
        activity?.findViewById<Button>(R.id.btn_click)?.setOnClickListener {
            var fragmentB = fragmentManager?.findFragmentByTag(fragmentTag)
            if (fragmentB == null) {
                fragmentB = TestFragmentB.newInstance()
            }
            fragmentManager
                    ?.beginTransaction()
                    ?.addSharedElement(imageView, "simple transition name")
                    ?.addSharedElement(textView, "simple text view")
                    ?.addToBackStack(fragmentTag)
                    ?.replace(R.id.frame_layout_test, fragmentB)
                    ?.commit()
        }
    }
}