package two.example.shen.yue.espressoprojectone.sc

/**
 * Author: Queen
 * Date: 2020/4/20 3:54 PM
 * Describe: TODO
 */
object ScVideoPlayerManager {

    var FIRST_FLOOR_JCVD: ScVideoPlayer? = null
    var SECOND_FLOOR_JCVD: ScVideoPlayer? = null

    fun setFirstFloor(jcVideoPlayer: ScVideoPlayer?) {
        FIRST_FLOOR_JCVD = jcVideoPlayer
    }

    fun setSecondFloor(jcVideoPlayer: ScVideoPlayer?) {
        SECOND_FLOOR_JCVD = jcVideoPlayer
    }

    fun getFirstFloor(): ScVideoPlayer? {
        return FIRST_FLOOR_JCVD
    }

    fun getSecondFloor(): ScVideoPlayer? {
        return SECOND_FLOOR_JCVD
    }

    fun getCurrentJcvd(): ScVideoPlayer? {
        return if (getSecondFloor() != null) {
            getSecondFloor()
        } else getFirstFloor()
    }

    fun completeAll() {
        if (SECOND_FLOOR_JCVD != null) {
            SECOND_FLOOR_JCVD!!.onCompletion()
            SECOND_FLOOR_JCVD = null
        }
        if (FIRST_FLOOR_JCVD != null) {
            FIRST_FLOOR_JCVD!!.onCompletion()
            FIRST_FLOOR_JCVD = null
        }
    }
}