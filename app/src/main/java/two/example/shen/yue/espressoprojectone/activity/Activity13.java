package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.adapter.Activity13Adapter;

public class Activity13 extends AppCompatActivity implements View.OnClickListener {

    private ListView lv_activity12;
    private Activity13Adapter adapter;
    private List<String> list = new ArrayList<>();
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_13);
        lv_activity12 = findViewById(R.id.lv_activity12);
        lv_activity12.setEmptyView(findViewById(R.id.tv_null_data));
        findViewById(R.id.btn_activity12_1).setOnClickListener(this);
        findViewById(R.id.btn_activity12_2).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
        setAdapter(list);
    }

    private void initData() {
        for (; i < 30; i++) {
            list.add(i + "" + i + "" + i);
        }
    }

    private void setAdapter(List<String> list) {
        if (adapter == null) {
            adapter = new Activity13Adapter(this, list);
            lv_activity12.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            lv_activity12.setSelection(list.size() - 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_activity12_1:
                add();
                break;
            case R.id.btn_activity12_2:
                subtraction();
                break;
            default:
                break;
        }
    }

    private void add() {
        i++;
        list.add(i + "" + i + "" + i);
        setAdapter(list);
    }

    private void subtraction() {
        if (list.size() > 0) {
            list.remove(0);
            setAdapter(list);
        }

    }
}
