package com.panku.ybdjapp.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.SupportMapFragment;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.services.poisearch.PoiSearch;
import com.panku.pkBaseLibrary.base.PanKuApplication;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.DrugStoreInfo;
import com.panku.ybdjapp.biz.NearbyShopInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/11
 * Time: 13:41
 * author: hyn
 */
@ContentView(R.layout.fg_nearby_map)
public class MapFragment extends SupportMapFragment implements LocationSource, AMapLocationListener, AMap.OnMarkerClickListener {
    @ViewInject(R.id.mv)
    private MapView mv;
    private AMap amap;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private PoiSearch poiSearch;
    private PoiSearch.Query query;

    private List<DrugStoreInfo> list_drug = new ArrayList<>();
    private DrugStoreInfo drugStoreInfo = null;
    private MyLocationStyle myLocationStyle;
    private HttpManager manager;
    public static NearbyShopInfo nearbyShopInfo;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = x.view().inject(this, layoutInflater, viewGroup);
        mv.onCreate(bundle);
        amap = mv.getMap();
        //设置缩放级别（缩放级别为4-20级）
        amap.moveCamera(CameraUpdateFactory.zoomTo(12));
        setUpMap();
        return view;
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
        amap.setOnMarkerClickListener(this);
//        amap.setInfoWindowAdapter(this);
//
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
        mLocationOption.setInterval(60000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

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

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == AMapLocation.LOCATION_SUCCESS) {
                mListener.onLocationChanged(aMapLocation);
                Log.i("HYN", aMapLocation.getCity() + ":" + aMapLocation.getAddress());
                getNearbyShop(Double.toString(aMapLocation.getLatitude()), Double.toString(aMapLocation.getLongitude()));
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

//    @Override
//    public void onPoiSearched(PoiResult poiResult, int i) {
//        if (i == 1000 && poiResult != null) {
//            ArrayList<PoiItem> p = poiResult.getPois();
//            for (int j = 0; j < p.size(); j++) {
//                drugStoreInfo = new DrugStoreInfo();
//                drugStoreInfo.setAddress(p.get(j).getSnippet());
//                drugStoreInfo.setDrugName(p.get(j).getTitle());
//                drugStoreInfo.setPhone(p.get(j).getTel());
//                drugStoreInfo.setDistance(p.get(j).getDistance());
//                list_drug.add(drugStoreInfo);
//            }
//            if (poiResult.getQuery() != null) {// 搜索poi的结果
//                if (poiResult.getQuery().equals(query)) {
//                    amap.clear();// 清理之前的图标
//                    PoiOverlay poiOverlay = new PoiOverlay(amap, poiResult.getPois());
//                    poiOverlay.removeFromMap();
//                    poiOverlay.addToMap();
//                    poiOverlay.zoomToSpan();
//                }
//            } else {
//                ToastUtils.showToast("没有相关信息");
//            }
//        } else {
//
//        }
//    }

//    @Override
//    public void onPoiItemSearched(PoiItem poiItem, int i) {
//
//    }

    @Override
    public boolean onMarkerClick(Marker marker) {
//        marker.showInfoWindow();
//        ToastUtils.showToast(marker.getTitle());
        return false;
    }

    public void getNearbyShop(String lat, String lng) {
        manager = new HttpManager();
        manager.offices(null, null, lat, lng, 50000, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                setMap(result);
            }

            @Override
            public void onFail(Throwable errorMsg) {
                ToastUtils.showToast("网络连接失败");
            }
        });
    }

    /**
     * 在地图上设置market点
     */
    public void setMarker(NearbyShopInfo.ResponseBean.Office office) {
//        amap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(office.getLat()), Double.parseDouble(office.getLng())), 15));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(Double.parseDouble(office.getLat()), Double.parseDouble(office.getLng())));
        markerOptions.title(office.getName());
        markerOptions.visible(true);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(PanKuApplication.context().getResources(), R.mipmap.ic_location));
        markerOptions.icon(bitmapDescriptor);
        amap.addMarker(markerOptions);
    }

    private void setMap(String result) {
        nearbyShopInfo = JSON.parseObject(result, NearbyShopInfo.class);
        if (nearbyShopInfo.getStatus().equals("ok")) {
            for (int i = 0; i < nearbyShopInfo.getResponse().getOfficeList().size(); i++) {
                NearbyShopInfo.ResponseBean.Office office = nearbyShopInfo.getResponse().getOfficeList().get(i);
                setMarker(office);
            }
        } else {
            Log.e("HYN", "附近商店搜索失败:" + result);
        }
    }
}
