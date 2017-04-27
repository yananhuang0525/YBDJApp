package com.panku.ybdjapp.biz;

import java.util.List;


/**
 * Date：2017/4/25
 * Time: 11:40
 * author: hyn
 * 商品列表的实体类
 */

public class GoodsListInfo {

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

    public static class ResponseBean {
        private List<GoodsBean> goods;

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            //                     "id":"83b06724868747d69d1e298f32664c8b",
//                    "sn":"",
//                    "name":"三九胃泰颗粒",
//                    "count":0,
//                    "weight":0,
//                    "keywords":"肠胃",
//                    "sort":30,
//                    "brand":Object{...},
//                    "office":Object{...},
//                    "total_store_count":0,
//                    "comment_count":0,
//                    "market_price":15,
//                    "shop_price":15,
//                    "desc":"",
//                    "title_img":"/001/userfiles/1/_thumbs/images/shop/goods/2016/12/004-01.png",
//                    "on_sale":true,
//                    "is_freeshiping":false,
//                    "on_time":"1900-01-01 00:00:00",
//                    "is_recommend":false,
//                    "is_new":false,
//                    "is_hot":false,
//                    "is_standcatelog":false,
//                    "is_collect":false
            private String id;
            private String sn;
            private String name;
            private int count;
            private int weight;
            private String keywords;
            private int sort;
            private BrandBean brand;
            private OfficeBean office;
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
            private boolean is_collect;

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

            public boolean is_collect() {
                return is_collect;
            }

            public void setIs_collect(boolean is_collect) {
                this.is_collect = is_collect;
            }

            public static class BrandBean {
                private String _href;
                private LinksBean _links;
                private String id;
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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

                public static class LinksBean {

                    private SelfBean self;

                    public SelfBean getSelf() {
                        return self;
                    }

                    public void setSelf(SelfBean self) {
                        this.self = self;
                    }

                    public static class SelfBean {
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

            public static class OfficeBean {
                //                         "_links":Object{...},
//                        "_href":"/r/v1/offices/da056e6f8b194e9d8318659ad93a45e1",
//                        "id":"da056e6f8b194e9d8318659ad93a45e1",
//                        "code":"",
//                        "name":"盘古公司",
//                        "address":"",
//                        "phone":"",
//                        "lat":"",
//                        "lng":"",
//                        "logo":"",
//                        "is_point":false,
//                        "business_start_time":"7:00",
//                        "business_end_time":"21:00"
                private LinksBean _links;
                private String _href;
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

                public static class LinksBean {
                    private SelfBean self;

                    public SelfBean getSelf() {
                        return self;
                    }

                    public void setSelf(SelfBean self) {
                        this.self = self;
                    }

                    public static class SelfBean {
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
        }
    }
}
