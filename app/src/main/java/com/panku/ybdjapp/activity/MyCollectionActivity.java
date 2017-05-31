package com.panku.ybdjapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.MyOrderAdapter;
import com.panku.ybdjapp.fragment.MyCollectionDrugsFragment;
import com.panku.ybdjapp.fragment.MyCollectionDrugstoreFragment;
import com.panku.ybdjapp.fragment.MyCollectionOrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/24
 * Time: 16:29
 * author: hyn
 */
public class MyCollectionActivity extends FragmentActivity {
    private LinearLayout ll_back;
    private TextView tv_title;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();//页卡视图集合
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private FragmentManager fragmentManager;
    private Fragment drugsFragment = new MyCollectionDrugsFragment();
    private Fragment drugstoreFragment = new MyCollectionDrugstoreFragment();
    private Fragment orderFragment = new MyCollectionOrderFragment();
    private MyOrderAdapter myOrderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_my_collection);
        initView();
        tv_title.setText("我的收藏");
        init();
    }

    private void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        mTabLayout = (TabLayout) findViewById(R.id.tl);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        //添加fragment
        fragments.add(drugsFragment);
        fragments.add(drugstoreFragment);
        fragments.add(orderFragment);
        //添加页卡标题
        mTitleList.add("药品");
        mTitleList.add("药店");
        mTitleList.add("订单");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
        myOrderAdapter = new MyOrderAdapter(fragmentManager, fragments, mTitleList);
        mViewPager.setAdapter(myOrderAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
