package two.example.shen.yue.espressoprojectone.kotlinone.mvp.actions

/**
 * CreateTime: 2018/12/11 - 下午5:10
 * Author: Queen_J
 * Describe: TODO
 */
class RequestException : Throwable {
    /**
     * The Msg.
     */
    var msg: String = ""
    /**
     * The Code.
     */
    var code: Int = -1
    /**
     * The E.
     */
    lateinit var e: Exception

    constructor(detailMessage: String, code: Int) : super(detailMessage) {
        this.msg = detailMessage
        this.code = code
    }

    /**
     * Instantiates a new Request exception.
     *
     * @param detailMessage the detail message
     * @param code          the code
     * @param e             the e
     */
    constructor(detailMessage: String, code: Int, e: Exception) : super(detailMessage) {
        this.msg = detailMessage
        this.code = code
        this.e = e
    }

    /**
     * Instantiates a new Request exception.
     *
     * @param detailMessage the detail message
     * @param cause         the cause
     * @param code          the code
     * @param e             the e
     */
    constructor(detailMessage: String, cause: Throwable, code: Int, e: Exception) : super(detailMessage, cause) {
        this.msg = detailMessage
        this.code = code
        this.e = e
    }

}