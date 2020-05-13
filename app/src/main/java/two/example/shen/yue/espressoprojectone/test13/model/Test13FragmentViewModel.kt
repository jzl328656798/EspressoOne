package two.example.shen.yue.espressoprojectone.test13.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import two.example.shen.yue.espressoprojectone.test13.bean.Test13Activity1Bean

/**
 * Author: Queen
 * Date: 2020/5/13 4:57 PM
 * Describe: Test13ViewModel
 */
class Test13FragmentViewModel : ViewModel() {

    val selected = MutableLiveData<Test13Activity1Bean>()


    fun select(bean: Test13Activity1Bean) {
        Log.i("queen","Test13FragmentViewModel")
        selected.value = bean
    }
}