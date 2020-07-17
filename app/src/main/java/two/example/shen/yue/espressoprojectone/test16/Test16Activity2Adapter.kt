package two.example.shen.yue.espressoprojectone.test16

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Author: Queen
 * Date: 2020/7/17 1:17 PM
 * Describe: Test16Activity2Adapter
 */
class Test16Activity2Adapter(fm:FragmentManager) :FragmentStatePagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return Test16Activity2Fragment.newInstance(
            backgroundColorArray[(position % titleArray.count())],
            resourceArray[(position % titleArray.count())],
            titleArray[(position % titleArray.count())]
        )
    }

    override fun getCount(): Int {
        return titleArray.count() * 20
    }
}