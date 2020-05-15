package two.example.shen.yue.espressoprojectone.test13.observer

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * Author: Queen
 * Date: 2020/5/15 1:42 PM
 * Describe: Test13Activity4Observer
 */
class Test13Activity4Observer : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connectListener() {
        Log.i("queen", "connectListener")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnectListener() {
        Log.i("queen", "disconnectListener")
    }

}