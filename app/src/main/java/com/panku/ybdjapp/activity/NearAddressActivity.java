package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.ListDrugShopAdapter;
import com.panku.ybdjapp.biz.DrugStoreInfo;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/24
 * Time: 10:34
 * author: hyn
 */
@ContentView(R.layout.ac_near_address)
public class NearAddressActivity extends Activity implements LocationSource, AMapLocationListener, PoiSearch.OnPoiSearchListener, BaseQuickAdapter.OnItemClickListener {
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;//返回按钮
    @ViewInject(R.id.tv_title)
    private TextView tv_title;//标题
    @ViewInject(R.id.mv)
    private MapView mv;
    @ViewInject(R.id.rv_near)
    private RecyclerView rv_near;
    private AMap amap;
    private LocationSource.OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private MyLocationStyle myLocationStyle;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        tv_title.setText("附近");
        mv.onCreate(savedInstanceState);
        amap = mv.getMap();
        //设置缩放级别（缩放级别为4-20级）
        amap.moveCamera(CameraUpdateFactory.zoomTo(15));
        setUpMap();

    }

    @Event(value = {R.id.ll_back})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    private void setUpMap() {
        // 自定义系统定位小蓝点
        myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.ic_location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        amap.setMyLocationStyle(myLocationStyle);
        amap.setLocationSource(this);// 设置定位监听
        amap.getUiSettings().setZoomGesturesEnabled(true);
        amap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        amap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
//        amap.setOnMarkerClickListener(this);
//        amap.setInfoWindowAdapter(this);
//
        mLocationClient = new AMapLocationClient(this);
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
        mLocationOption.setInterval(60000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }

    /**
     * 搜索操作
     */
    private void doSearchQuery(String cityCode, double longitude, double latitude, int Distant) {// 在地图上绘制药店的mark点
        query = new PoiSearch.Query("附近", "", cityCode);
        poiSearch = new PoiSearch(this, query);
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude,
                longitude), Distant));//设置周边搜索的中心点以及半径
        poiSearch.setOnPoiSearchListener(this);
        query.setPageSize(40);
        query.setPageNum(0);
        poiSearch.searchPOIAsyn();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mv.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mv.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mv.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mv.onDestroy();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
                mListener.onLocationChanged(aMapLocation);
                Log.i("HYN", aMapLocation.getCity() + ":" + aMapLocation.getAddress());
                doSearchQuery(aMapLocation.getCityCode(), aMapLocation.getLongitude(), aMapLocation.getLatitude(), 1500);
//                getNearbyShop(Double.toString(aMapLocation.getLatitude()), Double.toString(aMapLocation.getLongitude()));
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    private List<DrugStoreInfo> list_drug = new ArrayList<>();
    private DrugStoreInfo drugStoreInfo = null;
    private ListDrugShopAdapter listDrugShopAdapter;

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == 1000 && poiResult != null) {
            ArrayList<PoiItem> p = poiResult.getPois();
            for (int j = 0; j < p.size(); j++) {
                drugStoreInfo = new DrugStoreInfo();
                drugStoreInfo.setAddress(p.get(j).getSnippet());
                drugStoreInfo.setDrugName(p.get(j).getTitle());
                drugStoreInfo.setPhone(p.get(j).getTel());
                drugStoreInfo.setDistance(p.get(j).getDistance());
                list_drug.add(drugStoreInfo);
            }
            listDrugShopAdapter = new ListDrugShopAdapter(R.layout.item_list_shop, list_drug);
            rv_near.setLayoutManager(new LinearLayoutManager(NearAddressActivity.this));
            rv_near.setAdapter(listDrugShopAdapter);
            listDrugShopAdapter.setOnItemClickListener(this);
//            if (poiResult.getQuery() != null) {// 搜索poi的结果
//                if (poiResult.getQuery().equals(query)) {
////                    amap.clear();// 清理之前的图标
//                    PoiOverlay poiOverlay = new PoiOverlay(amap, poiResult.getPois());
//                    poiOverlay.removeFromMap();
//                    poiOverlay.addToMap();
//                    poiOverlay.zoomToSpan();
//                }
//            } else {
//                ToastUtils.showToast("没有相关信息");
//            }
        } else {

        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DrugStoreInfo drugStoreInfo = (DrugStoreInfo) adapter.getItem(position);
        Intent intent = new Intent();
        intent.putExtra("Address", drugStoreInfo.getDrugName());
        setResult(RESULT_OK, intent);
        finish();
    }
}
