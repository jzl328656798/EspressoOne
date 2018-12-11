package two.example.shen.yue.espressoprojectone.kotlinone.mvp.actions

/**
 * CreateTime: 2018/12/11 - 下午5:13
 * Author: Queen_J
 * Describe: TODO
 */
interface ICallBack1<in T1> : Action {

    fun onResult(t1: T1)
}