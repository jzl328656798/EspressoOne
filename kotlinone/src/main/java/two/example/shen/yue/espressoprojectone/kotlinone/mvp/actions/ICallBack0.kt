package two.example.shen.yue.espressoprojectone.kotlinone.mvp.actions

/**
 * CreateTime: 2018/12/11 - 下午5:09
 * Author: Queen_J
 * Describe: TODO
 */

interface ICallBack0 : Action {

    fun onResult()

    fun onError(throwable: RequestException)
}