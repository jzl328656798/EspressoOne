package two.example.shen.yue.espressoprojectone.kotlinone.mvp

import android.content.Context

/**
 * CreateTime: 2018/12/11 - 下午5:18
 * Author: Queen_J
 * Describe: TODO
 */
abstract class PresenterImpl<V : IView, M : Model<*>> : IPresenter {

    protected var iView: V? = null
    protected var mModel: M? = null
    protected var mContext: Context? = null

    /**
     * @return Class
     */
    protected abstract val model: Class<M>?

    override fun attach(context: Context) {
        mContext = context
        if (model != null) {
            try {
                mModel = model!!.newInstance()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }

        }
    }

    fun addIView(view: V) {
        iView = view
    }

    override fun detach() {
        if (mModel != null) {
            mModel!!.cancel()
        }
        mModel = null
        mContext = null
        iView = null
    }
}
