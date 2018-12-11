package two.example.shen.yue.espressoprojectone.kotlinone.mvp.actions

/**
 * CreateTime: 2018/12/11 - 下午5:11
 * Author: Queen_J
 * Describe: TODO
 */
interface ICallBack2<in T1, in T2> : Action {

    fun onResult1(t1: T1)

    fun onResult2(t2: T2)

    fun onError1(throwable: RequestException)

    fun onError2(throwable: RequestException)
}