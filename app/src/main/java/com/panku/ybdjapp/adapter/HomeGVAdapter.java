package com.panku.ybdjapp.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.ItemInfo;
import com.panku.ybdjapp.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Date：2017/4/11
 * Time: 8:59
 * author: hyn
 * 首页GridView适配器
 */

public class HomeGVAdapter extends BaseQuickAdapter<ItemInfo, BaseViewHolder> {
    public HomeGVAdapter(int layoutResId, List<ItemInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemInfo item) {

//        Log.i("HYN", "首页图片地址：" + item.getImg_url());
        if (item.getImg_url() != null && !item.getImg_url().equals("")) {
            ImageView iv = helper.getView(R.id.iv_item);
            ImageLoaderUtil.loadNormalImg(iv, item.getImg_url(), R.mipmap.allshare);
        } else {
            helper.setImageResource(R.id.iv_item, item.getIcon());
        }
        helper.setText(R.id.tv_item, item.getName());
    }
}
