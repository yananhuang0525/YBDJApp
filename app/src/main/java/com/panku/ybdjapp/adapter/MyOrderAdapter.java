package com.panku.ybdjapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Date：2017/4/11
 * Time: 11:39
 * author: hyn
 *
 */

public class MyOrderAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> mTitleList;

    public MyOrderAdapter(FragmentManager fm, List<Fragment> mViewList, List<String> mTitleList) {
        super(fm);
        this.fragments = mViewList;
        this.mTitleList = mTitleList;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position % mTitleList.size());//页卡标题
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
}
