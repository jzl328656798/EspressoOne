package two.example.shen.yue.espressoprojectone.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import two.example.shen.yue.espressoprojectone.utils.LogTrace;

/**
 * Created by queen on 2018/8/7.
 * Author: Queen
 * Date: 2018/8/7
 * Time: 下午2:11
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity12ViewA extends View {

    protected final String TAG = getClass().getSimpleName();

    public Activity12ViewA(Context context) {
        super(context);
    }

    public Activity12ViewA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Activity12ViewA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogTrace.i(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogTrace.i(TAG,"onTouchEvent");
        return super.onTouchEvent(event);
    }
}
