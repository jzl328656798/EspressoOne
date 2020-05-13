package two.example.shen.yue.espressoprojectone.test13

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test13_2.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.test13.bean.Test13Activity1Bean2

/**
 * Author: Queen
 * Date: 2020/4/20 2:21 PM
 * Describe: Test13Activity2
 */
class Test13Activity2 : BaseActivity() {

    val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test13_2)


        btn1.setOnClickListener {

            btn7()

        }

        text_view.text = "desc"
    }

    private fun btn7() {

        val model = ViewModelProvider(this).get(Test13ViewModel::class.java)
        model.getRepos().observe(this, Observer<Test13Activity1Bean2> { bean ->
            val desc = bean.data.datas[0].desc
            text_view.text = desc
        })

    }

    private fun btn6() {
        val retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/article/listproject/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(Test13Service::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val one = async { api.listProject2("1") }
            val two = async { api.listProject2("2") }
            val same =
                one.await().data.datas[0].desc + "\n --------------- \n" + two.await().data.datas[0].desc

            text_view.text = same
        }

    }

    private fun btn5() {
        val retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/article/listproject/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()


        val api = retrofit.create(Test13Service::class.java)

        Single.zip<Test13Activity1Bean2, Test13Activity1Bean2, String>(api.listProject3("1"),
            api.listProject3("2"),
            BiFunction { t1, t2 -> t1.data.datas[0].desc + t2.data.datas[0].desc })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(object : SingleObserver<String> {
                override fun onSuccess(t: String) {
                    text_view.text = t
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
    }

    private fun btn4() {
        val retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/article/listproject/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()


        val api = retrofit.create(Test13Service::class.java)

        api.listProject3("2")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Test13Activity1Bean2> {
                override fun onSuccess(t: Test13Activity1Bean2) {
                    val desc = t.data.datas[0].desc
                    text_view.text = desc
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })

    }

    private fun btn3() {
        val retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/article/listproject/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(Test13Service::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val desc = api.listProject2("2").data.datas[0].desc
            log("desc${desc}")
            text_view.text = desc
        }

    }

    private fun btn2() {
        val retrofit = Retrofit.Builder().baseUrl("https://wanandroid.com/article/listproject/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(Test13Service::class.java)

        api.listProject("2").enqueue(object : Callback<Test13Activity1Bean2> {
            override fun onFailure(call: Call<Test13Activity1Bean2>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<Test13Activity1Bean2>,
                response: Response<Test13Activity1Bean2>
            ) {
                val desc = response.body()?.data?.datas?.get(0)?.desc
                log("desc${desc}")
            }

        })
    }

    private fun btn1() {
        GlobalScope.launch(Dispatchers.Main) {
            log("btn1:${Thread.currentThread().name}")
        }
    }
}