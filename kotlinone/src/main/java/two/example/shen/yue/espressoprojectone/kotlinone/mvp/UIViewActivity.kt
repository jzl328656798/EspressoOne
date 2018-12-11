package two.example.shen.yue.espressoprojectone.kotlinone.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * CreateTime: 2018/12/11 - 下午5:19
 * Author: Queen_J
 * Describe: TODO
 */
abstract class UIViewActivity<P : IPresenter> : AppCompatActivity() {

    protected var mPresenter: P? = null

    protected abstract val presenter: Class<P>?

    override fun onCreate(savedInstanceState: Bundle?) {
        if (presenter != null) {
            try {
                mPresenter = presenter!!.newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
            mPresenter!!.attach(this)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter!!.detach()
            mPresenter = null
        }
    }
}