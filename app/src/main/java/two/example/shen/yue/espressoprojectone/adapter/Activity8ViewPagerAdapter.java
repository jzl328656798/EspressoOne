package two.example.shen.yue.espressoprojectone.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by queen on 2018/7/25.
 * Author: Queen
 * Date: 2018/7/25
 * Time: 上午10:50
 * Email: zhuolei.jiang@pactera.com & jiangzhuolei@126.com
 * Describe: TODO
 */
public class Activity8ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public Activity8ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }


    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "title:" + position;
    }
}
