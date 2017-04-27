package com.panku.ybdjapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Date：2017/4/7
 * Time: 16:36
 * author: hyn
 * 首页广告轮播适配器 循环播放
 */

public class RollAdapter extends LoopPagerAdapter {
    private Context context;
    //        private int[] images;
//    private List<String> list;
    private String[] img_url;

    public RollAdapter(RollPagerView viewPager, String[] img_url) {
        super(viewPager);
        this.img_url = img_url;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        ImageLoaderUtil.loadNormalImg(view, img_url[position], R.mipmap.allshare);
//        view.setImageResource(list.get(position));
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getRealCount() {
        return img_url.length;
    }

//    public RollAdapter(Context context, int[] imageViews) {
//        this.context = context;
//        this.images = imageViews;
//    }
//
//    @Override
//    public View getView(ViewGroup container, int position) {
//        ImageView view = new ImageView(container.getContext());
//        view.setImageResource(images[position]);
//        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        return view;
//    }
//
//    @Override
//    public int getCount() {
//        return images.length;
//    }
}
