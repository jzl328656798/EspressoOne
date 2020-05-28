package two.example.shen.yue.espressoprojectone.banner

import android.content.Context
import android.content.res.Resources
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs


/**
 * Author: Queen
 * Date: 2020/5/19 1:04 PM
 * Describe: 轮播图View
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class QueenBannerRecyclerView : RecyclerView, Handler.Callback {

    companion object {
        private const val MSG_PLAY_NEXT = 1989
        private const val TASK_TIMEOUT = 5000L
    }

    private val mRecyclerHandler by lazy { Handler(Looper.getMainLooper(), this) }

    private var listener: QueenBannerPageChangeListener? = null

    private var isPlaying = false

    private var lastIsPlayState = false

    private var realPosition = -1

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun getItemCount() = adapter?.itemCount ?: 0

    /**
     * 设置页面变化监听器
     */
    fun setPageChangeListener(queenBannerPageChangeListener: QueenBannerPageChangeListener) {
        this.listener = queenBannerPageChangeListener
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == SCROLL_STATE_IDLE) {
                    listener?.onPageSelection(getCurrentItem())
                }
            }
        })
        //this.listener?.onPageSelection(getCurrentItem())
    }

    private fun getCurrentItem() = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

    private fun setCurrentItem(position: Int, isAnimate: Boolean) {
        if (getItemCount() <= position) return
        if (isAnimate) {
            smoothScrollToPosition(position)
        } else {
            scrollToPosition(position)
        }
    }

    fun setCurrentItem(position: Int) {
        setCurrentItem(position, true)
    }

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        val linearLayoutManager = layoutManager as LinearLayoutManager

        val screenWidth: Int = Resources.getSystem().displayMetrics.widthPixels

        val lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
        val lastView: View? = linearLayoutManager.findViewByPosition(lastVisibleItemPosition)

        val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
        val firstView: View? = linearLayoutManager.findViewByPosition(firstVisibleItemPosition)

        val leftMargin = lastView?.let { (screenWidth - it.width) / 2 } ?: 0
        val rightMargin = lastView?.let { (screenWidth - it.width) / 2 + it.width } ?: 0
        val leftEdge = lastView?.left ?: 0
        val rightEdge = firstView?.right ?: 0
        val scrollDistanceLeft = leftEdge - leftMargin
        val scrollDistanceRight = rightMargin - rightEdge

        val targetPosition = if (abs(velocityX) < 1500) {
            when {
                leftEdge > screenWidth / 2 -> {
                    smoothScrollBy(-scrollDistanceRight, 0)
                    firstVisibleItemPosition
                }
                rightEdge < screenWidth / 2 -> {
                    smoothScrollBy(scrollDistanceLeft, 0)
                    firstVisibleItemPosition + 1
                }
                else -> {
                    if (velocityX > 0) {
                        smoothScrollBy(-scrollDistanceRight, 0)
                    } else {
                        smoothScrollBy(scrollDistanceLeft, 0)
                    }
                    firstVisibleItemPosition
                }
            }
        } else {
            if (velocityX > 0) {
                smoothScrollBy(scrollDistanceLeft, 0)
                firstVisibleItemPosition + 1
            } else {
                smoothScrollBy(-scrollDistanceRight, 0)
                firstVisibleItemPosition
            }
        }

        listener?.let {
            realPosition = targetPosition
            it.onPageSelection(targetPosition)
        }

        return true
    }

    override fun handleMessage(msg: Message): Boolean {
        when (msg.what) {
            MSG_PLAY_NEXT -> {
                showNextPage()
            }
        }
        return false
    }

    /**
     * 显示下一个页面
     */
    private fun showNextPage() {
        if (!isPlaying) return
        if (!canRecyclePlaying()) {
            isPlaying = false
            return
        }
        if (getItemCount() > 0) {
            if (getCurrentItem() == NO_POSITION) {
                setCurrentItem(0)
            } else {
                setCurrentItem(getCurrentItem() + 1)
            }
        }
        mRecyclerHandler.sendEmptyMessageDelayed(MSG_PLAY_NEXT, TASK_TIMEOUT)
    }

    private fun canRecyclePlaying() = getItemCount() >= 1

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        if (canRecyclePlaying()) {
            if (realPosition == -1) {
                realPosition = 0
            }
            setCurrentItem(realPosition, false)
        }
    }

    fun next() {
        this.smoothScrollToPosition(getCurrentItem() + 1)
    }

    fun startPlay() {
        if (isPlaying) {
            stopPlay()
        }
        if (!canRecyclePlaying()) {
            isPlaying = false
            return
        }
        isPlaying = true
        mRecyclerHandler.sendEmptyMessageDelayed(MSG_PLAY_NEXT, TASK_TIMEOUT)
    }

    fun stopPlay() {
        isPlaying = false
        mRecyclerHandler.removeMessages(MSG_PLAY_NEXT)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (lastIsPlayState) {
            startPlay()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        lastIsPlayState = isPlaying
        stopPlay()
    }


}