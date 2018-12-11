package two.example.shen.yue.espressoprojectone.kotlinone.mvp

import android.content.Context

/**
 * CreateTime: 2018/12/11 - 下午5:07
 * Author: Queen_J
 * Describe: TODO
 */
interface IPresenter {

    fun attach(context: Context)

    fun detach()
}