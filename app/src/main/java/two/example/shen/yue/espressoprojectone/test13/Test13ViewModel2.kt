package two.example.shen.yue.espressoprojectone.test13

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import two.example.shen.yue.espressoprojectone.test13.bean.Test13Activity1Bean2

/**
 * Author: Queen
 * Date: 2020/5/12 2:23 PM
 * Describe: Test13ViewModel
 */
class Test13ViewModel2 : ViewModel() {

    val repos = liveData<Test13Activity1Bean2> {
        emit(loadData())
    }


    private suspend fun loadData(): Test13Activity1Bean2 {
        val retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/article/listproject/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(Test13Service::class.java)

        return api.listProject2("2")
    }
}