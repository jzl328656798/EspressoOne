package two.example.shen.yue.espressoprojectone.test9

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_test9_1.*
import two.example.shen.yue.espressoprojectone.BaseActivity
import two.example.shen.yue.espressoprojectone.R
import two.example.shen.yue.espressoprojectone.test9.view.JCVideoPlayer

/**
 * Author: Queen
 * Date: 2020/4/20 2:21 PM
 * Describe: TODO
 */
class Test9Activity1 : BaseActivity() {

    var sensorManager: SensorManager? = null
    var sensorEventListener: JCVideoPlayer.JCAutoFullscreenListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test9_1)

        val list = ArrayList<String>()
        list.add("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8")
        list.add("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8")
        list.add("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8")
        list.add("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8")
        list.add("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8")
        list.add("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8")
        list.add("https://cmsqa-oss.sgmlink.com/5d47f514e458d/try/1.m3u8")

        val adapter = Test9Adapter1(this, list)
        list_view.adapter = adapter
    }

    override fun onBackPressed() {
        if (JCVideoPlayer.backPress()) {
            return
        }
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        val accelerometerSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager?.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(sensorEventListener)
        JCVideoPlayer.releaseAllVideos()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}