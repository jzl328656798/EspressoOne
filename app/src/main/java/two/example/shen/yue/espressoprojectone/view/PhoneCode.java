package two.example.shen.yue.espressoprojectone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by queen on 2018/3/29.
 */

public class PhoneCode extends RelativeLayout {

    private TextView textView;
    private View viewLine;
    private Context mContext;
    private int count = 4;
    private List<TextView> listTextView;
    private List<View> listViewLine;

    public PhoneCode(Context context) {
        super(context);
        this.mContext = context;
    }

    public PhoneCode(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }


    private void initView() {

        listTextView = new ArrayList<>();
        listViewLine = new ArrayList<>();

        textView = new TextView(mContext);
        viewLine = new View(mContext);
    }
}
