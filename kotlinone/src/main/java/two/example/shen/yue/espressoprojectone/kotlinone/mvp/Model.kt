package two.example.shen.yue.espressoprojectone.kotlinone.mvp

import two.example.shen.yue.espressoprojectone.kotlinone.mvp.actions.Action



/**
 * CreateTime: 2018/12/11 - 下午5:15
 * Author: Queen_J
 * Describe: TODO
 */
abstract class Model<T : Action> {

    protected var mCallBack: T? = null


    fun setCallBack(callBack: T) {
        mCallBack = callBack
    }


    abstract fun cancel()
}