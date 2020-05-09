package two.example.shen.yue.espressoprojectone.rv;

import android.view.View;

/**
 * Author: Queen
 * Date: 2020/5/9 8:30 AM
 * Describe: 条目点击事件
 */
public interface ItemListener<T> {

    void onItemClick(View view, T entity, int position);

    boolean onItemLongClick(View view, T entity, int position);

}
