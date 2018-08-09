package two.example.shen.yue.espressoprojectone.utils;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import two.example.shen.yue.espressoprojectone.R;

/**
 * Created by queen on 2018/7/30.
 * Author: Queen
 * Date: 2018/7/30
 * Time: 上午9:23
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */

public class CrashDialogActivity extends Activity {

    private TextView tv_crash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carash_dialog);
        ErrorBean errorBean = (ErrorBean) getIntent().getSerializableExtra("error_bean");
        //错误解决
        tv_crash = findViewById(R.id.tv_crash);
        tv_crash.setText(errorBean.getErrorMsg());
    }
}
