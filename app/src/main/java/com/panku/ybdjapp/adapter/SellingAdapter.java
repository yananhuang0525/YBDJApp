package com.panku.ybdjapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.DrugInfo;

import java.util.List;

/**
 * Date：2017/4/10
 * Time: 14:24
 * author: hyn
 * 热售适配器
 */

public class SellingAdapter extends BaseQuickAdapter<DrugInfo, BaseViewHolder> {

    public SellingAdapter(int layoutResId, List<DrugInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugInfo item) {
        helper.setImageResource(R.id.iv_icon, item.getIcon())
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_introduce, item.getIntroduce())
                .setText(R.id.tv_price, item.getPrice());
        helper.addOnClickListener(R.id.tv_add);
    }
}
