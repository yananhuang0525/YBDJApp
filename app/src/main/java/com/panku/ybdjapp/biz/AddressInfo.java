package com.panku.ybdjapp.biz;

import java.util.List;

/**
 * Date：2017/4/21
 * Time: 11:27
 * author: hyn
 * 收货地址信息
 */

public class AddressInfo {
    //    {
//        "status":"ok",
//            "response":{
//        "addresses":[
//        {
//            "_links":{
//            "self":{
//                "href":"/api/v1/useraddresses/4550c6b6b579459b8c246e9bd1e4255d"
//            }
//        },
//            "_href":"/api/v1/useraddresses/4550c6b6b579459b8c246e9bd1e4255d",
//                "id":"4550c6b6b579459b8c246e9bd1e4255d",
//                "consignee":"黄亚南",
//                "address":"郑州市金水区",
//                "phone":"15093651251",
//                "lat":"0",
//                "lng":"0",
//                "is_default":true
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
        List<Address> addresses;

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        public class Address {
            private LinksBean _links;
            private String _href;
            private String id;
            private String consignee;
            private String address;
            private String phone;
            private String lat;
            private String lng;
            private boolean is_default;

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

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
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

            public boolean is_default() {
                return is_default;
            }

            public void setIs_default(boolean is_default) {
                this.is_default = is_default;
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

    }
}
