package two.example.shen.yue.espressoprojectone.test17;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import two.example.shen.yue.espressoprojectone.BaseActivity;
import two.example.shen.yue.espressoprojectone.R;

public class Test17Activity1 extends BaseActivity {

    private String url = "http://v.juhe.cn/historyWeather/citys?province_id=2&key=bb52107206585ab074f5e59a8c73875b";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test17_1);
        TextView textView = findViewById(R.id.text_view);
        findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpManager.sendJsonRequest(url, null, ResponseClass.class, new IJsonDataTransform<ResponseClass>() {
                    @Override
                    public void onSuccess(ResponseClass o) {
                        log(o.toString());
                        textView.setText(o.toString());
                    }

                    @Override
                    public void onFailure() {

                    }
                });
            }
        });
    }
}
