package two.example.shen.yue.espressoprojectone.rv;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import two.example.shen.yue.espressoprojectone.R;

/**
 * Author: Queen
 * Date: 2020/5/11 8:14 AM
 * Describe: RVTestActivity1
 */
public class RVTestActivity1 extends BaseRViewActivity {

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
                Toast.makeText(RVTestActivity1.this, "onItemClick:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, RVTestBean1 entity, int position) {
                Toast.makeText(RVTestActivity1.this, "onItemLongClick:" + position, Toast.LENGTH_SHORT).show();
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
        adapter = new RViewAdapter<>(list, new RViewItem<RVTestBean1>() {
            @Override
            public int getItemLayout() {
                return R.layout.item_rv_test1;
            }

            @Override
            public boolean openClick() {
                return true;
            }

            @Override
            public boolean isItemView(RVTestBean1 entity, int position) {
                return true;
            }

            @Override
            public boolean convert(RViewHolder holder, RVTestBean1 entity, int position) {
                TextView tv = holder.getView(R.id.tv_rv_test1);
                tv.setText(entity.getText());
                return true;
            }
        });
        return adapter;
    }
}
