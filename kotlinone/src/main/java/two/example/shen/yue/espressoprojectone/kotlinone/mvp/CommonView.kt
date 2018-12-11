package two.example.shen.yue.espressoprojectone.kotlinone.mvp

/**
 * CreateTime: 2018/12/11 - 下午5:02
 * Author: 姜卓磊
 * Remark: TODO
 */
interface CommonView : IView {
    //    Success Failure
    //    RequestException
    fun startLoad(message: String) //界面等待开始

    fun stopLoad() //界面等待结束
}