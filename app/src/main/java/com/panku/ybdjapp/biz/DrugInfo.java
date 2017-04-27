package com.panku.ybdjapp.biz;

/**
 * Date：2017/4/10
 * Time: 14:29
 * author: hyn
 * 药品信息
 */

public class DrugInfo {
    private int id;
    private int icon;//图片
    private String name;//药名
    private String introduce;//介绍
    private String price;//价格

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
