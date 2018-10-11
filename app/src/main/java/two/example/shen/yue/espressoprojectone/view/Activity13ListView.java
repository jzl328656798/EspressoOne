package two.example.shen.yue.espressoprojectone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by queen on 2018/8/9.
 * Author: Queen
 * Date: 2018/8/9
 * Time: 上午10:02
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity13ListView extends ListView {

    public Activity13ListView(Context context) {
        super(context);
    }

    public Activity13ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Activity13ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int
            scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean
            isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY,
                maxOverScrollX, maxOverScrollY, isTouchEvent);
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY,
//                maxOverScrollX, 200, isTouchEvent);
    }
}
