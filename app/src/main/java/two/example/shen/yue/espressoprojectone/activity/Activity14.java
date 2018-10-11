package two.example.shen.yue.espressoprojectone.activity;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import two.example.shen.yue.espressoprojectone.R;

public class Activity14 extends AppCompatActivity {

    private TextView tv_activity14;
    private Button btn_activity14;

    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_14);
        tv_activity14 = findViewById(R.id.tv_activity14);
        btn_activity14 = findViewById(R.id.btn_activity14);

        fingerprintManager = (FingerprintManager) this.getSystemService(Context
                .FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) this.getSystemService(Context.KEYGUARD_SERVICE);


        btn_activity14.setOnClickListener(e -> test());
    }

    private void test() {
        if (isFinger()) {
            addText("正在进行指纹验证");
            startListening();
        }
    }

    @TargetApi(23)
    private void startListening() {
        fingerprintManager.authenticate(null, new CancellationSignal(), 0, new FingerprintManager
                .AuthenticationCallback() {


            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                addText("onAuthenticationError");
            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                addText("onAuthenticationHelp");
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                addText("onAuthenticationSucceeded");
                addText("验证成功");
            }

            @Override
            public void onAuthenticationFailed() {
                addText("onAuthenticationFailed");
                addText("验证失败");
            }
        }, null);
    }

    @TargetApi(23)
    private boolean isFinger() {
        if (!fingerprintManager.isHardwareDetected()) {
            addText("这个手机没有指纹功能");
            return false;
        }

        if (!keyguardManager.isKeyguardSecure()) {
            addText("这个手机没有开启指纹");
            return false;
        }

        if (!fingerprintManager.hasEnrolledFingerprints()) {
            addText("这个手机没有录入指纹");
            return false;
        }

        return true;
    }

    private void addText(String str) {
        tv_activity14.append(str);
    }
}
