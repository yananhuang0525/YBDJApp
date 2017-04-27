package com.panku.ybdjapp.biz;


import java.io.Serializable;

/**
 * Date：2017/4/11
 * Time: 14:46
 * author: hyn
 * 药店信息
 */
public class DrugStoreInfo implements Serializable {
    private String Address;//地址
    private String Phone;//电话
    private String DrugName;//药店名
    private int Distance;//距离

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }

    public int getDistance() {
        return Distance;
    }

    public String getDrugName() {
        return DrugName;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setDrugName(String drugName) {
        DrugName = drugName;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }
}
