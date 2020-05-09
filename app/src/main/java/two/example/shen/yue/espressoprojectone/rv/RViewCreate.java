package two.example.shen.yue.espressoprojectone.rv;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * Author: Queen
 * Date: 2020/5/9 12:39 PM
 * Describe: RV 创建类
 */
public interface RViewCreate<T> {

    Context context();

    SwipeRefreshLayout createSwipeRefresh();

    RecyclerView createRecyclerView();

    RViewAdapter<T> createRecyclerViewAdapter();

    boolean isSupportPaging();

}
