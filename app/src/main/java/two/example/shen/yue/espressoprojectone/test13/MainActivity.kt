package two.example.shen.yue.espressoprojectone.test13

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import two.example.shen.yue.espressoprojectone.test13.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
