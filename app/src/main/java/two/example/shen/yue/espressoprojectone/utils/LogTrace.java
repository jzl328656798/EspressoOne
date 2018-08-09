package two.example.shen.yue.espressoprojectone.utils;

import android.util.Log;

/**
 * Created by queen on 2018/8/7.
 * Author: Queen
 * Date: 2018/8/7
 * Time: 下午2:12
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class LogTrace {

    public static void i(String str) {
        Log.i("Queen", str);
    }

    public static void i(String tag, String str) {
        Log.i("Queen", tag + ":" + str);
    }
}
