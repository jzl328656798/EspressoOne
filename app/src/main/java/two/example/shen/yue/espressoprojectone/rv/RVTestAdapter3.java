package two.example.shen.yue.espressoprojectone.rv;

import android.widget.TextView;

import two.example.shen.yue.espressoprojectone.R;

/**
 * Author: Queen
 * Date: 2020/5/11 2:25 PM
 * Describe: RVTest
 */
public class RVTestAdapter3 implements RViewItem<RVTestBean1> {
    @Override
    public int getItemLayout() {
        return R.layout.item_rv_test2_c;
    }

    @Override
    public boolean openClick() {
        return true;
    }

    @Override
    public boolean isItemView(RVTestBean1 entity, int position){
        return entity.getType() == 2;
    }

    @Override
    public boolean convert(RViewHolder holder, RVTestBean1 entity, int position) {

        TextView textView = holder.getView(R.id.tv_rv_test1);
        textView.setText(entity.getText());

        return true;
    }
}
