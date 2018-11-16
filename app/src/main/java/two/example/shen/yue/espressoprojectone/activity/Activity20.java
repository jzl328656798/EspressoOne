package two.example.shen.yue.espressoprojectone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.authsdk.AuthConfig;
import com.tencent.authsdk.AuthSDKApi;
import com.tencent.authsdk.IDCardInfo;
import com.tencent.authsdk.callback.IdentityCallback;

import org.json.JSONObject;

import me.jessyan.autosize.internal.CancelAdapt;
import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.auth.AppConfig;
import two.example.shen.yue.espressoprojectone.auth.NetManager;
import two.example.shen.yue.espressoprojectone.auth.Sign;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;


public class Activity20 extends BaseActivity implements CancelAdapt, View.OnClickListener {

    private TextView mSdkResult;
    private EditText mIdcardEdit;
    private EditText mNameEdit;
    private EditText mTokenEdit;
    private EditText mPhoneEdit;
    private EditText mSceneID;
    private EditText mEdAppid, mEdSecretKey;
    private RadioGroup mRgServers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_20);

        mSdkResult = (TextView) findViewById(R.id.sdk_result);

        Button verityBtn = (Button) findViewById(R.id.authenticate_btn);
        verityBtn.setOnClickListener(this);

        Button secondaryAuthBtn = (Button) findViewById(R.id.secondary_auth_btn);
        secondaryAuthBtn.setOnClickListener(this);

        mIdcardEdit = (EditText) findViewById(R.id.idcard_edit);
        mNameEdit = (EditText) findViewById(R.id.name_edit);
        mTokenEdit = (EditText) findViewById(R.id.token_edit);
        mPhoneEdit = (EditText) findViewById(R.id.phone_edit);
        mSceneID = (EditText) findViewById(R.id.scene_id);

        mEdAppid = (EditText) findViewById(R.id.appid);
        mEdSecretKey = (EditText) findViewById(R.id.secretkey);
        mRgServers = (RadioGroup) findViewById(R.id.server_group);
        initData();

    }

//    APPID 4912
//    SecretKey 9270366a9c90f3c3a264fb0f24bc4554
//    ResultKey e47e9ba565b71739404625ff395ba438
//    Expired 600

    private void initData() {
        mEdAppid.setText("4912");
        mEdSecretKey.setText("9270366a9c90f3c3a264fb0f24bc4554");
        mNameEdit.setText("姜卓磊");
        mIdcardEdit.setText("220182198907103911");
        mPhoneEdit.setText("13917275427");
    }

    private void startAuthenticate(int type) {
        String appid = mEdAppid.getText().toString();
        appid = TextUtils.isEmpty(appid) ? AppConfig.APP_ID : appid;

        String serverUrl = getServerUrl();

        AuthConfig.Builder configBuilder = new AuthConfig.Builder(serverUrl, appid, R.class
                .getPackage().getName());

        //生成appauth接口的签名
        String signature = getSignature("appauth");
        if (type == 0) {//首次
            //如果需要传入手机号
            String phoneNum = mPhoneEdit.getText().toString();
            //在仅活体的流程时需要传入身份证和姓名,其他情况下不需要传。
            String idcard = mIdcardEdit.getText().toString();
            String name = mNameEdit.getText().toString();
            //sceneID
            String sceneID = mSceneID.getText().toString();

            configBuilder.signature(signature).phoneNum(phoneNum).idcard(idcard).name(name)
                    .sceneID(sceneID);
        } else {
            String idcard = mIdcardEdit.getText().toString();
            String name = mNameEdit.getText().toString();
            String token = mTokenEdit.getText().toString();
            String sceneID = mSceneID.getText().toString();
            configBuilder.signature(signature).idcard(idcard).name(name).sceneID(sceneID).pickey
                    (token).secondary(true);
        }
        AuthSDKApi.startMainPage(this, configBuilder.build(), mListener);

    }

    private IdentityCallback mListener = new IdentityCallback() {
        @Override
        public void onIdentityResult(Intent data) {
            String token = data.getStringExtra(AuthSDKApi.EXTRA_TOKEN);
            boolean indexback = data.getBooleanExtra(AuthSDKApi.INDEX_BACK, false);
            Log.d("tollcc", "indexback: " + indexback);
            Log.i("test", "token = " + token);
            boolean identityStatus = data.getBooleanExtra(AuthSDKApi.EXTRA_IDENTITY_STATUS, false);
            boolean secondary = data.getBooleanExtra(AuthSDKApi.SECONDARY, false);
            if (identityStatus) {
                if (!secondary) {
                    mTokenEdit.setText(token);
                }
                IDCardInfo idCardInfo = data.getExtras().getParcelable(AuthSDKApi
                        .EXTRA_IDCARD_INFO);
                if (idCardInfo != null) {
                    mNameEdit.setText(idCardInfo.getName() == null ? "" : idCardInfo.getName());
                    mIdcardEdit.setText(idCardInfo.getIDcard() == null ? "" : idCardInfo
                            .getIDcard());
                }
            }
            String result = getResultStr(identityStatus);
            mSdkResult.setText(result);

            /** 通过token和identityStatus可以请求保存结果
             * TODO DEMO中为方便测试在客户端直接调用了getdetectinfo.php接口获取验证信息，并对结果进行了RSA解密
             * TODO 后续调用方需通过应用服务器请求保存结果，由服务器请求getdetectinfo.php获取验证信息并进行RSA解密*/
            getDetectInfo(token);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.authenticate_btn:
                startAuthenticate(0);
                break;
            case R.id.secondary_auth_btn:
                startAuthenticate(1);
                break;
        }
    }

    private void getDetectInfo(final String token) {
        if (!TextUtils.isEmpty(token)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final JSONObject response = NetManager.getInstance().getDetectInfo(token);
                    if (response != null) {
                        Log.i("test", "response = " + response.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showResult(response);
                            }
                        });

                    }
                }
            }).start();
        } else {
            Toast.makeText(this, "token 为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResult(JSONObject response) {
        int errorCode = response.optInt("errorcode");
        if (errorCode == 0) {
            String data = response.optString("data");
            if (!TextUtils.isEmpty(data)) {
                //TODO 目前data数据已经采用RSA进行加密，需要进行解密,该工作都需要在应用服务器上进行

            }
        }
    }

    private String getResultStr(boolean identityStatus) {
        StringBuilder builder = new StringBuilder("实名认证结果: ");
        if (identityStatus) {
            builder.append("验证成功");
        } else {
            builder.append("验证失败");
        }
        return builder.toString();
    }

    //TODO 根据method生成签名信息，DEMO中为方便测试在客户端生成，调用方需改成通过应用服务器接口生成
    private String getSignature(String method) {
        StringBuffer mySign = new StringBuffer("");

        String appid = mEdAppid.getText().toString();
        appid = TextUtils.isEmpty(appid) ? AppConfig.APP_ID : appid;

        String secret = mEdSecretKey.getText().toString();
        secret = TextUtils.isEmpty(secret) ? AppConfig.SECRET_KEY : secret;

        Sign.appSign(appid, method, secret, AppConfig.EXPIRED_SECONDS, mySign);
        return mySign.toString();
    }

    private String getServerUrl() {
        switch (mRgServers.getCheckedRadioButtonId()) {
            case R.id.server_test:
                return "https://iauth-test.sparta.html5.qq.com/new/cgi-bin/";
            case R.id.server_sandbox:
                return "https://iauth-sandbox.wecity.qq.com/new/cgi-bin/";
            case R.id.server_normal:
                return "https://iauth.wecity.qq.com/new/cgi-bin/";
            default:
                return "https://iauth-sandbox.wecity.qq.com/new/cgi-bin/";
        }
    }

}
