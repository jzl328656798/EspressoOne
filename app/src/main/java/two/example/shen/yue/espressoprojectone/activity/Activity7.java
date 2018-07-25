package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.adapter.Activity7PopAdapter;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;
import two.example.shen.yue.espressoprojectone.view.SelectPopupWindow;

/**
 * Created by queen on 2018/6/21.
 * Author: Queen
 * Date: 2018/6/21
 * Time: 下午3:56
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity7 extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_activity7;
    private TextView tv_activity7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity7);
        ll_activity7 = findViewById(R.id.ll_activity7);
        tv_activity7 = findViewById(R.id.tv_activity7);
        tv_activity7.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_activity7:
                showPop(view);
                break;
            default:
                break;
        }
    }

    private void showPop(View view) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(i + "" + i + i + "" + i + i + "" + i);
        }

        Activity7PopAdapter adapter = new Activity7PopAdapter(this, list);

        int width = -1;

        int height = ll_activity7.getHeight() - (int)tv_activity7.getY() - tv_activity7.getHeight();

        SelectPopupWindow window = new SelectPopupWindow(this, width, height);

        window.setAdapter(adapter);

        window.showAsDropDown(view, 0, 0);
    }
}
