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
import com.panku.ybdjapp.fragment.MyOrderAllFragment;
import com.panku.ybdjapp.fragment.MyOrderCollectFragment;
import com.panku.ybdjapp.fragment.MyOrderFinishFragment;
import com.panku.ybdjapp.fragment.MyOrderPaymentFragment;
import com.panku.ybdjapp.fragment.MyOrderSendFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/24
 * Time: 16:29
 * author: hyn
 */
@ContentView(R.layout.ac_my_order)
public class MyOrderActivity extends FragmentActivity {
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tl)
    private TabLayout mTabLayout;
    @ViewInject(R.id.vp)
    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();//页卡视图集合
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private FragmentManager fragmentManager;
    private Fragment allFragment = new MyOrderAllFragment();
    private Fragment paymentFragment = new MyOrderPaymentFragment();
    private Fragment sendFragment = new MyOrderSendFragment();
    private Fragment collectFragment = new MyOrderCollectFragment();
    private Fragment finishFragment = new MyOrderFinishFragment();
    private MyOrderAdapter myOrderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        tv_title.setText("我的订单");
        init();
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        //添加fragment
        fragments.add(allFragment);
        fragments.add(paymentFragment);
        fragments.add(sendFragment);
        fragments.add(collectFragment);
        fragments.add(finishFragment);
        //添加页卡标题
        mTitleList.add("全部");
        mTitleList.add("待付款");
        mTitleList.add("待发货");
        mTitleList.add("待收货");
        mTitleList.add("已完成");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(4)));
        myOrderAdapter = new MyOrderAdapter(fragmentManager, fragments, mTitleList);
        mViewPager.setAdapter(myOrderAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Event(value = {R.id.ll_back})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
