package com.panku.ybdjapp.biz;

/**
 * @author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/6
 * Time: 11:19
 * 我的界面中GridView item信息
 */
public class ItemInfo {
    private int icon;//图标
    private String img_url;//图片地址
    private String name;//名称

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
