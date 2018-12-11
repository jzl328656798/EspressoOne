package two.example.shen.yue.espressoprojectone.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;
import two.example.shen.yue.espressoprojectone.utils.NotificationUtils;

/**
 * Created by queen on 2018/7/25.
 * Author: Queen
 * Date: 2018/7/25
 * Time: 上午10:00
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity9 extends BaseActivity {


    private TextView tv_activity9;
    private Button btn_activity9;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9);
        tv_activity9 = findViewById(R.id.tv_activity9);
        initData();

        btn_activity9 = findViewById(R.id.btn_activity9);
        btn_activity9.setOnClickListener(e -> {
            NotificationUtils utils = new NotificationUtils(this);
            utils.sendNotification("123", "123456789");
        });
    }

    private void initData() {
        append(Build.DEVICE);
        append(Build.BOARD);
        append(Build.BOOTLOADER);
        append(Build.BRAND);
        append(Build.DISPLAY);
        append(Build.FINGERPRINT);
        append(Build.getRadioVersion());
        append(Build.HARDWARE);
        append(Build.HOST);
        append(Build.ID);
        append(Build.TIME + "");
        append(Build.TYPE);
        append(Build.TAGS);
        append(Build.PRODUCT);
        append(Build.MODEL);
    }


    private void append(String str) {
        tv_activity9.append(str + "\n");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String str = intent.getStringExtra("test");
        Log.i(TAG, "str:" + str);
    }
}
