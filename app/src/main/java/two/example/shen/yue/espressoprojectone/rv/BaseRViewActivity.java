package two.example.shen.yue.espressoprojectone.rv;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

/**
 * Author: Queen
 * Date: 2020/5/9 12:56 PM
 * Describe: TODO
 */
public abstract class BaseRViewActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshListener {

    protected RViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper = new RViewHelper.Builder(this, this).build();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public SwipeRefreshLayout createSwipeRefresh() {
        return null;
    }

    @Override
    public RecyclerView createRecyclerView() {
        return null;
    }

    @Override
    public RViewAdapter createRecyclerViewAdapter() {
        return null;
    }

    @Override
    public boolean isSupportPaging() {
        return false;
    }

    @Override
    public void onRefresh() {

    }
}
