package two.example.shen.yue.espressoprojectone.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Map;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;

/**
 * Created by queen on 2018/3/29.
 */

public class Activity5 extends BaseActivity {

    private WebView wv_activity5;

    private String realm = "http://wxpay.wxutil.com";//十分重要的  是商户申请的域名


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5);

        wv_activity5 = findViewById(R.id.wv_activity5);

        wv_activity5.getSettings().setJavaScriptEnabled(true);

        wv_activity5.setWebViewClient(new MyWebviewClient());

        setData();
    }


    private void setData(){

        String fileUrl = "file:///android_asset/OpenApp.html";
        String fileUrl1 = "file:///android_asset/index.html";
//        wv_activity5.loadUrl("http://wxpay.wxutil.com/mch/pay/h5.v2.php");
        wv_activity5.loadUrl(fileUrl1);
    }


    private class MyWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //微信H5支付核心代码
            if (url.startsWith("weixin://wap/pay?")) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                return true;
            } else {
                Map<String, String> extraHeaders = new HashMap<>();
                extraHeaders.put("Referer", realm);
                view.loadUrl(url, extraHeaders);
            }
            return true;
        }
    }
}
