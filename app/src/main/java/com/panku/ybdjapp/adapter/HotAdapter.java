package com.panku.ybdjapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.panku.ybdjapp.R;

import java.util.List;

/**
 * Date：2017/4/10
 * Time: 14:24
 * author: hyn
 * 热搜适配器
 */

public class HotAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public HotAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_hot_name, item);
    }
}
