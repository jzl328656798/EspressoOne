package two.example.shen.yue.espressoprojectone.rv;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;

import two.example.shen.yue.espressoprojectone.R;

/**
 * Author: Queen
 * Date: 2020/5/9 12:56 PM
 * Describe: BaseRViewActivity
 */
public abstract class BaseRViewActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshListener {

    protected RViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_test1);
        helper = new RViewHelper.Builder(this, this).build();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public SwipeRefreshLayout createSwipeRefresh() {
        return findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public RecyclerView createRecyclerView() {
        return findViewById(R.id.recycler_view);
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

    protected void notifyAdapterDataSetChanged(List data) {
        helper.notifyAdapterDataSetChanged(data);
    }

}
