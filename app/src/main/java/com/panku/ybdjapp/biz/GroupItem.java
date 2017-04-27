package com.panku.ybdjapp.biz;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.panku.ybdjapp.adapter.CategoryAdapter;

/**
 * Date：2017/4/10
 * Time: 10:06
 * author: hyn
 * 药品分类中group信息
 */

public class GroupItem extends AbstractExpandableItem<DrugType> implements MultiItemEntity {
    private int icon;
    private String title;
    private String subTitle;
    private String img_url;

    public GroupItem() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return CategoryAdapter.TYPE_LEVEL_0;
    }
}
