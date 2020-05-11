package two.example.shen.yue.espressoprojectone.test1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/23 3:40 PM
 * Describe: Test1Activity
 */
class Test1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1)
        var fragment = supportFragmentManager.findFragmentByTag(TestFragmentA::class.java.name)
        if (fragment == null) {
            fragment = TestFragmentA.newInstance()
        }
        supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_layout_test, fragment, TestFragmentA::class.java.name)
                .commit()
    }

}