package com.panku.ybdjapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.jude.rollviewpager.RollPagerView;
import com.panku.pkBaseLibrary.util.DividerGridItemDecoration;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.activity.CategoryActivity;
import com.panku.ybdjapp.activity.NearByDrugStoreActivity;
import com.panku.ybdjapp.activity.SearchActivity;
import com.panku.ybdjapp.adapter.HomeGVAdapter;
import com.panku.ybdjapp.adapter.RollAdapter;
import com.panku.ybdjapp.biz.CategoryBasesInfo;
import com.panku.ybdjapp.biz.DrugType;
import com.panku.ybdjapp.biz.ItemInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/5
 * Time: 15:06
 */
public class HomeFragment extends Fragment implements AMapLocationListener, View.OnClickListener {
    private LinearLayout ll_search;//搜索
    private EditText et_search;
    private LinearLayout ll_more;//更多
    private TextView tv_address;//地址
    private RollPagerView rpv;
    private LinearLayout ll_nearby;//附近药店
    private LinearLayout ll_look_more;//搜索
    private RecyclerView rv_home;
    private RollAdapter rollAdapter;

    //    private int[] imgs = {R.mipmap.img01, R.mipmap.img02};
    private String[] imgs = {"", ""};
    private List<ItemInfo> list;
    private LinearLayoutManager manger;
    private HomeGVAdapter homeAdapter;
    private HttpManager httpManager;

    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;

    private CategoryBasesInfo categoryBasesInfo;
    private List<DrugType> list_drugType = new ArrayList<>();
    public static List<MultiItemEntity> categories = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = x.view().inject(this, inflater, container);
        View view = inflater.inflate(R.layout.fg_home, container, false);
        initView(view);
        Log.i("HYN", "Home==onCreate");
        httpManager = new HttpManager();
        initRoll();
        getCategoryList("", "", 1);
        return view;
    }

    private void initView(View view) {
        ll_search = (LinearLayout) view.findViewById(R.id.ll_search);
        ll_more = (LinearLayout) view.findViewById(R.id.ll_more);
        ll_nearby = (LinearLayout) view.findViewById(R.id.ll_nearby);
        ll_look_more = (LinearLayout) view.findViewById(R.id.ll_look_more);
        et_search = (EditText) view.findViewById(R.id.et_search);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        rpv = (RollPagerView) view.findViewById(R.id.rpv);
        rv_home = (RecyclerView) view.findViewById(R.id.rv_home);
        ll_look_more.setOnClickListener(this);
        ll_search.setOnClickListener(this);
        ll_more.setOnClickListener(this);
        ll_nearby.setOnClickListener(this);
    }

    /**
     * 初始化广告轮播
     */
    private void initRoll() {
        rollAdapter = new RollAdapter(rpv, imgs);
        rpv.setAdapter(rollAdapter);
        et_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                et_search.setInputType(InputType.TYPE_NULL); // 关闭软键盘
                return false;
            }
        });

    }

    /**
     * 开始定位
     */
    private void startLocation() {
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
                tv_address.setText(aMapLocation.getAddress());
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
                tv_address.setText("位置获取失败");
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("HYN", "onResume");
        startLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("HYN", "onPause");
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
//        mLocationClient = null;
    }

    /**
     * 获取类目数据
     *
     * @param parent_cid
     * @param office_id
     * @param level
     * @return
     */
    private void getCategoryList(String parent_cid, String office_id, final int level) {

        httpManager.getCategoryBases(parent_cid, office_id, level, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HYN", "getCategoryBases" + result);
                categoryBasesInfo = JSON.parseObject(result, CategoryBasesInfo.class);
                if (categoryBasesInfo.getStatus().equals("ok")) {
                    for (int i = 0; i < categoryBasesInfo.getResponse().getCategorybases().size(); i++) {
                        CategoryBasesInfo.ResponseBean.Category category = categoryBasesInfo.getResponse().getCategorybases().get(i);
                        getCategoryListForChild(category.getId(), null, 2);
                    }
                }
            }

            @Override
            public void onFail(Throwable errorMsg) {
                ToastUtils.showToast("");
            }
        });
    }

    private int num = 1;//计算网络请求的返回结果的次数

    private void getCategoryListForChild(String parent_cid, String office_id, int level) {
        httpManager.getCategoryBases(parent_cid, office_id, level, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                CategoryBasesInfo BasesInfo = JSON.parseObject(result, CategoryBasesInfo.class);
                if (BasesInfo.getStatus().equals("ok")) {
                    for (int i = 0; i < BasesInfo.getResponse().getCategorybases().size(); i++) {
                        CategoryBasesInfo.ResponseBean.Category category = BasesInfo.getResponse().getCategorybases().get(i);
                        DrugType drugType = new DrugType();
                        drugType.setTypeName(category.getName());
                        drugType.setParent_id(category.getParent_id());
                        drugType.setId(category.getId());
                        list_drugType.add(drugType);
                    }
//                    Log.e("HYN", "==" + categoryBasesInfo.getResponse().getCategorybases().size() + ":" + num);
                    if (categoryBasesInfo.getResponse().getCategorybases().size() == num) {
                        handler.sendEmptyMessage(0);
                    }
                    num++;
                }
            }

            @Override
            public void onFail(Throwable errorMsg) {

            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    CategoryBasesInfo.ResponseBean.Category category = null;
                    for (int i = 0; i < categoryBasesInfo.getResponse().getCategorybases().size(); i++) {
                        category = categoryBasesInfo.getResponse().getCategorybases().get(i);
                        for (int j = 0; j < list_drugType.size(); j++) {
                            if (category.getId().equals(list_drugType.get(j).getParent_id())) {
                                category.addSubItem(list_drugType.get(j));
                            }
                        }
                        categories.add(category);
                    }
                    list = new ArrayList<>();
                    for (int i = 0; i < categoryBasesInfo.getResponse().getCategorybases().size(); i++) {
                        ItemInfo info = new ItemInfo();
                        info.setImg_url(categoryBasesInfo.getResponse().getCategorybases().get(i).getPicture());
                        info.setName(categoryBasesInfo.getResponse().getCategorybases().get(i).getName());
                        list.add(info);
                    }
                    manger = new GridLayoutManager(getActivity(), 3);
                    homeAdapter = new HomeGVAdapter(R.layout.item_gridview, list);
                    rv_home.setLayoutManager(manger);
                    rv_home.setAdapter(homeAdapter);
                    rv_home.addItemDecoration(new DividerGridItemDecoration(getActivity()));
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_more:
            case R.id.ll_look_more:
                startActivity(new Intent(getActivity(), CategoryActivity.class));
                break;
            case R.id.et_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.ll_nearby:
                startActivity(new Intent(getActivity(), NearByDrugStoreActivity.class));
                break;
        }
    }
}
