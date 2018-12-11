package two.example.shen.yue.espressoprojectone.kotlinone.mvp.actions

/**
 * CreateTime: 2018/12/11 - 下午5:11
 * Author: Queen_J
 * Describe: TODO
 */
interface ICallBack6<in T1, in T2, in T3, in T4, in T5, in T6> : Action {

    fun onResult1(t1: T1)

    fun onResult2(t2: T2)

    fun onResult3(t3: T3)

    fun onResult4(t4: T4)

    fun onResult5(t5: T5)

    fun onResult6(t6: T6)

    fun onError1(throwable: RequestException)

    fun onError2(throwable: RequestException)

    fun onError3(throwable: RequestException)

    fun onError4(throwable: RequestException)

    fun onError5(throwable: RequestException)

    fun onError6(throwable: RequestException)
}