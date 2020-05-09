package two.example.shen.yue.espressoprojectone.rv;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Author: Queen
 * Date: 2020/5/9 8:32 AM
 * Describe: RV帮助类
 */
public class RViewHelper<T> {

    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshHelper swipeRefreshHelper;
    private RecyclerView recyclerView;
    private RViewAdapter<T> adapter;
    private int startPageNumber = 1;
    private boolean isSupportPaging;
    private SwipeRefreshListener listener;
    private int currentPageNum;

    private RViewHelper(Builder<T> builder) {
        this.swipeRefreshLayout = builder.create.createSwipeRefresh();
        this.recyclerView = builder.create.createRecyclerView();
        this.adapter = builder.create.createRecyclerViewAdapter();
        this.context = builder.create.context();
        this.isSupportPaging = builder.create.isSupportPaging();
        this.listener = builder.listener;
        this.currentPageNum = this.startPageNumber;
        if (swipeRefreshLayout != null) {
            swipeRefreshHelper = SwipeRefreshHelper.create(swipeRefreshLayout);
        }
        init();
    }

    private void init() {
        //初始化
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //下拉刷新
        if (swipeRefreshHelper != null) {
            swipeRefreshHelper.setSwipeRefreshListener(() -> {
                if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                if (listener != null) {
                    listener.onRefresh();
                }
            });
        }
    }


    public static class Builder<T> {

        private RViewCreate<T> create;
        private SwipeRefreshListener listener;

        public Builder(RViewCreate<T> create, SwipeRefreshListener listener) {
            this.create = create;
            this.listener = listener;
        }

        public RViewHelper build() {
            return new RViewHelper<>(this);
        }
    }

}
