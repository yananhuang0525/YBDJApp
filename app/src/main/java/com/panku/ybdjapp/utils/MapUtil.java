package com.panku.ybdjapp.utils;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.services.poisearch.PoiSearch;
import com.panku.pkBaseLibrary.base.PanKuApplication;
import com.panku.ybdjapp.R;

/**
 * Date：2017/4/14
 * Time: 13:45
 * author: hyn
 * 地图工具类
 */

public class MapUtil {
    /**
     * 开启定位
     *
     * @param context
     * @param mLocationClient
     * @param mLocationOption
     */
//    public static void startLocation(Context context, AMapLocationClient mLocationClient, AMapLocationClientOption mLocationOption) {
//        mLocationClient = new AMapLocationClient(context);
//        //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
//        mLocationClient.setLocationListener(context);
//        //初始化定位参数
//        mLocationOption = new AMapLocationClientOption();
//        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //设置是否返回地址信息（默认返回地址信息）
//        mLocationOption.setNeedAddress(true);
//        //设置是否只定位一次,默认为false
//        mLocationOption.setOnceLocation(false);
//        //设置是否允许模拟位置,默认为false，不允许模拟位置
//        mLocationOption.setMockEnable(false);
//        //设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(20000);
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        //启动定位
//        mLocationClient.startLocation();
//    }
}
