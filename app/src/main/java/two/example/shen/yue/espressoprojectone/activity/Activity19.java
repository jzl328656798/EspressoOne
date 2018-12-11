package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.buick.jni.Buick;

import me.jessyan.autosize.internal.CancelAdapt;
import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;


public class Activity19 extends BaseActivity implements CancelAdapt {


    private TextView tv_activity19_1;
    private TextView tv_activity19_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_19);

        tv_activity19_1 = findViewById(R.id.tv_activity19_1);
        tv_activity19_2 = findViewById(R.id.tv_activity19_2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Buick buick = new Buick();
        tv_activity19_1.setText("test:" + buick.getReceiveDataKey(this.getApplicationContext()));
    }
}
