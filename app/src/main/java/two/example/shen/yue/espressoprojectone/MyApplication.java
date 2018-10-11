package two.example.shen.yue.espressoprojectone;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Message;

import com.aliyun.sls.android.sdk.utils.IPService;
import com.qihoo360.replugin.RePlugin;

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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        RePlugin.App.attachBaseContext(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RePlugin.App.onCreate();
        application = this;
        new Thread(() -> IPService.getInstance().asyncGetIp(IPService.DEFAULT_URL, handlerAli))
                .start();
        CrashHandler.getInstance(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        RePlugin.App.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        RePlugin.App.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        RePlugin.App.onConfigurationChanged(newConfig);
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
