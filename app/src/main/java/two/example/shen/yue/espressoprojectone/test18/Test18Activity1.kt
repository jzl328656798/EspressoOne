package two.example.shen.yue.espressoprojectone.test18

import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_test18_1.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/7/14 9:07 AM
 * Describe: biff
 */
class Test18Activity1 : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test18_1)

        btn_sop1.setOnClickListener { sop1() }
        btn_sop2.setOnClickListener { sop2() }
        btn_sop3.setOnClickListener { sop3() }

    }

    private fun sop3() {


        val observable1 = Observable.just("第一个").map { "$it:123456789" }

        val observable2 = Observable.just("第二个")

        Observable.zip(observable1, observable2, BiFunction<String, String, String> { t1, t2 -> "$t1---$t2" })
            .subscribe(
                {
                    log(it)
                },
                {
                    log(it.toString())
                }
            ).dispose()
    }

    private fun sop2() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://v.juhe.cn/weather/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        retrofit.create(Test18interface1::class.java)
            .getWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Any> {
                override fun onComplete() {
                    log("onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    log("onSubscribe")
                }

                override fun onNext(t: Any) {
                    log("onNext:$t")
                }

                override fun onError(e: Throwable) {
                    log("onError:$e")
                }

            })

    }


    private fun sop1() {
        val observable = Observable.create(ObservableOnSubscribe<String> {
            log("A-")
            it.onNext("A")
            log("B-")
            it.onNext("B")
            log("C-")
            it.onNext("C")
            log("onComplete-")
            it.onComplete()
        })

        val observer = object : Observer<String> {
            override fun onComplete() {
                log("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                log("onSubscribe")
            }

            override fun onNext(t: String) {
                log("onNext")
            }

            override fun onError(e: Throwable) {
                log("onError")
            }

        }

        observable.subscribe(observer)

    }

}


