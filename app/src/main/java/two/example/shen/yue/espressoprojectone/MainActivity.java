package two.example.shen.yue.espressoprojectone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import two.example.shen.yue.espressoprojectone.activity.Activity1;
import two.example.shen.yue.espressoprojectone.activity.Activity3;
import two.example.shen.yue.espressoprojectone.activity.Activity4;
import two.example.shen.yue.espressoprojectone.activity.Activity5;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_main_btn1;
    private Button btn_main_btn2;
    private Button btn_main_btn3;
    private Button btn_main_btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn_main_btn1 = findViewById(R.id.btn_main_btn1);
        btn_main_btn2 = findViewById(R.id.btn_main_btn2);
        btn_main_btn3 = findViewById(R.id.btn_main_btn3);
        btn_main_btn3 = findViewById(R.id.btn_main_btn4);
        btn_main_btn1.setOnClickListener(this);
        btn_main_btn2.setOnClickListener(this);
        btn_main_btn3.setOnClickListener(this);
        btn_main_btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_main_btn1:
                startActivity(Activity1.class);
                break;
            case R.id.btn_main_btn2:
                startActivity(Activity3.class);
                break;
            case R.id.btn_main_btn3:
                startActivity(Activity4.class);
                break;
            case R.id.btn_main_btn4:
                startActivity(Activity5.class);
                break;
            default:
                break;
        }
    }
}
