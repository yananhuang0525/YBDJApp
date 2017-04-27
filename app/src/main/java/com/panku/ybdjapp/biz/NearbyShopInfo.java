package com.panku.ybdjapp.biz;

import java.util.List;

/**
 * Date：2017/4/14
 * Time: 16:02
 * author: hyn
 * 搜索出来的附近的商店信息
 */

public class NearbyShopInfo {
//    {
//        "status":"ok",
//            "response":{
//        "_pagination":{
//            "pageNo":1,
//                    "returnedItems":4,
//                    "pageSize":15,
//                    "totalItems":-1,
//                    "urlTemplate":"$rest_path$/offices?page_no=$page_no$&page_size=$page_size$&lat=$lat$&lng=$lng$&range=$range$",
//                    "urlArguments":{
//                "lng":"1",
//                        "lat":"1",
//                        "rest_path":"/api"
//            }
//        },
//        "_links":{
//            "self":{
//                "href":"/api/offices?page_no=1&page_size=15&lat=1&lng=1&range="
//            },
//            "previousPage":{
//                "href":"/api/offices?page_no=15&page_size=&lat=1&lng=1&range="
//            }
//        },
//        "_href":"/api/offices?page_no=1&page_size=15&lat=1&lng=1&range=",
//                "officeList":[
//        {
//            "_links":{
//            "self":{
//                "href":"/api/v1/offices/1"
//            }
//        },
//            "_href":"/api/v1/offices/1",
//                "id":"1",
//                "code":"10000",
//                "name":"医保平台",
//                "address":"",
//                "phone":"",
//                "lat":"34.77845",
//                "lng":"113.56444",
//                "logo":"",
//                "distance":11996,
//                "is_point":false,
//                "business_start_time":"7:00",
//                "business_end_time":"21:00"
//        },
//        {
//            "_links":{
//            "self":{
//                "href":"/api/v1/offices/b0a42b5fa6574405b9daf3815e57c365"
//            }
//        },
//            "_href":"/api/v1/offices/b0a42b5fa6574405b9daf3815e57c365",
//                "id":"b0a42b5fa6574405b9daf3815e57c365",
//                "code":"20000",
//                "name":"汤臣倍健",
//                "address":"",
//                "phone":"",
//                "lat":"34.737946",
//                "lng":"113.606017",
//                "logo":"",
//                "distance":12001,
//                "is_point":false,
//                "business_start_time":"7:00",
//                "business_end_time":"21:00"
//        },
//        {
//            "_links":{
//            "self":{
//                "href":"/api/v1/offices/8e492f268bdc4ae1936596d09714fbd9"
//            }
//        },
//            "_href":"/api/v1/offices/8e492f268bdc4ae1936596d09714fbd9",
//                "id":"8e492f268bdc4ae1936596d09714fbd9",
//                "code":"100000",
//                "name":"大参林",
//                "address":"",
//                "phone":"15039636562",
//                "lat":"34.793366",
//                "lng":"113.666442",
//                "logo":"",
//                "distance":12005,
//                "is_point":false,
//                "business_start_time":"7:00",
//                "business_end_time":"21:00"
//        },
//        {
//            "_links":{
//            "self":{
//                "href":"/api/v1/offices/da056e6f8b194e9d8318659ad93a45e1"
//            }
//        },
//            "_href":"/api/v1/offices/da056e6f8b194e9d8318659ad93a45e1",
//                "id":"da056e6f8b194e9d8318659ad93a45e1",
//                "code":"10056",
//                "name":"盘古公司",
//                "address":"",
//                "phone":"",
//                "lat":"34.11555",
//                "lng":"113.56235",
//                "logo":"",
//                "distance":12014,
//                "is_point":false,
//                "business_start_time":"7:00",
//                "business_end_time":"21:00"
//        }
//        ]
//    }
//    }

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
        private String _href;
        private LinksBean _links;
        private Pagination _pagination;
        public List<Office> officeList;

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

        public Pagination get_pagination() {
            return _pagination;
        }

        public void set_pagination(Pagination _pagination) {
            this._pagination = _pagination;
        }

        public List<Office> getOfficeList() {
            return officeList;
        }

        public void setOfficeList(List<Office> officeList) {
            this.officeList = officeList;
        }

        public class LinksBean {
//            "_links":{
//                  "self":{
//                      "href":"/api/offices?page_no=1&page_size=15&lat=1&lng=1&range="
//                      },
//                  "previousPage":{
//                      "href":"/api/offices?page_no=15&page_size=&lat=1&lng=1&range="
//                      }
//                  }

            private PreviousPageBean previousPage;

            private SelfBean self;

            public PreviousPageBean getPreviousPage() {
                return previousPage;
            }

            public void setPreviousPage(PreviousPageBean previousPage) {
                this.previousPage = previousPage;
            }

            public SelfBean getSelf() {
                return self;
            }

            public void setSelf(SelfBean self) {
                this.self = self;
            }

            public class PreviousPageBean {
                private String href;

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
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

        public class Pagination {
            //            "_pagination":{
//                     "pageNo":1,
//                    "returnedItems":4,
//                    "pageSize":15,
//                    "totalItems":-1,
//                    "urlTemplate":"$rest_path$/offices?page_no=$page_no$&page_size=$page_size$&lat=$lat$&lng=$lng$&range=$range$",
//                    "urlArguments":{
//                              "lng":"1",
//                              "lat":"1",
//                              "rest_path":"/api"
//                              }
//        }
            public int pageNo;
            public int returnedItems;
            public int pageSize;
            public int totalItems;
            public String urlTemplate;
            public UrlArguments urlArguments;

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getReturnedItems() {
                return returnedItems;
            }

            public void setReturnedItems(int returnedItems) {
                this.returnedItems = returnedItems;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalItems() {
                return totalItems;
            }

            public void setTotalItems(int totalItems) {
                this.totalItems = totalItems;
            }

            public String getUrlTemplate() {
                return urlTemplate;
            }

            public void setUrlTemplate(String urlTemplate) {
                this.urlTemplate = urlTemplate;
            }

            public UrlArguments getUrlArguments() {
                return urlArguments;
            }

            public void setUrlArguments(UrlArguments urlArguments) {
                this.urlArguments = urlArguments;
            }

            public class UrlArguments {
                public String lng;
                public String lat;
                public String rest_path;

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getRest_path() {
                    return rest_path;
                }

                public void setRest_path(String rest_path) {
                    this.rest_path = rest_path;
                }
            }
        }

        public class Office {
            // {
//                "_links":{
//                      "self":{
//                          "href":"/api/v1/offices/1"
//                            }
//                        },
//                "_href":"/api/v1/offices/1",
//                 "id":"1",
//                 "code":"10000",
//                 "name":"医保平台",
//                 "address":"",
//                 "phone":"",
//                 "lat":"34.77845",
//                 "lng":"113.56444",
//                 "logo":"",
//                 "distance":11996,
//                 "is_point":false,
//                 "business_start_time":"7:00",
//                 "business_end_time":"21:00"
//            }
            public Links _links;
            public String _href;
            public String id;
            public String code;
            public String name;
            public String address;
            public String phone;
            public String lat;
            public String lng;
            public String logo;
            public int distance;
            public boolean is_point;
            public String business_start_time;
            public String business_end_time;

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

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
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
