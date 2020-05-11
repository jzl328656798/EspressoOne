package two.example.shen.yue.espressoprojectone.test1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test1_1.*
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/23 3:40 PM
 * Describe: Test1Activity
 */
class Test1Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test1_1)

        btn1.setOnClickListener {

            QueenBaseDialog(this).show()

        }

    }

}