package two.example.shen.yue.espressoprojectone.test1

import android.os.Bundle
import android.support.v4.app.Fragment
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/3/23 3:48 PM
 * Describe: TestFragmentB
 */
class TestFragmentB : Fragment() {

    companion object {
        fun newInstance(): TestFragmentB {
            return TestFragmentB()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_test_b, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val imageView = view?.findViewById<ImageView>(R.id.imageView)
//
//        Picasso.with(context)
//                .load("https://s3-us-west-1.amazonaws.com/powr/defaults/image-slider2.jpg")
//                .fit().centerCrop()
//                .noFade()
//                .into(imageView, object : Callback {
//                    override fun onSuccess() {
//                        startPostponedEnterTransition()
//                    }
//
//                    override fun onError() {}
//                })
    }
}