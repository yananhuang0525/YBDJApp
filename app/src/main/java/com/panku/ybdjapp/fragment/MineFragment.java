package com.panku.ybdjapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.panku.pkBaseLibrary.util.DividerGridItemDecoration;
import com.panku.pkBaseLibrary.util.PermissionUtils;
import com.panku.pkBaseLibrary.util.SharedPreferencesUtil;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.activity.AddressManagerActivity;
import com.panku.ybdjapp.activity.CardManagerActivity;
import com.panku.ybdjapp.activity.LoginActivity;
import com.panku.ybdjapp.activity.MyCollectionActivity;
import com.panku.ybdjapp.activity.MyOrderActivity;
import com.panku.ybdjapp.activity.RegisterActivity;
import com.panku.ybdjapp.activity.SettingActivity;
import com.panku.ybdjapp.adapter.HomeGVAdapter;
import com.panku.ybdjapp.biz.ItemInfo;
import com.panku.ybdjapp.core.UserInfo;
import com.panku.ybdjapp.utils.ImageLoaderUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/5
 * Time: 15:06
 * 我的界面
 */
@ContentView(R.layout.fg_mine)
public class MineFragment extends Fragment implements BaseQuickAdapter.OnItemClickListener {
    @ViewInject(R.id.rv_mine)
    private RecyclerView rv_mine;
    @ViewInject(R.id.iv_user_head)
    private ImageView iv_user_head;//用户头像
    @ViewInject(R.id.tv_user)
    private TextView tv_user;//用户名
    @ViewInject(R.id.ll_btn)
    private LinearLayout ll_btn;//登录、注册布局
    @ViewInject(R.id.tv_login)
    private TextView tv_login;//登录
    @ViewInject(R.id.tv_register)
    private TextView tv_register;//注册

    private List<ItemInfo> list;
    private LinearLayoutManager manger;
    private HomeGVAdapter homeAdapter;
    private UserInfo userInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
//        initGridView();
        Log.i("HYN", "Mine==onCreate");
        initGV();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        userInfo = SharedPreferencesUtil.getMobileLoginInfo(getActivity(), UserInfo.class);
        if (userInfo != null) {
            ll_btn.setVisibility(View.GONE);
            tv_user.setVisibility(View.VISIBLE);
            tv_user.setText(userInfo.getUsername());
            ImageLoaderUtil.loadCircularHeadImg(iv_user_head, userInfo.getHead_pic(), true);
        } else {
            ll_btn.setVisibility(View.VISIBLE);
            tv_user.setVisibility(View.GONE);
            iv_user_head.setImageResource(R.mipmap.ic_default);
        }
    }

    private void initGV() {
        String[] name = {"我的订单", "慢性病购药单", "社保卡管理",
                "收货地址管理", "优惠卷中心", "消息中心", "我的收藏", "基本信息设置",
                "安全认证"};
        int[] icon = {R.mipmap.ic_item01, R.mipmap.ic_item02, R.mipmap.ic_item03,
                R.mipmap.ic_item04, R.mipmap.ic_item05, R.mipmap.ic_item06, R.mipmap.ic_item07,
                R.mipmap.ic_item08, R.mipmap.ic_item09};
        list = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            ItemInfo info = new ItemInfo();
            info.setIcon(icon[i]);
            info.setName(name[i]);
            list.add(info);
        }
        manger = new GridLayoutManager(getActivity(), 3);
        homeAdapter = new HomeGVAdapter(R.layout.item_gridview, list);
        rv_mine.setLayoutManager(manger);
        rv_mine.setAdapter(homeAdapter);
        rv_mine.addItemDecoration(new DividerGridItemDecoration(getActivity()));
        homeAdapter.setOnItemClickListener(this);
    }

    @Event(value = {R.id.tv_login, R.id.tv_register})
    private void onItemClick(View v) {
        Intent intent = new Intent();
        int id = v.getId();
        if (id == R.id.tv_login) {
            intent.setClass(getActivity(), LoginActivity.class);
        } else if (id == R.id.tv_register) {
            intent.setClass(getActivity(), RegisterActivity.class);
        }
        startActivity(intent);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                onItemClick(MyOrderActivity.class);
                break;
            case 1:
                break;
            case 2:
                onItemClick(CardManagerActivity.class);
                break;
            case 3:
                onItemClick(AddressManagerActivity.class);
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                onItemClick(MyCollectionActivity.class);
                break;
            case 7:
                onItemClick(SettingActivity.class);
//                PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE, permissionGrant);
                break;
            case 8:
                break;
        }
    }

    private void onItemClick(Class c) {
        Intent intent = new Intent();
        if (userInfo != null) {
            intent.setClass(getActivity(), c);
        } else {
            intent.setClass(getActivity(), LoginActivity.class);
        }
        startActivity(intent);
    }

    private PermissionUtils.PermissionGrant permissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
//                    Toast.makeText(getActivity(), "Result Permission Grant CODE_WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(getActivity(), requestCode, permissions, grantResults, permissionGrant);
    }
}
