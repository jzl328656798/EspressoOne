package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.adapter.Activity8ViewPagerAdapter;
import two.example.shen.yue.espressoprojectone.base.BaseActivity;
import two.example.shen.yue.espressoprojectone.fragment.Activity8Fragment1;
import two.example.shen.yue.espressoprojectone.fragment.Activity8Fragment2;
import two.example.shen.yue.espressoprojectone.fragment.Activity8Fragment3;
import two.example.shen.yue.espressoprojectone.fragment.Activity8Fragment4;
import two.example.shen.yue.espressoprojectone.fragment.Activity8Fragment5;

/**
 * Created by queen on 2018/7/25.
 * Author: Queen
 * Date: 2018/7/25
 * Time: 上午10:00
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity8 extends BaseActivity {

    private TabLayout tl_activity8;
    private ViewPager vp_activity8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity8);
        tl_activity8 = findViewById(R.id.tl_activity8);
        vp_activity8 = findViewById(R.id.vp_activity8);

//        tl_activity8.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                vp_activity8.setCurrentItem(tab.getPosition(), true);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        List<Fragment> list = new ArrayList<>();
        list.add(new Activity8Fragment1());
        list.add(new Activity8Fragment2());
        list.add(new Activity8Fragment3());
        list.add(new Activity8Fragment4());
        list.add(new Activity8Fragment5());

        Activity8ViewPagerAdapter adapter = new Activity8ViewPagerAdapter(getSupportFragmentManager(),list);

        vp_activity8.setAdapter(adapter);

        tl_activity8.setupWithViewPager(vp_activity8);
    }
}
