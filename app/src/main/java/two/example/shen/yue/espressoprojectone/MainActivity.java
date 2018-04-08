package two.example.shen.yue.espressoprojectone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import two.example.shen.yue.espressoprojectone.activity.Activity1;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_main_btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        btn_main_btn1 = findViewById(R.id.btn_main_btn1);
        btn_main_btn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_main_btn1:
                startActivity(Activity1.class);
                break;
            default:
                break;
        }
    }
}
