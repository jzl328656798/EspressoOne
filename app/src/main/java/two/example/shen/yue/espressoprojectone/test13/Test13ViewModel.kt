package two.example.shen.yue.espressoprojectone.test13

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import two.example.shen.yue.espressoprojectone.test13.bean.Test13Activity1Bean2

/**
 * Author: Queen
 * Date: 2020/5/12 2:23 PM
 * Describe: Test13ViewModel
 */
class Test13ViewModel : ViewModel() {

    private val repos: MutableLiveData<Test13Activity1Bean2> by lazy {
        MutableLiveData<Test13Activity1Bean2>().also { loadData() }
    }

    fun getRepos(): LiveData<Test13Activity1Bean2> {
        return repos
    }


    private fun loadData() {
        val retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/article/listproject/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(Test13Service::class.java)

        api.listProject("2").enqueue(object : Callback<Test13Activity1Bean2> {
            override fun onFailure(call: Call<Test13Activity1Bean2>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Test13Activity1Bean2>,
                response: Response<Test13Activity1Bean2>
            ) {
                repos.value = response.body()
            }

        })


    }

}