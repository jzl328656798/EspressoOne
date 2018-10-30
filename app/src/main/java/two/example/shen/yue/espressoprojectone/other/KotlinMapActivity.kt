package two.example.shen.yue.espressoprojectone.other

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import com.tencent.map.geolocation.TencentLocation
import com.tencent.map.geolocation.TencentLocationListener
import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.map.geolocation.TencentLocationRequest
import com.tencent.mapsdk.raster.model.*
import com.tencent.tencentmap.mapsdk.map.TencentMap
import kotlinx.android.synthetic.main.activity_map.*
import two.example.shen.yue.espressoprojectone.R




/**
 * Created by queen on 2018/10/30.
 * Author: Queen
 * Date: 2018/10/30
 * Time: 下午3:19
 * Email: zhuolei.jiang@softlinker.com & jiangzhuolei@126.com
 * Describe: TODO
 */
class KotlinMapActivity : com.tencent.tencentmap.mapsdk.map.MapActivity(), TencentLocationListener {

    private lateinit var mTencentMap: TencentMap
    private lateinit var mTencentLocation: TencentLocation
    private lateinit var mTencentLocationManager: TencentLocationManager
    private lateinit var mMarker: Marker
    private lateinit var mCircle: Circle

//    private var mRequestParams = ""

    override fun onStatusUpdate(p0: String?, p1: Int, p2: String?) {
    }

    override fun onLocationChanged(p0: TencentLocation?, p1: Int, p2: String?) {
        if (p1 == TencentLocation.ERROR_OK){
            mTencentLocation = p0!!

            var latLngLocation = LatLng(mTencentLocation.latitude,mTencentLocation.longitude)

            if (mMarker == null){
                mMarker = mTencentMap.addMarker(
                        MarkerOptions()
                                .position(latLngLocation)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)))
            }else{
                mMarker.position = latLngLocation
            }
            if (mCircle == null){
                mCircle = mTencentMap.addCircle(
                        CircleOptions()
                                .center(latLngLocation)
                                .radius(mTencentLocation.accuracy.toDouble())
                                .fillColor(0x884433ff.toInt())
                                .strokeColor(0xaa1122ee.toInt())
                                .strokeWidth(1.toFloat()))
            }else{
                mCircle.center = latLngLocation
                mCircle.radius = mTencentLocation.accuracy.toDouble()
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        mTencentMap = map_view.map
        mTencentLocationManager = TencentLocationManager.getInstance(this)
        mTencentLocationManager.coordinateType = TencentLocationManager.COORDINATE_TYPE_GCJ02



        if (Build.VERSION.SDK_INT >= 23) {
            val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissions, 0)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        startLocation()
    }

    override fun onPause() {
        super.onPause()
        stopLocation()
    }


    private fun startLocation() {
        val request = TencentLocationRequest.create()
        request.interval = 5000
        mTencentLocationManager.requestLocationUpdates(request, this)

//        mRequestParams = (request.toString() + ", 坐标系="
//                + DemoUtils.toString(mLocationManager.getCoordinateType()))
    }

    private fun stopLocation() {
        mTencentLocationManager.removeUpdates(this)
    }
}

