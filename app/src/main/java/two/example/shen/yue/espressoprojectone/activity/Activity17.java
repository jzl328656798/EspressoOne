package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;
import two.example.shen.yue.espressoprojectone.kotlinone.view.PageLayout;


public class Activity17 extends BaseActivity {


    private PageLayout pageLayout;
    private LinearLayout ll_default;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_17);


        ll_default = findViewById(R.id.ll_default);


        pageLayout = new PageLayout.Builder(Activity17.this)
                .initPage(ll_default)
                .setCustomView(getLayoutInflater().inflate(R.layout.layout_custom,null))
                .setOnRetryListener(new PageLayout.OnRetryClickListener() {
                    @Override
                    public void onRetry() {
                        loadData();
                    }
                }).create();


        loadData();
    }


    private void loadData() {
        pageLayout.showLoading();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pageLayout.hide();
            }
        }, 3000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus_activity17,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_loading:
                loadData();
                break;
            case R.id.menu_empty:
                pageLayout.showEmpty();
                break;
            case R.id.menu_error:
                pageLayout.showError();
                break;
            case R.id.menu_content:
                pageLayout.hide();
                break;
            case R.id.menu_customer:
                pageLayout.showCustom();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
