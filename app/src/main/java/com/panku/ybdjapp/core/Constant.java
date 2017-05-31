package com.panku.ybdjapp.core;

/**
 * Date：2017/4/7
 * Time: 11:58
 * author: hyn
 */

public class Constant {
    //        private static String BASE_URL = "http://192.168.1.182/001/f/v1/";
//    private static String BASE_URL = "http://116.255.137.170:8080/001/f/v1";
//    public static UserInfo UserInfo = null;//用户信息
//    public static String BASE_URL = "http://192.168.1.182:8082/001";
//    public static String BASE_URL = "http://yibaodaojia.com:8036";
    public static String BASE_URL = "http://yibaodaojia.com:8082";
    public static String PATH_LOGIN = BASE_URL + "/f/v1/customers/authentication";//登录接口
    public static String PATH_REGISTER = BASE_URL + "/f/v1/customers/regist";//注册接口
    public static String getCategoryBases = BASE_URL + "/f/v1/categorybases";//获取类目列表
    public static String offices = BASE_URL + "/f/v1/offices";//附近商铺
    public static String update_profile = BASE_URL + "/f/v1/customers/update_profile";//修改基本信息
    public static String bind = BASE_URL + "/f/v1/customers/insurance/bind";//社保卡绑定
    public static String query = BASE_URL + "/f/v1/customers/insurance/query";//社保卡信息查询
    public static String unbind = BASE_URL + "/f/v1/customers/insurance/unbind";//解除社保卡绑定
    public static String get_code = BASE_URL + "/f/v1/shortmessages/";//获取验证码
    public static String change_pwd = BASE_URL + "/f/v1/customers/change_pwd";//修改密码
    public static String addresses = BASE_URL + "/f/v1/addresses";//收货地址列表
    public static String address_create = BASE_URL + "/f/v1/addresses/create";//添加收货地址
    public static String remove = BASE_URL + "/f/v1/addresses/remove";//删除收货地址
    public static String update = BASE_URL + "/f/v1/addresses/update";//修改收货地址
    public static String orders = BASE_URL + "/f/v1/orders";//订单列表
    public static String goods = BASE_URL + "/f/v1/goods";//获取商品列表
    public static String cars_create = BASE_URL + "/f/v1/carts/create";//添加商品到购物车
    public static String cars = BASE_URL + "/f/v1/carts";//购物车列表
    public static String cars_remove = BASE_URL + "/f/v1/carts/remove";//删除购物车商品
    public static String change_num = BASE_URL + "/f/v1/carts/change_num";//修改购物车商品数量
    public static String cars_clean = BASE_URL + "/f/v1/carts/clean";//清空购物车

}
