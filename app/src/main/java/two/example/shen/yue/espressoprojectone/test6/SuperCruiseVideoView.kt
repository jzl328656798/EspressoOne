package two.example.shen.yue.espressoprojectone.test6

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.RelativeLayout
import android.widget.SeekBar
import kotlinx.android.synthetic.main.super_cruise_video_view_layout.view.*
import two.example.shen.yue.espressoprojectone.R


/**
 * Author: Queen
 * Date: 2020/4/16 5:08 PM
 * Describe: SuperCruiseVideoView
 */
class SuperCruiseVideoView : RelativeLayout {

    private val sDefaultTimeout = 3000
    private var mShowing = false
    private var mShowPlay = false
    private var mDragging = false

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.super_cruise_video_view_layout, this, true)
//        tv_title       头部标题
//        video_view     播放窗口
//        iv_bg          背景
//        iv_play        播放按钮
//        ll_schedule    导航Layout
//        tv_at_time     当前播放时间
//        seek_bar       播放进度条
//        tv_total_time  视频总时间
//        iv_full_screen 全屏按钮
        seek_bar.max = 1000
        initListener()
    }

    fun setVideoUrl(url: String) {
        //video_view.setVideoURI(Uri.parse(url))
        video_view.setVideoURI(Uri.parse("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8"))
    }

    private fun initListener() {
        video_view.setOnPreparedListener {
            Log.i("queen", "video 准备好了")
        }

        video_view.setOnCompletionListener {
            Log.i("queen", "video 播放完了")
            playFinish()
        }

        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (!fromUser) {
                    return
                }
                val duration = video_view.duration.toLong()
                val newPosition = duration * progress / 1000L
                video_view.seekTo(newPosition.toInt())
                tv_at_time.text = stringForTime(newPosition.toInt())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mDragging = true
                removeCallbacks(mShowProgress)
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mDragging = false
                setProgress()
                updatePausePlay()
                show()
                post(mShowProgress)
            }

        })

        iv_play.setOnClickListener {
            if (video_view.isPlaying) {
                video_view.pause()
            } else {
                video_view.start()
            }
            updatePausePlay()
            if (!mShowPlay) {
                iv_play.visibility = View.GONE
                mShowPlay = true
            }
        }
    }

    private fun show() {
        if (!mShowing) {
            setProgress()
            ll_schedule.visibility = View.VISIBLE
            iv_play.visibility = View.VISIBLE
            mShowing = true
        }
        updatePausePlay()
        post(mShowProgress)
        removeCallbacks(mFadeOut)
        postDelayed(mFadeOut, sDefaultTimeout.toLong())
    }

    private fun hide() {
        if (mShowing) {
            try {
                removeCallbacks(mShowProgress)
                ll_schedule.visibility = View.GONE
                if (mShowPlay) {
                    iv_play.visibility = View.GONE
                } else {
                    iv_play.visibility = View.VISIBLE
                }
            } catch (ex: IllegalArgumentException) {
                Log.w("MediaController", "already removed")
            }
            mShowing = false
        }
    }

    private fun updatePausePlay() {
        if (video_view.isPlaying) {
            iv_play.setImageResource(R.mipmap.icon_sc_video_pause)
        } else {
            iv_play.setImageResource(R.mipmap.icon_sc_video_play)
        }
    }

    private val mFadeOut = Runnable { hide() }

    private val mShowProgress = object : Runnable {
        override fun run() {
            val pos: Int = setProgress()
            if (!mDragging && mShowing && video_view.isPlaying) {
                postDelayed(this, 1000 - (pos % 1000).toLong())
            }
        }
    }

    private fun setProgress(): Int {

        val duration = video_view.duration
        val position = video_view.currentPosition
        if (duration > 0) {
            val pos = 1000L * position / duration
            seek_bar.progress = pos.toInt()
        }
        val percent: Int = video_view.bufferPercentage
        seek_bar.secondaryProgress = percent * 10

        tv_total_time.text = stringForTime(duration)
        tv_at_time.text = stringForTime(position)
        return position
    }

    private fun playFinish() {
        iv_bg.visibility = View.VISIBLE
        ll_schedule.visibility = View.GONE
        updatePausePlay()
        mShowPlay = false
    }


    private fun stringForTime(m: Int): String {
        val second = m / 1000
        val hour = second / 3600
        val minute = second % 3600 / 60
        val ss = second % 60
        return if (hour != 0) {
            String.format("%02d:%02d:%02d", hour, minute, ss)
        } else {
            String.format("%02d:%02d", minute, ss)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                if (mShowing) {
                    hide()
                } else {
                    show()
                }
            }
        }
        return true

    }
}