package two.example.shen.yue.espressoprojectone;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import two.example.shen.yue.espressoprojectone.activity.Activity1;
import two.example.shen.yue.espressoprojectone.activity.Activity3;
import two.example.shen.yue.espressoprojectone.activity.Activity4;
import two.example.shen.yue.espressoprojectone.activity.Activity5;
import two.example.shen.yue.espressoprojectone.activity.Activity6;
import two.example.shen.yue.espressoprojectone.activity.Activity7;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;
import two.example.shen.yue.espressoprojectone.service.TestIntentService1;
import two.example.shen.yue.espressoprojectone.utils.PermissionUtils;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    private Button btn_main_btn1;
    private Button btn_main_btn2;
    private Button btn_main_btn3;
    private Button btn_main_btn4;
    private Button btn_main_btn5;
    private Button btn_main_btn6;
    private Button btn_main_btn7;
    private Button btn_main_btn8;
    private Button btn_main_btn9;
    private Button btn_main_btn10;
    private Button btn_main_btn11;
    private Button btn_main_btn12;

    private Timer timer;
    private TimerTask task;
    private int index = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                Log.i("Queen", "Activity Thread id:" + Thread.currentThread().getId());
                Log.i("Queen", "Activity Thread name:" + Thread.currentThread().getName());
                Log.i("Queen", "test:" + index++);
//                startService();
            }
        };

//        timer.schedule(task, 5000, 1000);
    }

    private void initView() {
        btn_main_btn1 = findViewById(R.id.btn_main_btn1);
        btn_main_btn2 = findViewById(R.id.btn_main_btn2);
        btn_main_btn3 = findViewById(R.id.btn_main_btn3);
        btn_main_btn4 = findViewById(R.id.btn_main_btn4);
        btn_main_btn5 = findViewById(R.id.btn_main_btn5);
        btn_main_btn6 = findViewById(R.id.btn_main_btn6);
        btn_main_btn7 = findViewById(R.id.btn_main_btn7);
        btn_main_btn8 = findViewById(R.id.btn_main_btn8);
        btn_main_btn9 = findViewById(R.id.btn_main_btn9);
        btn_main_btn10 = findViewById(R.id.btn_main_btn10);
        btn_main_btn11 = findViewById(R.id.btn_main_btn11);
        btn_main_btn12 = findViewById(R.id.btn_main_btn12);

        btn_main_btn1.setOnClickListener(this);
        btn_main_btn2.setOnClickListener(this);
        btn_main_btn3.setOnClickListener(this);
        btn_main_btn4.setOnClickListener(this);
        btn_main_btn5.setOnClickListener(this);
        btn_main_btn6.setOnClickListener(this);
        btn_main_btn7.setOnClickListener(this);
        btn_main_btn8.setOnClickListener(this);
        btn_main_btn9.setOnClickListener(this);
        btn_main_btn10.setOnClickListener(this);
        btn_main_btn11.setOnClickListener(this);
        btn_main_btn12.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_btn1:
                startActivity(Activity1.class);
                break;
            case R.id.btn_main_btn2:
                startActivity(Activity3.class);
                break;
            case R.id.btn_main_btn3:
                startActivity(Activity4.class);
                break;
            case R.id.btn_main_btn4:
                startActivity(Activity5.class);
                break;
            case R.id.btn_main_btn5:
                startActivity(Activity5.class);
                break;
            case R.id.btn_main_btn6:
                startActivity(Activity6.class);
                break;
            case R.id.btn_main_btn9:
                startActivity(Activity7.class);
                break;
            case R.id.btn_main_btn10:
                break;
            case R.id.btn_main_btn11:
                break;
            case R.id.btn_main_btn12:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        requestCodeQRCodePermissions();
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }

    //检查权限
    private void getPermission() {
        checkPermission(new PermissionUtils.CheckPermissionListener() {
            @Override
            public void permissionOnSuccessGrant() {
                startActivity(Activity1.class);
            }

            @Override
            public void permissionOnDenied() {
            }
        }, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    private void startService() {
        Intent intent = new Intent(this, TestIntentService1.class);
        startService(intent);
    }
}
