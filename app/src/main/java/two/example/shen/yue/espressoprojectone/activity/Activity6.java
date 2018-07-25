package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;

/**
 * Created by queen on 2018/3/29.
 */

public class Activity6 extends BaseActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6);

        setData();
    }


    private void setData(){

    }

    public void onClick1(View view){
        sop1();
    }

    public void sop1(){

//        商户APP工程中引入微信JAR包，调用API前，需要先向微信注册您的APPID，代码如下：

//        IWXAPI api = WXAPIFactory.createWXAPI(this, null);
        IWXAPI api = WXAPIFactory.createWXAPI(this, "wx6e959f7e9aa85c41");
        // 将该app注册到微信

//        api.registerApp("wxd930ea5d5a258f4f");



//        IWXAPI api;

        PayReq request = new PayReq();

        request.appId = "wxb4ba3c02aa476ea1";

        request.partnerId = "1900006771";

        request.prepayId= "wx11091510249632c8c9bdfa951312141724";

        request.packageValue = "Sign=WXPay";

        request.nonceStr= "11fec6f6d067665d448b4a122d79937f";

        request.timeStamp= "1526001310";

        request.sign= "244E347F29834CE0A72737995BBFC146";

        api.sendReq(request);
    }

}
