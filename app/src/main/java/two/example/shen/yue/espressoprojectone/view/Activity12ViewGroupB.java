package two.example.shen.yue.espressoprojectone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

import two.example.shen.yue.espressoprojectone.utils.LogTrace;

/**
 * Created by queen on 2018/8/7.
 * Author: Queen
 * Date: 2018/8/7
 * Time: 下午2:10
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity12ViewGroupB extends ViewGroup {

    protected final String TAG = getClass().getSimpleName();

    public Activity12ViewGroupB(Context context) {
        super(context);
    }

    public Activity12ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Activity12ViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogTrace.i(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogTrace.i(TAG,"onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogTrace.i(TAG,"onTouchEvent");
        return super.onTouchEvent(event);
    }
}
