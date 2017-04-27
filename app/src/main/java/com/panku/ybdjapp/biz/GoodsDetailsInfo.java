package com.panku.ybdjapp.biz;

import java.util.List;

/**
 * Date：2017/4/25
 * Time: 16:57
 * author: hyn
 * 商品详情实体类
 */

public class GoodsDetailsInfo {
    private ResponseBean response;

    private String status;

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class ResponseBean {
//                "_links":Object{...},
//                "_href":"/r/v1/goods/83b06724868747d69d1e298f32664c8b",
//                "id":"83b06724868747d69d1e298f32664c8b",
//                "sn":"",
//                "name":"三九胃泰颗粒",
//                "count":0,
//                "weight":15,
//                "keywords":"肠胃",
//                "sort":30,
//                "categorybase":Object{...},
//                "brand":Object{...},
//                "office":Object{...},
//                "specs":Array[3],
//                "stocks":Array[1],
//                "total_store_count":1019,
//                "comment_count":0,
//                "market_price":15,
//                "shop_price":15,
//                "desc":"",
//                "title_img":"/001/userfiles/1/_thumbs/images/shop/goods/2016/12/004-01.png",
//                "on_sale":true,
//                "is_freeshiping":false,
//                "on_time":"1900-01-01 00:00:00",
//                "is_recommend":false,
//                "is_new":false,
//                "is_hot":false,
//                "is_standcatelog":false,
//                "goods_images":Array[3],
//                "appraise_score":0,
//                "is_collect":false,
//                "detail_url":"/f/shop/goods?id=83b06724868747d69d1e298f32664c8b"

        private LinksBean _links;
        private String _href;
        private String id;
        private String sn;
        private String name;
        private int count;
        private int weight;
        private String keywords;
        private int sort;
        private CategoryBaseBean categorybase;
        private BrandBean brand;
        private OfficeBean office;
        private List<Spec> specs;
        private List<Stock> stocks;
        private int total_store_count;
        private int comment_count;
        private int market_price;
        private int shop_price;
        private String desc;
        private String title_img;
        private boolean on_sale;
        private boolean is_freeshiping;
        private String on_time;
        private boolean is_recommend;
        private boolean is_new;
        private boolean is_hot;
        private boolean is_standcatelog;
        private List<GoodsImagesBean> goods_images;
        private int appraise_score;
        private boolean is_collect;
        private String detail_url;

        public LinksBean get_links() {
            return _links;
        }

        public void set_links(LinksBean _links) {
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

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public CategoryBaseBean getCategorybase() {
            return categorybase;
        }

        public void setCategorybase(CategoryBaseBean categorybase) {
            this.categorybase = categorybase;
        }

        public BrandBean getBrand() {
            return brand;
        }

        public void setBrand(BrandBean brand) {
            this.brand = brand;
        }

        public OfficeBean getOffice() {
            return office;
        }

        public void setOffice(OfficeBean office) {
            this.office = office;
        }

        public List<Spec> getSpecs() {
            return specs;
        }

        public void setSpecs(List<Spec> specs) {
            this.specs = specs;
        }

        public List<Stock> getStocks() {
            return stocks;
        }

        public void setStocks(List<Stock> stocks) {
            this.stocks = stocks;
        }

        public int getTotal_store_count() {
            return total_store_count;
        }

        public void setTotal_store_count(int total_store_count) {
            this.total_store_count = total_store_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getMarket_price() {
            return market_price;
        }

        public void setMarket_price(int market_price) {
            this.market_price = market_price;
        }

        public int getShop_price() {
            return shop_price;
        }

        public void setShop_price(int shop_price) {
            this.shop_price = shop_price;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getTitle_img() {
            return title_img;
        }

        public void setTitle_img(String title_img) {
            this.title_img = title_img;
        }

        public boolean isOn_sale() {
            return on_sale;
        }

        public void setOn_sale(boolean on_sale) {
            this.on_sale = on_sale;
        }

        public boolean is_freeshiping() {
            return is_freeshiping;
        }

        public void setIs_freeshiping(boolean is_freeshiping) {
            this.is_freeshiping = is_freeshiping;
        }

        public String getOn_time() {
            return on_time;
        }

        public void setOn_time(String on_time) {
            this.on_time = on_time;
        }

        public boolean is_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(boolean is_recommend) {
            this.is_recommend = is_recommend;
        }

        public boolean is_new() {
            return is_new;
        }

        public void setIs_new(boolean is_new) {
            this.is_new = is_new;
        }

        public boolean is_hot() {
            return is_hot;
        }

        public void setIs_hot(boolean is_hot) {
            this.is_hot = is_hot;
        }

        public boolean is_standcatelog() {
            return is_standcatelog;
        }

        public void setIs_standcatelog(boolean is_standcatelog) {
            this.is_standcatelog = is_standcatelog;
        }

        public List<GoodsImagesBean> getGoods_images() {
            return goods_images;
        }

        public void setGoods_images(List<GoodsImagesBean> goods_images) {
            this.goods_images = goods_images;
        }

        public int getAppraise_score() {
            return appraise_score;
        }

        public void setAppraise_score(int appraise_score) {
            this.appraise_score = appraise_score;
        }

        public boolean is_collect() {
            return is_collect;
        }

        public void setIs_collect(boolean is_collect) {
            this.is_collect = is_collect;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public class LinksBean {
            private SelfBean self;

            public SelfBean getSelf() {
                return self;
            }

            public void setSelf(SelfBean self) {
                this.self = self;
            }

            public class SelfBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }
        }

        public class CategoryBaseBean {
            private String _href;
            private LinksBean _links;
            private String id;
            private String is_hot;
            private int level;
            private String name;
            private String picture;
            private String parent_id;
            private int sort;
            private boolean is_parent;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public boolean is_parent() {
                return is_parent;
            }

            public void setIs_parent(boolean is_parent) {
                this.is_parent = is_parent;
            }

            public String get_href() {
                return _href;
            }

            public void set_href(String _href) {
                this._href = _href;
            }

            public LinksBean get_links() {
                return _links;
            }

            public void set_links(LinksBean _links) {
                this._links = _links;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public class LinksBean {

                private SelfBean self;

                public SelfBean getSelf() {
                    return self;
                }

                public void setSelf(SelfBean self) {
                    this.self = self;
                }

                public class SelfBean {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }
            }
        }

        public class BrandBean {
            private String _href;
            private LinksBean _links;
            private String id;
            private String name;

            public String get_href() {
                return _href;
            }

            public void set_href(String _href) {
                this._href = _href;
            }

            public LinksBean get_links() {
                return _links;
            }

            public void set_links(LinksBean _links) {
                this._links = _links;
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

            public class LinksBean {
                /**
                 * href : /r/v1/brand/2c9c76676d1f4ea18b1df3ae22d2ef2e
                 */

                private SelfBean self;

                public SelfBean getSelf() {
                    return self;
                }

                public void setSelf(SelfBean self) {
                    this.self = self;
                }

                public class SelfBean {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }
            }
        }

        public class OfficeBean {
            private String _href;
            private LinksBean _links;
            private String id;
            private String code;
            private String name;
            private String address;
            private String phone;
            private String lat;
            private String lng;
            private String logo;
            private boolean is_point;
            private String business_start_time;
            private String business_end_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public boolean is_point() {
                return is_point;
            }

            public void setIs_point(boolean is_point) {
                this.is_point = is_point;
            }

            public String getBusiness_start_time() {
                return business_start_time;
            }

            public void setBusiness_start_time(String business_start_time) {
                this.business_start_time = business_start_time;
            }

            public String getBusiness_end_time() {
                return business_end_time;
            }

            public void setBusiness_end_time(String business_end_time) {
                this.business_end_time = business_end_time;
            }

            public String get_href() {
                return _href;
            }

            public void set_href(String _href) {
                this._href = _href;
            }

            public LinksBean get_links() {
                return _links;
            }

            public void set_links(LinksBean _links) {
                this._links = _links;
            }

            public class LinksBean {
                /**
                 * href : /r/v1/office/da056e6f8b194e9d8318659ad93a45e1
                 */

                private SelfBean self;

                public SelfBean getSelf() {
                    return self;
                }

                public void setSelf(SelfBean self) {
                    this.self = self;
                }

                public class SelfBean {
                    private String href;

                    public String getHref() {
                        return href;
                    }

                    public void setHref(String href) {
                        this.href = href;
                    }
                }
            }
        }

        public class Spec {
            //            "id":"4831fe410498475699894793131198e6",
//                    "name":"包装",
//                    "sort":8,
//                    "items":Array[3]
            private String id;
            private String name;
            private int sort;
            private List<Item> items;

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

            public List<Item> getItems() {
                return items;
            }

            public void setItems(List<Item> items) {
                this.items = items;
            }

            public class Item {
                //                "id":"09530ccc78ca4746805b4f37063b5798",
//                        "item":"袋装"
                private String id;
                private String item;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getItem() {
                    return item;
                }

                public void setItem(String item) {
                    this.item = item;
                }
            }
        }

        public class Stock {
            //                     "items":"79a5a6c6f1fe4a24b20d0bcb782c1223_050b34100feb46fa8268f2bc177b3c8d_0f0cf6237be543b38fc7fa7d4549a18c",
//                    "keyname":"米:12g,包装:颗粒,剂量:100g",
//                    "price":56,
//                    "sku":"c50db56ec3e74640bbb6ab1c6a6dba73",
//                    "store_count":999
            private String items;
            private String keyname;
            private int price;
            private String sku;
            private int store_count;

            public String getItems() {
                return items;
            }

            public void setItems(String items) {
                this.items = items;
            }

            public String getKeyname() {
                return keyname;
            }

            public void setKeyname(String keyname) {
                this.keyname = keyname;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public int getStore_count() {
                return store_count;
            }

            public void setStore_count(int store_count) {
                this.store_count = store_count;
            }
        }

        public class GoodsImagesBean {
            private String id;
            private String picUrl;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPicUrl() {
                return picUrl;
            }

            public void setPicUrl(String picUrl) {
                this.picUrl = picUrl;
            }
        }
    }
}
