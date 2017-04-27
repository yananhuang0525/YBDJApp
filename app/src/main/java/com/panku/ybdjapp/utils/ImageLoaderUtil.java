package com.panku.ybdjapp.utils;

import android.util.Log;
import android.widget.ImageView;

import com.panku.pkBaseLibrary.http.HttpHelp;
import com.panku.ybdjapp.core.Constant;

/**
 * Date：2017/4/15
 * Time: 16:46
 * author: hyn
 * 加载网络图片工具类
 */

public class ImageLoaderUtil {
    /**
     * 加载正常图片
     *
     * @param iv
     * @param url
     * @param failImg 加载失败显示的图片
     */
    public static void loadNormalImg(ImageView iv, String url, int failImg) {
//        Log.e("HYN",""+Constant.BASE_URL + url);
        HttpHelp.getInstance().bindCommonImage(iv, Constant.BASE_URL + url, failImg, true);
//        HttpHelp.getInstance().bindCommonImage(iv,  url, failImg, true);
    }

    /**
     * 加载圆形图片
     *
     * @param iv
     * @param url
     * @param option
     */
    public static void loadImg(ImageView iv, String url, boolean option) {
        HttpHelp.getInstance().bindCircularImage(iv, Constant.BASE_URL + url, option);
    }

    public static void loadCircularHeadImg(ImageView iv, String url, boolean option) {
        HttpHelp.getInstance().bindCircularImageHead(iv, Constant.BASE_URL + url, option);
    }
}
