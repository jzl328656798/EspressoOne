package two.example.shen.yue.espressoprojectone.test18

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Author: Queen
 * Date: 2020/7/27 2:23 PM
 * Describe: Test18interface1
 */
interface Test18interface1 {

    @GET("index?format=2&cityname='+\$citycode+'&key=c82727e986a4f6cfc6ba1984f1f9183a")
    fun getWeather(): Observable<Any>
}