package two.example.shen.yue.espressoprojectone.sc

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.media.MediaPlayer
import android.os.Handler
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.view.View.OnTouchListener
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import kotlinx.android.synthetic.main.sc_video_player.view.*
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.test9.view.*
import java.lang.reflect.Constructor
import java.util.*

/**
 * Author: Queen
 * Date: 2020/4/20 3:45 PM
 * Describe: TODO
 */
open class ScVideoPlayer : FrameLayout, View.OnClickListener, OnSeekBarChangeListener, OnTouchListener {
    var BACKUP_PLAYING_BUFFERING_STATE = -1
    var currentState = -1
    var currentScreen = -1
    var loop = false
    var url = ""
    var objects: Array<Any>? = null
    var seekToInAdvance = 0

    private var mScreenWidth = 0
    private var mScreenHeight = 0
    private var mAudioManager: AudioManager? = null
    private var mHandler: Handler? = null
    private var mProgressTimerTask: ProgressTimerTask? = null
    private var mTouchingProgressBar = false
    private var mDownX = 0f
    private var mDownY = 0f
    private var mChangeVolume = false
    private var mChangePosition = false
    private var mDownPosition = 0
    private var mGestureDownVolume = 0
    private var mSeekTimePosition = 0

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    open fun initView(context: Context) {
        View.inflate(context, R.layout.sc_video_player, this)

        start.setOnClickListener(this)
        fullscreen.setOnClickListener(this)
        progress.setOnSeekBarChangeListener(this)
        layout_bottom.setOnClickListener(this)
        surface_container.setOnClickListener(this)
        surface_container.setOnTouchListener(this)

        mScreenWidth = getContext().resources.displayMetrics.widthPixels
        mScreenHeight = getContext().resources.displayMetrics.heightPixels
        mAudioManager = getContext().getSystemService(Context.AUDIO_SERVICE) as AudioManager
        mHandler = Handler()
    }

    open fun setUp(url: String, screen: Int) {
        if (!TextUtils.isEmpty(this.url) && TextUtils.equals(this.url, url)) {
            return
        }
        this.url = url
        currentScreen = screen
        setUiWitStateAndScreen(CURRENT_STATE_NORMAL)
    }

    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.start) {
            if (TextUtils.isEmpty(url)) {
                Toast.makeText(context, resources.getString(R.string.no_url), Toast.LENGTH_SHORT).show()
                return
            }
            if (currentState == CURRENT_STATE_NORMAL || currentState == CURRENT_STATE_ERROR) {
                if (!url.startsWith("file") && !JCUtils.isWifiConnected(context) && !WIFI_TIP_DIALOG_SHOWED) {
                    showWifiDialog()
                    return
                }
                prepareMediaPlayer()
                onEvent(if (currentState != CURRENT_STATE_ERROR) JCUserAction.ON_CLICK_START_ICON else JCUserAction.ON_CLICK_START_ERROR)
            } else if (currentState == CURRENT_STATE_PLAYING) {
                onEvent(JCUserAction.ON_CLICK_PAUSE)
                Log.d(TAG, "pauseVideo [" + this.hashCode() + "] ")
                JCMediaManager.instance().mediaPlayer.pause()
                setUiWitStateAndScreen(CURRENT_STATE_PAUSE)
            } else if (currentState == CURRENT_STATE_PAUSE) {
                onEvent(JCUserAction.ON_CLICK_RESUME)
                JCMediaManager.instance().mediaPlayer.start()
                setUiWitStateAndScreen(CURRENT_STATE_PLAYING)
            } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
                onEvent(JCUserAction.ON_CLICK_START_AUTO_COMPLETE)
                prepareMediaPlayer()
            }
        } else if (i == R.id.fullscreen) {
            if (currentState == CURRENT_STATE_AUTO_COMPLETE) return
            if (currentScreen == SCREEN_WINDOW_FULLSCREEN) { //quit fullscreen
                backPress()
            } else {
                onEvent(JCUserAction.ON_ENTER_FULLSCREEN)
                startWindowFullscreen()
            }
        } else if (i == R.id.surface_container && currentState == CURRENT_STATE_ERROR) {
            prepareMediaPlayer()
        }
    }

    private fun prepareMediaPlayer() {
        JCVideoPlayerManager.completeAll()
        initTextureView()
        addTextureView()
        val mAudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
        JCUtils.scanForActivity(context).window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        JCMediaManager.CURRENT_PLAYING_URL = url
        JCMediaManager.CURRENT_PLING_LOOP = loop
        setUiWitStateAndScreen(CURRENT_STATE_PREPARING)
        ScVideoPlayerManager.setFirstFloor(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        val id = v.id
        if (id == R.id.surface_container) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    mTouchingProgressBar = true
                    mDownX = x
                    mDownY = y
                    mChangeVolume = false
                    mChangePosition = false
                }
                MotionEvent.ACTION_MOVE -> {
                    val deltaX = x - mDownX
                    var deltaY = y - mDownY
                    val absDeltaX = Math.abs(deltaX)
                    val absDeltaY = Math.abs(deltaY)
                    if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
                        if (!mChangePosition && !mChangeVolume) {
                            if (absDeltaX > THRESHOLD || absDeltaY > THRESHOLD) {
                                cancelProgressTimer()
                                if (absDeltaX >= THRESHOLD) {
                                    if (currentState != CURRENT_STATE_ERROR) {
                                        mChangePosition = true
                                        mDownPosition = getCurrentPositionWhenPlaying()
                                    }
                                } else {
                                    mChangeVolume = true
                                    mGestureDownVolume = mAudioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC)
                                }
                            }
                        }
                    }
                    if (mChangePosition) {
                        val totalTimeDuration = getDuration()
                        mSeekTimePosition = (mDownPosition + deltaX * totalTimeDuration / mScreenWidth).toInt()
                        if (mSeekTimePosition > totalTimeDuration) mSeekTimePosition = totalTimeDuration
                        val seekTime = JCUtils.stringForTime(mSeekTimePosition)
                        val totalTime = JCUtils.stringForTime(totalTimeDuration)
                        showProgressDialog(deltaX, seekTime, mSeekTimePosition, totalTime, totalTimeDuration)
                    }
                    if (mChangeVolume) {
                        deltaY = -deltaY
                        val max = mAudioManager!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                        val deltaV = (max * deltaY * 3 / mScreenHeight).toInt()
                        mAudioManager!!.setStreamVolume(AudioManager.STREAM_MUSIC, mGestureDownVolume + deltaV, 0)
                        val volumePercent = (mGestureDownVolume * 100 / max + deltaY * 3 * 100 / mScreenHeight).toInt()
                        showVolumeDialog(-deltaY, volumePercent)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    mTouchingProgressBar = false
                    dismissProgressDialog()
                    dismissVolumeDialog()
                    if (mChangePosition) {
                        onEvent(JCUserAction.ON_TOUCH_SCREEN_SEEK_POSITION)
                        JCMediaManager.instance().mediaPlayer.seekTo(mSeekTimePosition)
                        val duration = getDuration()
                        val progress = mSeekTimePosition * 100 / if (duration == 0) 1 else duration
                        this.progress.progress = progress
                    }
                    if (mChangeVolume) {
                        onEvent(JCUserAction.ON_TOUCH_SCREEN_SEEK_VOLUME)
                    }
                    startProgressTimer()
                }
            }
        }
        return false
    }

    var widthRatio = 16
    var heightRatio = 9
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN || currentScreen == SCREEN_WINDOW_TINY) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            return
        }
        if (widthRatio != 0 && heightRatio != 0) {
            val specWidth = MeasureSpec.getSize(widthMeasureSpec)
            val specHeight = (specWidth * heightRatio.toFloat() / widthRatio).toInt()
            setMeasuredDimension(specWidth, specHeight)
            val childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(specWidth, MeasureSpec.EXACTLY)
            val childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(specHeight, MeasureSpec.EXACTLY)
            getChildAt(0).measure(childWidthMeasureSpec, childHeightMeasureSpec)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }

    private fun initTextureView() {
        removeTextureView()
        JCMediaManager.textureView = JCResizeTextureView(context)
        JCMediaManager.textureView.surfaceTextureListener = JCMediaManager.instance()
    }

    private fun addTextureView() {
        Log.d(TAG, "addTextureView [" + this.hashCode() + "] ")
        val layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER)
        surface_container.addView(JCMediaManager.textureView, layoutParams)
    }

    private fun removeTextureView() {
        JCMediaManager.savedSurfaceTexture = null
        if (JCMediaManager.textureView != null && JCMediaManager.textureView.parent != null) {
            (JCMediaManager.textureView.parent as ViewGroup).removeView(JCMediaManager.textureView)
        }
    }

    private fun setUiWitStateAndScreen(state: Int) {
        currentState = state
        when (currentState) {
            CURRENT_STATE_NORMAL -> {
                cancelProgressTimer()
                if (isCurrentJcvd()) { //这个if是无法取代的，否则进入全屏的时候会releaseMediaPlayer
                    JCMediaManager.instance().releaseMediaPlayer()
                }
            }
            CURRENT_STATE_PREPARING -> resetProgressAndTime()
            CURRENT_STATE_PLAYING, CURRENT_STATE_PAUSE, CURRENT_STATE_PLAYING_BUFFERING_START -> startProgressTimer()
            CURRENT_STATE_ERROR -> cancelProgressTimer()
            CURRENT_STATE_AUTO_COMPLETE -> {
                cancelProgressTimer()
                this.progress.progress = 100
                current.text = total.text
            }
        }
    }

    private fun startProgressTimer() {
        cancelProgressTimer()
        UPDATE_PROGRESS_TIMER = Timer()
        mProgressTimerTask = ProgressTimerTask()
        UPDATE_PROGRESS_TIMER!!.schedule(mProgressTimerTask, 0, 300)
    }

    private fun cancelProgressTimer() {
        if (UPDATE_PROGRESS_TIMER != null) {
            UPDATE_PROGRESS_TIMER!!.cancel()
        }
        if (mProgressTimerTask != null) {
            mProgressTimerTask!!.cancel()
        }
    }

    open fun onPrepared() {
        if (currentState != CURRENT_STATE_PREPARING) return
        if (seekToInAdvance != 0) {
            JCMediaManager.instance().mediaPlayer.seekTo(seekToInAdvance)
            seekToInAdvance = 0
        } else {
            val position = JCUtils.getSavedProgress(context, url)
            if (position != 0) {
                JCMediaManager.instance().mediaPlayer.seekTo(position)
            }
        }
        startProgressTimer()
        setUiWitStateAndScreen(CURRENT_STATE_PLAYING)
    }

    private fun clearFullscreenLayout() {
        val vp = JCUtils.scanForActivity(context).findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val oldF = vp.findViewById<View>(FULLSCREEN_ID)
        val oldT = vp.findViewById<View>(TINY_ID)
        if (oldF != null) {
            vp.removeView(oldF)
        }
        if (oldT != null) {
            vp.removeView(oldT)
        }
        showSupportActionBar(context)
    }

    fun onAutoCompletion() {
        Runtime.getRuntime().gc()
        onEvent(JCUserAction.ON_AUTO_COMPLETE)
        dismissVolumeDialog()
        dismissProgressDialog()
        setUiWitStateAndScreen(CURRENT_STATE_AUTO_COMPLETE)
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            backPress()
        }
        JCUtils.saveProgress(context, url, 0)
    }

    fun onCompletion() {
        //save position
        if (currentState == CURRENT_STATE_PLAYING || currentState == CURRENT_STATE_PAUSE) {
            val position = getCurrentPositionWhenPlaying()
            //            int duration = getDuration();
            JCUtils.saveProgress(context, url, position)
        }
        setUiWitStateAndScreen(CURRENT_STATE_NORMAL)
        // 清理缓存变量
        surface_container.removeView(JCMediaManager.textureView)
        JCMediaManager.instance().currentVideoWidth = 0
        JCMediaManager.instance().currentVideoHeight = 0
        val mAudioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        mAudioManager.abandonAudioFocus(onAudioFocusChangeListener)
        JCUtils.scanForActivity(context).window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        clearFullscreenLayout()
        JCUtils.getAppCompActivity(context).requestedOrientation = NORMAL_ORIENTATION
        JCMediaManager.textureView = null
        JCMediaManager.savedSurfaceTexture = null
    }

    fun playOnThisJcvd() {
        onEvent(if (currentScreen == JCVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN) JCUserAction.ON_QUIT_FULLSCREEN else JCUserAction.ON_QUIT_TINYSCREEN)
        currentState = JCVideoPlayerManager.getSecondFloor().currentState
        clearFloatScreen()
        setUiWitStateAndScreen(currentState)
        addTextureView()
    }

    private fun clearFloatScreen() {
        JCUtils.getAppCompActivity(context).requestedOrientation = NORMAL_ORIENTATION
        showSupportActionBar(context)
        val secJcvd = ScVideoPlayerManager.getCurrentJcvd()
        secJcvd?.surface_container?.removeView(JCMediaManager.textureView)
        val vp = JCUtils.scanForActivity(context).findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        vp.removeView(secJcvd)
        JCVideoPlayerManager.setSecondFloor(null)
    }

    fun autoFullscreen(x: Float) {
        if (isCurrentJcvd()
                && currentState == CURRENT_STATE_PLAYING && currentScreen != SCREEN_WINDOW_FULLSCREEN && currentScreen != SCREEN_WINDOW_TINY) {
            if (x > 0) {
                JCUtils.getAppCompActivity(context).requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            } else {
                JCUtils.getAppCompActivity(context).requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
            startWindowFullscreen()
        }
    }

    fun autoQuitFullscreen() {
        if (System.currentTimeMillis() - lastAutoFullscreenTime > 2000 && isCurrentJcvd()
                && currentState == CURRENT_STATE_PLAYING && currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            lastAutoFullscreenTime = System.currentTimeMillis()
            backPress()
        }
    }

    fun onSeekComplete() {}
    fun onError(what: Int, extra: Int) {
        if (what != 38 && what != -38) {
            setUiWitStateAndScreen(CURRENT_STATE_ERROR)
            if (isCurrentJcvd()) {
                JCMediaManager.instance().releaseMediaPlayer()
            }
        }
    }

    fun onInfo(what: Int, extra: Int) {
        if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
            BACKUP_PLAYING_BUFFERING_STATE = currentState
            setUiWitStateAndScreen(CURRENT_STATE_PLAYING_BUFFERING_START)
        } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
            if (BACKUP_PLAYING_BUFFERING_STATE != -1) {
                setUiWitStateAndScreen(BACKUP_PLAYING_BUFFERING_STATE)
                BACKUP_PLAYING_BUFFERING_STATE = -1
            }
        }
    }

    fun onVideoSizeChanged() {
        JCMediaManager.textureView.setVideoSize(JCMediaManager.instance().videoSize)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        cancelProgressTimer()
        var vpdown = parent
        while (vpdown != null) {
            vpdown.requestDisallowInterceptTouchEvent(true)
            vpdown = vpdown.parent
        }
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        onEvent(JCUserAction.ON_SEEK_POSITION)
        startProgressTimer()
        var vpup = parent
        while (vpup != null) {
            vpup.requestDisallowInterceptTouchEvent(false)
            vpup = vpup.parent
        }
        if (currentState != CURRENT_STATE_PLAYING &&
                currentState != CURRENT_STATE_PAUSE) return
        val time = seekBar.progress * getDuration() / 100
        JCMediaManager.instance().mediaPlayer.seekTo(time)
    }

    private fun startWindowFullscreen() {
        hideSupportActionBar(context)
        JCUtils.getAppCompActivity(context).requestedOrientation = FULLSCREEN_ORIENTATION
        val vp = JCUtils.scanForActivity(context).findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val old = vp.findViewById<View>(FULLSCREEN_ID)
        if (old != null) {
            vp.removeView(old)
        }
        surface_container.removeView(JCMediaManager.textureView)
        try {
            val constructor = this@ScVideoPlayer.javaClass.getConstructor(Context::class.java) as Constructor<ScVideoPlayer>
            val jcVideoPlayer = constructor.newInstance(context)
            jcVideoPlayer.id = FULLSCREEN_ID
            val lp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            vp.addView(jcVideoPlayer, lp)
            jcVideoPlayer.setUp(url, JCVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN)
            jcVideoPlayer.setUiWitStateAndScreen(currentState)
            jcVideoPlayer.addTextureView()
            ScVideoPlayerManager.setSecondFloor(jcVideoPlayer)
            CLICK_QUIT_FULLSCREEN_TIME = System.currentTimeMillis()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun startWindowTiny() {
        onEvent(JCUserAction.ON_ENTER_TINYSCREEN)
        if (currentState == CURRENT_STATE_NORMAL || currentState == CURRENT_STATE_ERROR) return
        val vp = JCUtils.scanForActivity(context) //.getWindow().getDecorView();
                .findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
        val old = vp.findViewById<View>(TINY_ID)
        if (old != null) {
            vp.removeView(old)
        }
        surface_container.removeView(JCMediaManager.textureView)
        try {
            val constructor = this@ScVideoPlayer.javaClass.getConstructor(Context::class.java) as Constructor<ScVideoPlayer>
            val jcVideoPlayer = constructor.newInstance(context)
            jcVideoPlayer.id = TINY_ID
            val lp = LayoutParams(400, 400)
            lp.gravity = Gravity.RIGHT or Gravity.BOTTOM
            vp.addView(jcVideoPlayer, lp)
            jcVideoPlayer.setUp(url, JCVideoPlayerStandard.SCREEN_WINDOW_TINY)
            jcVideoPlayer.setUiWitStateAndScreen(currentState)
            jcVideoPlayer.addTextureView()
            ScVideoPlayerManager.setSecondFloor(jcVideoPlayer)
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class ProgressTimerTask : TimerTask() {
        override fun run() {
            if (currentState == CURRENT_STATE_PLAYING || currentState == CURRENT_STATE_PAUSE || currentState == CURRENT_STATE_PLAYING_BUFFERING_START) { //                Log.v(TAG, "onProgressUpdate " + position + "/" + duration + " [" + this.hashCode() + "] ");
                mHandler!!.post { setProgressAndText() }
            }
        }
    }

    private fun getCurrentPositionWhenPlaying(): Int {
        var position = 0
        if (currentState == CURRENT_STATE_PLAYING || currentState == CURRENT_STATE_PAUSE || currentState == CURRENT_STATE_PLAYING_BUFFERING_START) {
            position = try {
                JCMediaManager.instance().mediaPlayer.currentPosition
            } catch (e: IllegalStateException) {
                e.printStackTrace()
                return position
            }
        }
        return position
    }

    private fun getDuration(): Int {
        var duration = 0
        duration = try {
            JCMediaManager.instance().mediaPlayer.duration
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            return duration
        }
        return duration
    }

    fun setProgressAndText() {
        val position = getCurrentPositionWhenPlaying()
        val duration = getDuration()
        val progress = position * 100 / if (duration == 0) 1 else duration
        if (!mTouchingProgressBar) {
            if (progress != 0) this.progress.progress = progress
        }
        if (position != 0) current.text = JCUtils.stringForTime(position)
        total.text = JCUtils.stringForTime(duration)
    }

    fun setBufferProgress(bufferProgress: Int) { //        int percent = progressBarValue(bufferProgress);
//        if (percent > 95) percent = 100;
        if (bufferProgress != 0) this.progress.secondaryProgress = bufferProgress
    }

    private fun resetProgressAndTime() {
        this.progress.progress = 0
        this.progress.secondaryProgress = 0
        current.text = JCUtils.stringForTime(0)
        total.text = JCUtils.stringForTime(0)
    }

    fun release() {
        if (url == JCMediaManager.CURRENT_PLAYING_URL &&
                System.currentTimeMillis() - CLICK_QUIT_FULLSCREEN_TIME > FULL_SCREEN_NORMAL_DELAY) {
            if (JCVideoPlayerManager.getSecondFloor() != null &&
                    JCVideoPlayerManager.getSecondFloor().currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            } else if (JCVideoPlayerManager.getSecondFloor() == null && JCVideoPlayerManager.getFirstFloor() != null && JCVideoPlayerManager.getFirstFloor().currentScreen == SCREEN_WINDOW_FULLSCREEN) { //直接全屏
            } else {
                Log.d(TAG, "release [" + this.hashCode() + "]")
                releaseAllVideos()
            }
        }
    }

    private fun isCurrentJcvd(): Boolean {
        return (ScVideoPlayerManager.getCurrentJcvd() != null && ScVideoPlayerManager.getCurrentJcvd() === this)
    }

    private fun onEvent(type: Int) {
        if (JC_USER_EVENT != null && isCurrentJcvd()) {
            JC_USER_EVENT!!.onEvent(type, url, currentScreen)
        }
    }

    class JCAutoFullscreenListener : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[SensorManager.DATA_X]
            val y = event.values[SensorManager.DATA_Y]
            val z = event.values[SensorManager.DATA_Z]
            //过滤掉用力过猛会有一个反向的大数值
            if ((x > -15 && x < -10 || x < 15 && x > 10) && Math.abs(y) < 1.5) {
                if (System.currentTimeMillis() - lastAutoFullscreenTime > 2000) {
                    if (JCVideoPlayerManager.getCurrentJcvd() != null) {
                        JCVideoPlayerManager.getCurrentJcvd().autoFullscreen(x)
                    }
                    lastAutoFullscreenTime = System.currentTimeMillis()
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    private fun showWifiDialog() {}
    private fun showProgressDialog(deltaX: Float,
                                   seekTime: String?, seekTimePosition: Int,
                                   totalTime: String?, totalTimeDuration: Int) {
    }

    private fun dismissProgressDialog() {}
    private fun showVolumeDialog(deltaY: Float, volumePercent: Int) {}
    private fun dismissVolumeDialog() {}

    companion object {
        const val TAG = "JieCaoVideoPlayer"
        var ACTION_BAR_EXIST = true
        var TOOL_BAR_EXIST = true
        var FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR
        var NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        var SAVE_PROGRESS = true
        var WIFI_TIP_DIALOG_SHOWED = false
        const val FULLSCREEN_ID = 33797
        const val TINY_ID = 33798
        const val THRESHOLD = 80
        const val FULL_SCREEN_NORMAL_DELAY = 300
        var CLICK_QUIT_FULLSCREEN_TIME: Long = 0
        const val SCREEN_LAYOUT_NORMAL = 0
        const val SCREEN_LAYOUT_LIST = 1
        const val SCREEN_WINDOW_FULLSCREEN = 2
        const val SCREEN_WINDOW_TINY = 3
        const val CURRENT_STATE_NORMAL = 0
        const val CURRENT_STATE_PREPARING = 1
        const val CURRENT_STATE_PLAYING = 2
        const val CURRENT_STATE_PLAYING_BUFFERING_START = 3
        const val CURRENT_STATE_PAUSE = 5
        const val CURRENT_STATE_AUTO_COMPLETE = 6
        const val CURRENT_STATE_ERROR = 7
        protected var JC_USER_EVENT: JCUserAction? = null
        protected var UPDATE_PROGRESS_TIMER: Timer? = null
        var lastAutoFullscreenTime: Long = 0
        fun backPress(): Boolean {
            Log.i(TAG, "backPress")
            if (System.currentTimeMillis() - CLICK_QUIT_FULLSCREEN_TIME < FULL_SCREEN_NORMAL_DELAY) return false
            if (JCVideoPlayerManager.getSecondFloor() != null) {
                CLICK_QUIT_FULLSCREEN_TIME = System.currentTimeMillis()
                JCVideoPlayerManager.getFirstFloor().playOnThisJcvd()
                return true
            } else if (JCVideoPlayerManager.getFirstFloor() != null &&
                    (JCVideoPlayerManager.getFirstFloor().currentScreen == SCREEN_WINDOW_FULLSCREEN ||
                            JCVideoPlayerManager.getFirstFloor().currentScreen == SCREEN_WINDOW_TINY)) { //以前我总想把这两个判断写到一起，这分明是两个独立是逻辑
                CLICK_QUIT_FULLSCREEN_TIME = System.currentTimeMillis()
                //直接退出全屏和小窗
                JCVideoPlayerManager.getCurrentJcvd().currentState = CURRENT_STATE_NORMAL
                JCVideoPlayerManager.getFirstFloor().clearFloatScreen()
                JCMediaManager.instance().releaseMediaPlayer()
                JCVideoPlayerManager.setFirstFloor(null)
                return true
            }
            return false
        }

        var onAudioFocusChangeListener: OnAudioFocusChangeListener = object : OnAudioFocusChangeListener {
            override fun onAudioFocusChange(focusChange: Int) {
                when (focusChange) {
                    AudioManager.AUDIOFOCUS_GAIN -> {
                    }
                    AudioManager.AUDIOFOCUS_LOSS -> {
                        releaseAllVideos()
                    }
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                        if (JCMediaManager.instance().mediaPlayer != null &&
                                JCMediaManager.instance().mediaPlayer.isPlaying) {
                            JCMediaManager.instance().mediaPlayer.pause()
                        }
                    }
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    }
                }
            }
        }

        fun releaseAllVideos() {
            if (System.currentTimeMillis() - CLICK_QUIT_FULLSCREEN_TIME > FULL_SCREEN_NORMAL_DELAY) {
                JCVideoPlayerManager.completeAll()
                JCMediaManager.instance().releaseMediaPlayer()
            }
        }

        fun setJcUserAction(jcUserEvent: JCUserAction?) {
            JC_USER_EVENT = jcUserEvent
        }

        fun startFullscreen(context: Context?, _class: Class<*>, url: String, vararg objects: Any?) {
            hideSupportActionBar(context)
            JCUtils.getAppCompActivity(context).requestedOrientation = FULLSCREEN_ORIENTATION
            val vp = JCUtils.scanForActivity(context) //.getWindow().getDecorView();
                    .findViewById<View>(Window.ID_ANDROID_CONTENT) as ViewGroup
            val old = vp.findViewById<View>(FULLSCREEN_ID)
            if (old != null) {
                vp.removeView(old)
            }
            try {
                val constructor: Constructor<ScVideoPlayer> = _class.getConstructor(Context::class.java) as Constructor<ScVideoPlayer>
                val jcVideoPlayer = constructor.newInstance(context)
                jcVideoPlayer.id = FULLSCREEN_ID
                val lp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                vp.addView(jcVideoPlayer, lp)
                jcVideoPlayer.setUp(url, JCVideoPlayerStandard.SCREEN_WINDOW_FULLSCREEN)
                CLICK_QUIT_FULLSCREEN_TIME = System.currentTimeMillis()
                jcVideoPlayer.start.performClick()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun hideSupportActionBar(context: Context?) {
            if (ACTION_BAR_EXIST) {
                val ab = JCUtils.getAppCompActivity(context).supportActionBar
                if (ab != null) {
                    ab.setShowHideAnimationEnabled(false)
                    ab.hide()
                }
            }
            if (TOOL_BAR_EXIST) {
                JCUtils.getAppCompActivity(context).window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        fun showSupportActionBar(context: Context?) {
            if (ACTION_BAR_EXIST) {
                val ab = JCUtils.getAppCompActivity(context).supportActionBar
                if (ab != null) {
                    ab.setShowHideAnimationEnabled(false)
                    ab.show()
                }
            }
            if (TOOL_BAR_EXIST) {
                JCUtils.getAppCompActivity(context).window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            }
        }

        fun clearSavedProgress(context: Context?, url: String?) {
            JCUtils.clearSavedProgress(context, url)
        }
    }
}