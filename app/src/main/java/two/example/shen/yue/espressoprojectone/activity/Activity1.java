package two.example.shen.yue.espressoprojectone.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;
import two.example.shen.yue.espressoprojectone.zxing.QRCodeView;
import two.example.shen.yue.espressoprojectone.zxing.ZXingView;

/**
 * Created by queen on 2018/3/29.
 */

public class Activity1 extends BaseActivity implements QRCodeView.Delegate {


    private QRCodeView mQRCodeView;
    private TextView tv_activity1;

    public static void sop(Context mContext){
        Intent intent = new Intent(mContext,Activity1.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        tv_activity1 = findViewById(R.id.tv_activity1);
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

        Log.i("Queen", "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        tv_activity1.setText(result);
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();

        mQRCodeView.showScanRect();

        mQRCodeView.startSpotAndShowRect();

    }


    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

//    private void initData(){
//        mEdAppid.setText("4912");
//        mEdSecretKey.setText("9270366a9c90f3c3a264fb0f24bc4554");
//        mNameEdit.setText("姜卓磊");
//        mIdcardEdit.setText("220182198907103911");
//        mPhoneEdit.setText("13917275427");
//    }

}
