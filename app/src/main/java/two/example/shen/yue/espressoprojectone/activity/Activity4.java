package two.example.shen.yue.espressoprojectone.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;
import two.example.shen.yue.espressoprojectone.utils.EasyCallBack;

/**
 * Created by queen on 2018/3/29.
 */

public class Activity4 extends BaseActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                checkPermission(Manifest.permission.CAMERA);
                break;
            case R.id.button2:
                requestPermission(Manifest.permission.CAMERA);
                break;
            case R.id.button3:
                somePermissionPermanentlyDenied(Manifest.permission.CAMERA);
                break;
            case R.id.button4:
                sop1();
                break;
        }
    }


    private void sop1(){
        if (checkPermission(Manifest.permission.CAMERA)){
            startActivity(Activity1.class);
        }else {
            requestPermission(Manifest.permission.CAMERA, new EasyCallBack() {
                @Override
                public void success() {
                    startActivity(Activity1.class);
                }

                @Override
                public void failure() {
//                    requestPermission(Manifest.permission.CAMERA);
                }

                @Override
                public void nextFailure() {

                }
            });
        }
    }
}
