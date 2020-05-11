package two.example.shen.yue.espressoprojectone.rv;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Queen
 * Date: 2020/5/11 8:14 AM
 * Describe: RVTestActivity1
 */
public class RVTestActivity2 extends BaseRViewActivity {

    RViewAdapter<RVTestBean1> adapter;
    List<RVTestBean1> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
        initData();
    }

    private void initListener() {
        adapter.setItemListener(new ItemListener<RVTestBean1>() {
            @Override
            public void onItemClick(View view, RVTestBean1 entity, int position) {
                Toast.makeText(RVTestActivity2.this, "onItemClick:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RVTestBean1 entity, int position) {
                Toast.makeText(RVTestActivity2.this, "onItemLongClick:" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void initData() {
        list.clear();
        for (int i = 0; i < 50; i++) {
            list.add(new RVTestBean1(i + ""));
        }
        notifyAdapterDataSetChanged(list);
    }

    @Override
    public void onRefresh() {
        initData();
    }


    @Override
    public RViewAdapter createRecyclerViewAdapter() {
        adapter = new MultiAdapter(list);
        return adapter;
    }
}
