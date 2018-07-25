package two.example.shen.yue.espressoprojectone.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by queen on 2018/6/29.
 * Author: Queen
 * Date: 2018/6/29
 * Time: 下午12:40
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class TestIntentService1 extends IntentService {

    public TestIntentService1() {
        super("test1");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Queen","Service onCreate");
        Log.i("Queen","Service Thread Id:"+Thread.currentThread().getId());
        Log.i("Queen","Service Thread name:"+Thread.currentThread().getName());
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.i("Queen","Service onStartCommand");
        Log.i("Queen","Service Thread Id:"+Thread.currentThread().getId());
        Log.i("Queen","Service Thread name:"+Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Queen","Service onDestroy");
    }
}
