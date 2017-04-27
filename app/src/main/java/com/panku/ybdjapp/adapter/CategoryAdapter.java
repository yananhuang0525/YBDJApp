package com.panku.ybdjapp.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.CategoryBasesInfo;
import com.panku.ybdjapp.biz.DrugType;
import com.panku.ybdjapp.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Date：2017/4/10
 * Time: 9:44
 * author: hyn
 * 类目分类适配器
 */

public class CategoryAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public CategoryAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_more_group);
        addItemType(TYPE_LEVEL_1, R.layout.item_more_child);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, MultiItemEntity multiItemEntity) {
        switch (baseViewHolder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final CategoryBasesInfo.ResponseBean.Category item = (CategoryBasesInfo.ResponseBean.Category) multiItemEntity;
                ImageView iv = baseViewHolder.getView(R.id.iv_head);
                ImageLoaderUtil.loadNormalImg(iv, item.getPicture(), R.mipmap.allshare);
                baseViewHolder
                        .setText(R.id.tv_title, item.getName())
                        .setText(R.id.tv_two, item.getGoods_number() + "");
                baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = baseViewHolder.getAdapterPosition();
                        if (item.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final DrugType type = (DrugType) multiItemEntity;
                baseViewHolder.setText(R.id.tv_title, type.getTypeName());
//                baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtils.showToast(type.getTypeName());
//                    }
//                });
                break;
        }
    }
}
