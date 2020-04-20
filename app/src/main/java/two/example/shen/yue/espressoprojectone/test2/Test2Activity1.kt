package two.example.shen.yue.espressoprojectone.test2

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import two.example.shen.yue.espressoprojectone.R

/**
 * Author: Queen
 * Date: 2020/3/23 4:39 PM
 * Describe: Test2Activity1
 */
class Test2Activity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2_1)

        val tv1 = findViewById<TextView>(R.id.tv1)
        val tv2 = findViewById<TextView>(R.id.tv2)
        val tv3 = findViewById<TextView>(R.id.tv3)

        tv1.setOnClickListener {
            val intent = Intent(this, Test2Activity2::class.java)
            val pairTv1 = android.support.v4.util.Pair<View, String>(tv1, "simple text view1")
            val pairTv2 = android.support.v4.util.Pair<View, String>(tv2, "simple text view2")
            val pairTv3 = android.support.v4.util.Pair<View, String>(tv3, "simple text view3")
            val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairTv1, pairTv2, pairTv3)
            startActivity(intent, optionsCompat.toBundle())
        }
    }
}