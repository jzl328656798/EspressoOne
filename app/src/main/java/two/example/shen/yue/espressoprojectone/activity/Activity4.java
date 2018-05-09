package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;

/**
 * Created by queen on 2018/3/29.
 */

public class Activity4 extends BaseActivity {

    private LinearLayout ll_activity4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);

        ll_activity4 = findViewById(R.id.ll_activity4);

        setData();
    }


    private void setData(){
        for (int i=0;i<10;i++){
            View view = View.inflate(this,R.layout.item_activity4,null);
            Button btn_item_activity4_1 = view.findViewById(R.id.btn_item_activity4_1);
            Button btn_item_activity4_2 = view.findViewById(R.id.btn_item_activity4_2);
            final TextView tv_item_activity4_test1 = view.findViewById(R.id.tv_item_activity4_test1);
            final TextView tv_item_activity4_test2 = view.findViewById(R.id.tv_item_activity4_test2);
            btn_item_activity4_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_item_activity4_test1.setVisibility(View.GONE);
                    tv_item_activity4_test1.setText("123456");
                    tv_item_activity4_test2.setVisibility(View.VISIBLE);
                }
            });
            btn_item_activity4_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tv_item_activity4_test1.setVisibility(View.VISIBLE);
                    tv_item_activity4_test2.setVisibility(View.GONE);
                }
            });
            ll_activity4.addView(view);
        }
    }

}
