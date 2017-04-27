package com.panku.ybdjapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.panku.ybdjapp.R;
import com.panku.ybdjapp.fragment.BuyFragment;
import com.panku.ybdjapp.fragment.HomeFragment;
import com.panku.ybdjapp.fragment.DetailedListFragment;
import com.panku.ybdjapp.fragment.MineFragment;
import com.panku.ybdjapp.fragment.ShopFragment;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private static int currSel = 0;
    private RadioGroup group;
    private Fragment homeFragment = new HomeFragment();
    private Fragment shopFragment = new ShopFragment();
    private Fragment buyFragment = new BuyFragment();
    private Fragment listFragment = new DetailedListFragment();
    private Fragment mineFragment = new MineFragment();
    private List<Fragment> fragmentList = Arrays.asList(homeFragment, shopFragment, buyFragment, listFragment, mineFragment);
    private FragmentManager fragmentManager;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        fragmentManager = getSupportFragmentManager();
        initFootBar();
        checkPermission();
    }

    private void initFootBar() {
        group = (RadioGroup) findViewById(R.id.rg);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        currSel = 0;
                        break;
                    case R.id.rb_shop:
                        currSel = 1;
                        break;
                    case R.id.rb_buy:
                        currSel = 2;
                        break;
                    case R.id.rb_list:
                        currSel = 3;
                        break;
                    case R.id.rb_mine:
                        currSel = 4;
                        break;
                }
                addFragmentToStack(currSel);
            }
        });
        addFragmentToStack(0);
    }

    private void addFragmentToStack(int cur) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentList.get(cur);
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        }
        for (int i = 0; i < fragmentList.size(); i++) {
            Fragment f = fragmentList.get(i);
            if (i == cur && f.isAdded()) {
                fragmentTransaction.show(f);
            } else if (f != null && f.isAdded() && f.isVisible()) {
                fragmentTransaction.hide(f);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = fragmentList.get(currSel);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 检查是否有权限
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                Log.i("HYN","权限获取失败");
//                ToastUtils.showToast("权限获取失败");
            } else {
                Log.i("HYN","权限获取成功");
//                Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getActivity(), NearByDrugStoreActivity.class));
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
