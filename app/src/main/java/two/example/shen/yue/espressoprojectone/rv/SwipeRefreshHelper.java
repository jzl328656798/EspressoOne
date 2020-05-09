package two.example.shen.yue.espressoprojectone.rv;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Author: Queen
 * Date: 2020/5/9 12:26 PM
 * Describe: 下拉刷新帮助类
 */
public class SwipeRefreshHelper {

    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshListener swipeRefreshListener;

    public static SwipeRefreshHelper create(SwipeRefreshLayout layout){
        return new SwipeRefreshHelper(layout);
    }

    private SwipeRefreshHelper(SwipeRefreshLayout swipeRefreshLayout){
        this.swipeRefreshLayout = swipeRefreshLayout;
        init();
    }

    private void init() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark,android.R.color.holo_green_dark,android.R.color.holo_blue_dark);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            if (swipeRefreshListener !=null){
                swipeRefreshListener.onRefresh();
            }
        });
    }

    public void setSwipeRefreshListener(SwipeRefreshListener swipeRefreshListener) {
        this.swipeRefreshListener = swipeRefreshListener;
    }
}
