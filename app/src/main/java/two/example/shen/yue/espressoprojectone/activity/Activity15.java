package two.example.shen.yue.espressoprojectone.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import two.example.shen.yue.espressoprojectone.R;
import two.example.shen.yue.espressoprojectone.fragment.Activity8Fragment1;

public class Activity15 extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout mTabLayout;

    private ViewPager mViewPager;

    private static String[] sUris = new String[]{"home", "mine", "stub"};
    private static String[] sTitles = new String[]{"Home", "Mine", "Stub"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_15);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            Fragment fragment = Small.createObject("fragment-v4", sUris[position],
// Activity15.this);
//            if (fragment == null) {
//                fragment = PlaceholderFragment.newInstance(position + 1);
//            }
//            return fragment;
            return new Activity8Fragment1();
        }

        @Override
        public int getCount() {
            return sTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sTitles[position];
        }
    }
}
