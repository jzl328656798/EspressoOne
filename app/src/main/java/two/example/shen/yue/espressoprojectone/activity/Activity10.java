package two.example.shen.yue.espressoprojectone.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;

/**
 * Created by queen on 2018/7/25.
 * Author: Queen
 * Date: 2018/7/25
 * Time: 上午10:00
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity10 extends BaseActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity10);
//        getWindow().setBackgroundDrawable(null);
        sop1();
    }


    private void sop1(){
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPermission = ContextCompat.checkSelfPermission(Activity10.this, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Activity10.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                ActivityCompat.requestPermissions(Activity10.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else {
//                wbContent.loadUrl("https://xxxxxxxxxxxxxxxxxxxxxxxx");
            }
        }
    }
}
