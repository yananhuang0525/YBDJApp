package com.panku.ybdjapp.core;

/**
 * Date：2017/4/19
 * Time: 16:30
 * author: hyn
 * 用户信息
 */

public class UserInfo {
    //    {
//        "status":"ok",
//            "response":{
//                 "id":"aff27e7fe93f4e1aaf4f4a92246d635e",
//                "username":"150****1251",
//                "sex":"1",
//                "birthday":"1900-01-01",
//                "code":"10080",
//                "reg_time":"2017-04-19 16:22:48",
//                "last_login":"1900-01-01 00:00:00",
//                "last_ip":"",
//                "head_pic":"",
//                "is_bind_insurance":false,
//                "is_bind_wechat":false,
//                "is_bind_face":false
//    }
//    }
//    private String status;
//    private ResponseBean response;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public ResponseBean getResponse() {
//        return response;
//    }
//
//    public void setResponse(ResponseBean response) {
//        this.response = response;
//    }

    //    public class ResponseBean {
    private String id;
    private String username;//用户名
    private String sex;//性别
    private String birthday;//生日
    private String code;//
    private String reg_time;//注册时间
    private String last_login;//最后登录时间
    private String last_ip;//最后登录IP
    private String head_pic;//头像
    private boolean is_bind_insurance;
    private boolean is_bind_wechat;
    private boolean is_bind_face;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getLast_ip() {
        return last_ip;
    }

    public void setLast_ip(String last_ip) {
        this.last_ip = last_ip;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public boolean is_bind_insurance() {
        return is_bind_insurance;
    }

    public void setIs_bind_insurance(boolean is_bind_insurance) {
        this.is_bind_insurance = is_bind_insurance;
    }

    public boolean is_bind_wechat() {
        return is_bind_wechat;
    }

    public void setIs_bind_wechat(boolean is_bind_wechat) {
        this.is_bind_wechat = is_bind_wechat;
    }

    public boolean is_bind_face() {
        return is_bind_face;
    }

    public void setIs_bind_face(boolean is_bind_face) {
        this.is_bind_face = is_bind_face;
    }
//    }
}
