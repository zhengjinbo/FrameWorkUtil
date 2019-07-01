package com.zjb.frameworkutil.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.zjb.frameworkutil.R;
import com.zjb.frameworkutil.base.BaseActivity;
import com.zjb.frameworkutil.fragment.Fragment1;
import com.zjb.frameworkutil.fragment.Fragment2;
import com.zjb.frameworkutil.fragment.Fragment3;
import com.zjb.frameworkutil.fragment.Fragment4;
import com.zjb.frameworkutil.utils.LogUtil;
import com.zjb.frameworkutil.views.CustomToast;
import com.zjb.frameworkutil.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private long mExitTime;
    private NoScrollViewPager viewPager;
    private RadioButton rb1, rb2, rb3, rb4;
    private List<Fragment> fragments;
    private MyPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI() {
        viewPager = findViewById(R.id.viewPager);
        rb1 = findViewById(R.id.RadioButton1);
        rb2 = findViewById(R.id.RadioButton2);
        rb3 = findViewById(R.id.RadioButton3);
        rb4 = findViewById(R.id.RadioButton4);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());

        viewPager.setNoScroll(false);
//        viewPager.setOffscreenPageLimit(fragments.size());
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                switch (i) {
                    case 0:
                        rb1.setChecked(true);
                        switchState();
                        break;
                    case 1:
                        rb2.setChecked(true);
                        switchState();
                        break;
                    case 2:
                        rb3.setChecked(true);
                        switchState();
                        break;
                    case 3:
                        rb4.setChecked(true);
                        switchState();
                        break;

                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.RadioButton1:
                viewPager.setCurrentItem(0);
                switchState();
                break;
            case R.id.RadioButton2:
                viewPager.setCurrentItem(1);
                switchState();
                break;
            case R.id.RadioButton3:
                viewPager.setCurrentItem(2);
                switchState();
                break;
            case R.id.RadioButton4:
                viewPager.setCurrentItem(3);
                switchState();
                break;
        }

    }

    private void switchState() {

        if (rb1.isChecked()) {
            rb1.setTextColor(getResources().getColor(R.color.red));
            rb2.setTextColor(getResources().getColor(R.color.black));
            rb3.setTextColor(getResources().getColor(R.color.black));
            rb4.setTextColor(getResources().getColor(R.color.black));
        } else if (rb2.isChecked()) {
            rb1.setTextColor(getResources().getColor(R.color.black));
            rb2.setTextColor(getResources().getColor(R.color.red));
            rb3.setTextColor(getResources().getColor(R.color.black));
            rb4.setTextColor(getResources().getColor(R.color.black));
        } else if (rb3.isChecked()) {
            rb1.setTextColor(getResources().getColor(R.color.black));
            rb2.setTextColor(getResources().getColor(R.color.black));
            rb3.setTextColor(getResources().getColor(R.color.red));
            rb4.setTextColor(getResources().getColor(R.color.black));
        } else if (rb4.isChecked()) {
            rb1.setTextColor(getResources().getColor(R.color.black));
            rb2.setTextColor(getResources().getColor(R.color.black));
            rb3.setTextColor(getResources().getColor(R.color.black));
            rb4.setTextColor(getResources().getColor(R.color.red));

        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }

        public int getCount() {
            return fragments.size();
        }
    }

}
