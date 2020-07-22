package two.example.shen.yue.espressoprojectone.test15

import android.os.Bundle
import android.os.Environment
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import java.lang.reflect.Proxy


/**
 * Author: Queen
 * Date: 2020/5/29 1:28 PM
 * Describe: Test15Activity
 */
class Test15Activity1 : BaseActivity() {


    private val savePath = Environment.getExternalStorageDirectory().absolutePath + "/DCIM"

    private val imagePath = "/storage/emulated/0/DCIM/Camera/IMG_20200110_152110.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test15_1)


        val proxy = Proxy.newProxyInstance(this.classLoader, arrayOf(Test15Activity1::class.java)) { proxy, method, args ->
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }


}