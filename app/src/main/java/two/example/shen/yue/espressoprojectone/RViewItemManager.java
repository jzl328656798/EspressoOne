package two.example.shen.yue.espressoprojectone;

import android.support.v4.util.SparseArrayCompat;

import two.example.shen.yue.espressoprojectone.rv.RViewHolder;
import two.example.shen.yue.espressoprojectone.rv.RViewItem;

/**
 * Author: Queen
 * Date: 2020/5/9 8:56 AM
 * Describe: RV
 */
public class RViewItemManager<T> {

    private SparseArrayCompat<RViewItem<T>> styles = new SparseArrayCompat<>();

    public void addStyles(RViewItem<T> item) {
        if (item != null) {
            styles.put(styles.size(), item);
        }
    }

    public int getItemViewStylesCount() {
        return styles.size();
    }

    public int getItemViewType(T entity, int position) {
        for (int i = styles.size() - 1; i >= 0; i--) {
            RViewItem<T> item = styles.valueAt(i);
            if (item.isItemView(entity, position)) {
                return styles.keyAt(i);
            }
        }
        throw new NullPointerException("无法找到该View类型");
    }

    public RViewItem getRViewItem(int viewType) {
        return styles.get(viewType);
    }

    public void convert(RViewHolder holder, T entity, int position) {
        for (int i = 0; i < styles.size(); i++) {
            RViewItem<T> item = styles.valueAt(i);
            if (item.isItemView(entity, position)) {
                item.convert(holder,entity,position);
                return;
            }
        }
        throw new NullPointerException("无法找到对应Item");
    }
}
