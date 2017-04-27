package com.panku.ybdjapp.adapter;

import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.AddressInfo;

import java.util.List;

/**
 * Date：2017/4/21
 * Time: 11:28
 * author: hyn
 * 收货地址管理适配器
 */

public class AddressManagerAdapter extends BaseQuickAdapter<AddressInfo.ResponseBean.Address, BaseViewHolder> {
    public AddressManagerAdapter(int layoutResId, List<AddressInfo.ResponseBean.Address> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressInfo.ResponseBean.Address item) {
        CheckBox checkBox = helper.getView(R.id.cb_default);
        helper.setText(R.id.tv_name, item.getConsignee())
                .setText(R.id.tv_phone, item.getPhone())
                .setText(R.id.tv_address, item.getAddress())
                .setChecked(R.id.cb_default, item.is_default());
        if (item.is_default()) {
            checkBox.setText("默认地址");
        } else {
            checkBox.setText("设为默认地址");
        }
        helper.addOnClickListener(R.id.ll_address);
        helper.addOnClickListener(R.id.tv_change);
        helper.addOnClickListener(R.id.tv_delete);
        helper.addOnClickListener(R.id.cb_default);
    }
}
