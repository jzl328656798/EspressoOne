package two.example.shen.yue.espressoprojectone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by queen on 2018/7/31.
 * Author: Queen
 * Date: 2018/7/31
 * Time: 下午4:41
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity10View2 extends AppCompatTextView {

    private Paint mPaint1;
    private Paint mPaint2;

    public Activity10View2(Context context) {
        super(context);
        initData();
    }

    public Activity10View2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public Activity10View2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData(){
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_light));
        mPaint1.setStyle(Paint.Style.FILL);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.YELLOW);
        mPaint2.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f,0.0f,getMeasuredWidth(),getMeasuredHeight(),mPaint1);
        canvas.drawRect(10.0f,10.0f,getMeasuredWidth()-10,getMeasuredHeight()-10,mPaint2);
        canvas.save();
        canvas.translate(10,0);
        super.onDraw(canvas);
        canvas.restore();
    }
}
