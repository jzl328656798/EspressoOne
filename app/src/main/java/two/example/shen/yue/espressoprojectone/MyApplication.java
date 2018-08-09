package two.example.shen.yue.espressoprojectone;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import com.aliyun.sls.android.sdk.utils.IPService;

import two.example.shen.yue.espressoprojectone.utils.CrashHandler;

/**
 * Created by queen on 2018/7/30.
 * Author: Queen
 * Date: 2018/7/30
 * Time: 上午9:47
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class MyApplication extends Application {

    private Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        new Thread(()-> IPService.getInstance().asyncGetIp(IPService.DEFAULT_URL,handlerAli)).start();
        CrashHandler.getInstance(this);
    }

    private Handler handlerAli = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IPService.HANDLER_MESSAGE_GETIP_CODE:
//                    AliLogUtils.SOURCE_IP = (String) msg.obj;
//                    startActivity(new Intent(application, CrashDialogActivity.class));
                    return;
            }
            super.handleMessage(msg);
        }
    };

}
