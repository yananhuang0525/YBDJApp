package com.panku.ybdjapp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.DrugStoreInfo;

import java.util.List;

/**
 * Date：2017/4/15
 * Time: 9:21
 * author: hyn
 * 搜索出来的商店列表适配器
 */

public class ListDrugShopAdapter extends BaseQuickAdapter<DrugStoreInfo, BaseViewHolder> {
    public ListDrugShopAdapter(int layoutResId, List<DrugStoreInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugStoreInfo item) {
        helper.setText(R.id.tv_name, item.getDrugName())
                .setText(R.id.tv_address, item.getAddress())
                .setText(R.id.tv_distance, item.getDistance() + "米");
    }
}
