package com.panku.ybdjapp.biz;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.panku.ybdjapp.adapter.CategoryAdapter;

/**
 * Date：2017/4/10
 * Time: 10:10
 * author: hyn
 * 药品分类child信息
 */

public class DrugType implements MultiItemEntity {
    private String typeName;
    private  String parent_id;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public DrugType() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public int getItemType() {
        return CategoryAdapter.TYPE_LEVEL_1;
    }
}
