package com.panku.ybdjapp.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.DrugInfo;
import com.panku.ybdjapp.biz.GoodsListInfo;
import com.panku.ybdjapp.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Date：2017/4/10
 * Time: 14:24
 * author: hyn
 * 商品列表适配器
 */

public class GoodsListAdapter extends BaseQuickAdapter<GoodsListInfo.ResponseBean.GoodsBean, BaseViewHolder> {

    public GoodsListAdapter(int layoutResId, List<GoodsListInfo.ResponseBean.GoodsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListInfo.ResponseBean.GoodsBean item) {
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        ImageLoaderUtil.loadNormalImg(iv_icon, item.getTitle_img(), R.mipmap.tu06);
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_introduce, item.getDesc())
                .setText(R.id.tv_price, "￥" + item.getShop_price());
        helper.addOnClickListener(R.id.tv_add);
    }
}
