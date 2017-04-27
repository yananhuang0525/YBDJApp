package com.panku.ybdjapp.biz;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.panku.ybdjapp.adapter.CategoryAdapter;

import java.util.List;

/**
 * Date：2017/4/15
 * Time: 16:09
 * author: hyn
 * 类目列表信息
 */

public class CategoryBasesInfo {
    private String status;
    private ResponseBean response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public class ResponseBean {
        private List<Category> categorybases;

        public List<Category> getCategorybases() {
            return categorybases;
        }

        public void setCategorybases(List<Category> categorybases) {
            this.categorybases = categorybases;
        }

        public class Category extends AbstractExpandableItem<DrugType> implements MultiItemEntity {
            //            "_links":{
//                "self":{
//                    "href":"/api/v1/categorybase/6ed680be63914043be3484cd6b6dc31e"
//                }
//            },
//                    "_href":"/api/v1/categorybase/6ed680be63914043be3484cd6b6dc31e",
//                    "id":"6ed680be63914043be3484cd6b6dc31e",
//                    "name":"儿童用药",
//                    "sort":30,
//                    "level":1,
//                    "picture":"",
//                    "is_hot":"0",
//                    "parent_id":"1",
//                    "is_parent":true,
//                    "goods_number":2
            private Links _links;
            private String _href;
            private String id;
            private String name;
            private int sort;
            private int level;
            private String picture;
            private String is_hot;
            private String parent_id;
            private boolean is_parent;
            private int goods_number;

            public Links get_links() {
                return _links;
            }

            public void set_links(Links _links) {
                this._links = _links;
            }

            public String get_href() {
                return _href;
            }

            public void set_href(String _href) {
                this._href = _href;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public boolean is_parent() {
                return is_parent;
            }

            public void setIs_parent(boolean is_parent) {
                this.is_parent = is_parent;
            }

            public int getGoods_number() {
                return goods_number;
            }

            public void setGoods_number(int goods_number) {
                this.goods_number = goods_number;
            }

            @Override
            public int getItemType() {
                return CategoryAdapter.TYPE_LEVEL_0;
            }

            public class Links {
                public Self self;

                public Self getSelf() {
                    return self;
                }

                public void setSelf(Self self) {
                    this.self = self;
                }

                public class Self {
                    public String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }
            }
        }
    }
}
