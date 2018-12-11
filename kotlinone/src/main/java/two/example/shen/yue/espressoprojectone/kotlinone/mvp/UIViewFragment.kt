package two.example.shen.yue.espressoprojectone.kotlinone.mvp

import android.app.Fragment
import android.content.Context

/**
 * CreateTime: 2018/12/11 - 下午5:25
 * Author: Queen_J
 * Describe: TODO
 */
abstract class UIViewFragment<P : IPresenter> : Fragment() {

    protected var mPresenter: P? = null

    protected abstract val presenter: Class<P>?

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (presenter != null) {
            try {
                mPresenter = presenter!!.newInstance()
            } catch (e: java.lang.InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

            mPresenter!!.attach(context)
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (mPresenter != null) {
            mPresenter!!.detach()
            mPresenter = null
        }
    }

}