package com.panku.ybdjapp.biz;

/**
 * Date：2017/4/20
 * Time: 14:54
 * author: hyn
 * 社保卡信息
 */

public class SBCardInfo {
    //    {
//        "status": "ok",
//            "response": {
//                 "status": "1",
//                "name": "陆伟",
//                "insurance_card": "A8236635",
//                "id_card": "41148119840711483X"
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
        private String status;
        private String name;//姓名
        private String insurance_card;//社保卡卡号
        private String id_card;//身份证号

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInsurance_card() {
            return insurance_card;
        }

        public void setInsurance_card(String insurance_card) {
            this.insurance_card = insurance_card;
        }

        public String getId_card() {
            return id_card;
        }

        public void setId_card(String id_card) {
            this.id_card = id_card;
        }
    }
}
