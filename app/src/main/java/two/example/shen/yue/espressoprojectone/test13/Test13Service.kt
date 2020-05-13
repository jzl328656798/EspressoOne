package two.example.shen.yue.espressoprojectone.test13

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import two.example.shen.yue.espressoprojectone.test13.bean.Test13Activity1Bean2

/**
 * Author: Queen
 * Date: 2020/5/12 8:29 AM
 * Describe: Test13Service
 */
interface Test13Service {

    @GET("{pageNum}/json")
    fun listProject(@Path("pageNum") pageNum: String): Call<Test13Activity1Bean2>

    @GET("{pageNum}/json")
    suspend fun listProject2(@Path("pageNum") pageNum: String): Test13Activity1Bean2

    @GET("{pageNum}/json")
    fun listProject3(@Path("pageNum") pageNum: String): Single<Test13Activity1Bean2>
}