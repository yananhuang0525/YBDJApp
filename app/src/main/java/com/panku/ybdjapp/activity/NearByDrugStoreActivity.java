package com.panku.ybdjapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.panku.pkBaseLibrary.util.PermissionUtils;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.fragment.ListDrugStoreFragment;
import com.panku.ybdjapp.fragment.MapFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/11
 * Time: 10:42
 * author: hyn
 */
@ContentView(R.layout.ac_nearby_drug_store)
public class NearByDrugStoreActivity extends FragmentActivity {
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.rg)
    private RadioGroup rg;
    //    @ViewInject(R.id.tl)
//    private TabLayout mTabLayout;
//    @ViewInject(R.id.vp)
//    private ViewPager mViewPager;
    private List<Fragment> fragments = new ArrayList<>();//页卡视图集合
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private Fragment mapFragment = new MapFragment();
    private Fragment listFragment = new ListDrugStoreFragment();
    private FragmentManager fragmentManager;
    private static int currSel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        tv_title.setText("附近药店");
        fragmentManager = getSupportFragmentManager();
        fragments.add(mapFragment);
        fragments.add(listFragment);
        initTitleBar();
//        init();
    }

    @Event(value = {R.id.ll_back})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    private void initTitleBar() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_map:
                        currSel = 0;
                        break;
                    case R.id.rb_list:
                        currSel = 1;
                        break;
                }
                addFragmentToStack(currSel);
            }
        });
        addFragmentToStack(0);
    }

    private void addFragmentToStack(int cur) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragments.get(cur);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        }
        for (int i = 0; i < fragments.size(); i++) {
            Fragment f = fragments.get(i);
            if (i == cur && f.isAdded()) {
                fragmentTransaction.show(f);
            } else if (f != null && f.isAdded() && f.isVisible()) {
                fragmentTransaction.hide(f);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    //    private void init() {
//        fragmentManager = getSupportFragmentManager();
//        mapFragment = new MapFragment();
//        listFragment = new ListDrugStoreFragment();
//        fragments.add(mapFragment);
//        fragments.add(listFragment);
//        //添加页卡标题
//        mTitleList.add("地图");
//        mTitleList.add("列表");
//        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
//        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
//        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
//        nearbyDrugStoreAdapter = new MyOrderAdapter(fragmentManager, fragments, mTitleList);
//        mViewPager.setAdapter(nearbyDrugStoreAdapter);
//        mTabLayout.setupWithViewPager(mViewPager);
//    }

}
