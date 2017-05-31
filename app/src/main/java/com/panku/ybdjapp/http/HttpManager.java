package com.panku.ybdjapp.http;

import android.app.Dialog;

import com.panku.ybdjapp.core.Constant;
import com.panku.ybdjapp.http.Interface.HttpCallBack;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * Date：2017/4/7
 * Time: 11:49
 * author: hyn
 * 网络请求
 */

public class HttpManager {
    /**
     * 登录
     *
     * @param dialog
     * @param userName
     * @param password
     * @param callBack
     */
    public void login(Dialog dialog, String userName, String password, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", userName);
        params.put("password", password);
        OkHttpHelp.postAsync(dialog, Constant.PATH_LOGIN, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().post(dialog, Constant.PATH_LOGIN, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 注册
     *
     * @param dialog
     * @param userName
     * @param password
     * @param callBack
     */
    public void register(Dialog dialog, String userName, String password, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", userName);
        params.put("password", password);
        OkHttpHelp.postAsync(dialog, Constant.PATH_REGISTER, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().post(dialog, Constant.PATH_REGISTER, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 获取类目列表
     * 根据父级类目、商家、等级等获取商品类目列表
     *
     * @param parent_cid 父类目 id, 为空时，获取所有
     * @param office_id  商家 ID
     * @param level      类目等级（1,2,3 级）
     * @param callBack
     */
    public void getCategoryBases(String parent_cid, String office_id, int level, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("parent_cid", parent_cid);
        params.put("office_id", office_id);
        params.put("level", String.valueOf(level));
        OkHttpHelp.getAsync(null, Constant.getCategoryBases, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(Constant.getCategoryBases, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 附近店铺（商家）
     *
     * @param customer_id 用户 id
     * @param name        商家名称
     * @param lat         收货地址的经度
     * @param lng         收货地址的纬度
     * @param range       范围
     * @param callBack
     */
    public void offices(String customer_id, String name, String lat, String lng, long range, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        params.put("name", name);
        params.put("lat", lat);
        params.put("lng", lng);
        params.put("page_no", "1");
        params.put("page_size", "15");
        params.put("range", "" + range);
        OkHttpHelp.getAsync(null, Constant.offices, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(Constant.offices, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 修改基本信息
     *
     * @param customer_id 用户 ID
     * @param sex         用户性别
     * @param birthday    生日
     * @param head_pic    头像
     * @param callBack
     */
    public void update_profile(Dialog dialog, String customer_id, String sex, String birthday, File head_pic, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        params.put("sex", sex);
        params.put("birthday", birthday);
//        Map<String, File> fileMap = new HashMap<>();
//        fileMap.put("head_pic", head_pic);
        OkHttpHelp.postUploadFile(dialog, Constant.update_profile, params, "head_pic", head_pic, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().upLoadFile(dialog, Constant.update_profile, params, fileMap, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 社保卡绑定
     *
     * @param dialog
     * @param customer_id    用户 ID
     * @param name           姓名
     * @param id_card        身份证号
     * @param insurance_card 社保卡卡号
     * @param callBack
     */
    public void bind(Dialog dialog, String customer_id, String name, String id_card, String insurance_card, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        params.put("name", name);
        params.put("id_card", id_card);
        params.put("insurance_card", insurance_card);
        OkHttpHelp.postAsync(dialog, Constant.bind, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().post(dialog, Constant.bind, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 社保卡解除绑定
     *
     * @param dialog
     * @param customer_id 用户ID
     * @param callBack
     */
    public void unbind(Dialog dialog, String customer_id, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        OkHttpHelp.postAsync(dialog, Constant.unbind, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().post(dialog, Constant.unbind, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 获取社保信息
     *
     * @param customer_id 用户ID
     * @param callBack
     */
    public void query(String customer_id, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        OkHttpHelp.getAsync(null, Constant.query, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(Constant.query, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 获取验证码
     *
     * @param phone    手机号
     * @param callBack
     */
    public void getCode(String phone, final HttpCallBack callBack) {
        OkHttpHelp.getAsync(null, Constant.get_code + phone, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(Constant.get_code + phone, null, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 修改密码
     *
     * @param dialog
     * @param phone        手机号
     * @param new_password 新密码
     * @param callBack
     */
    public void change_pwd(Dialog dialog, String phone, String new_password, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        params.put("new_password", new_password);
        OkHttpHelp.postAsync(dialog, Constant.change_pwd, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().post(dialog, Constant.change_pwd, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 获取收货地址列表
     *
     * @param customer_id 用户ID
     * @param callBack
     */
    public void getAddressList(String customer_id, final OkHttpHelp.DataCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        OkHttpHelp.getAsync(null, Constant.addresses, params, callBack);
//        HttpHelp.getInstance().get(Constant.addresses, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }
    //    customer_id  String  是  用户 ID
//    consigee  String  是  收货人
//    address  String  是  收货地址
//    phone  String  手机号
//    lat  String  经度
//    lng  String  纬度
//    is_default  Boolean  是否为默认收货地址

    /**
     * 添加新的收货地址
     *
     * @param dialog
     * @param customer_id 用户 ID
     * @param consignee   收货人
     * @param address     收货地址
     * @param phone       手机号
     * @param lat         经度
     * @param lng         纬度
     * @param is_default  是否为默认收货地址
     * @param callBack
     */
    public void addNewAddress(Dialog dialog, String customer_id, String consignee, String address, String phone, String lat, String lng, boolean is_default, final OkHttpHelp.DataCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        params.put("consignee", consignee);
        params.put("address", address);
        params.put("phone", phone);
        params.put("lat", lat);
        params.put("lng", lng);
        params.put("is_default", is_default + "");
        OkHttpHelp.postAsync(dialog, Constant.address_create, params, callBack);
//        HttpHelp.getInstance().post(dialog, Constant.address_create, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 删除收货地址
     *
     * @param dialog
     * @param address_id 收货地址ID
     * @param callBack
     */
    public void deleteAddress(Dialog dialog, String address_id, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("address_id", address_id);
        OkHttpHelp.postAsync(dialog, Constant.remove, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().post(dialog, Constant.remove, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }
//    address_id  String  是  收货地址 ID
//    consigee  String  是  收货人
//    address  String  是  收货地址
//    phone  String  手机号
//    lat  String  经度
//    lng  String  纬度
//    is_default  Boolean  是否为默认收货地址

    /**
     * 修改收货地址
     *
     * @param dialog
     * @param address_id 收货地址 ID
     * @param consignee  收货人
     * @param address    收货地址
     * @param phone      手机号
     * @param lat        经度
     * @param lng        纬度
     * @param is_default 是否为默认收货地址
     * @param callBack
     */
    public void updateAddress(Dialog dialog, String address_id, String consignee, String address, String phone, String lat, String lng, boolean is_default, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("address_id", address_id);
        params.put("consignee", consignee);
        params.put("address", address);
        params.put("phone", phone);
        params.put("lat", lat);
        params.put("lng", lng);
        params.put("is_default", is_default + "");
        OkHttpHelp.postAsync(dialog, Constant.update, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().post(dialog, Constant.update, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 根据收货地址ID获取单条收货信息
     *
     * @param address_id 收货地址ID
     * @param callBack
     */
    public void getAddressById(String address_id, final HttpCallBack callBack) {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("customer_id", customer_id);
        String url = "/" + address_id;
        OkHttpHelp.getAsync(null, Constant.addresses + url, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(Constant.addresses + url, null, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 获取订单信息
     *
     * @param customer_id     用户 ID
     * @param page_no         页码
     * @param page_size       分页数据条数
     * @param status          订单状态 0 待付款 1 已付款3 待发货 4 待收货5 已收货 6 已取消
     * @param shipping_status 发货状态  -1 全部 0 未发货 1 已发货 2 已退货
     * @param pay_status      支付状态  -1 全部 0 未支付 1 已支付 2 已退款
     * @param callBack
     */
    public void getOrdersList(String customer_id, int page_no, int page_size, String status, String shipping_status, String pay_status, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        params.put("page_no", page_no + "");
        params.put("page_size", page_size + "");
        params.put("status", status);
        params.put("shipping_status", shipping_status);
        params.put("pay_status", pay_status);
        OkHttpHelp.getAsync(null, Constant.orders, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(Constant.orders, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 获取商品列表
     *
     * @param category_id         商品类目 ID（默认为1）
     * @param office_id           商家 ID
     * @param order               排序（默认为 1）
     * @param is_medical_card_pay 是否支持医保卡（默认为 false）
     * @param is_hot              是否热卖
     * @param page_no             页码
     * @param page_size           分页数据条数
     * @param callBack
     */
    public void getGoodsList(Dialog dialog, String category_id, String office_id, int order, boolean is_medical_card_pay, boolean is_hot, int page_no, int page_size, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("category_id", category_id);
        params.put("office_id", office_id);
        params.put("order", order + "");
        params.put("is_medical_card_pay", is_medical_card_pay + "");
        params.put("is_hot", is_hot + "");
        params.put("page_no", page_no + "");
        params.put("page_size", page_size + "");
        OkHttpHelp.getAsync(dialog, Constant.goods, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(dialog, Constant.goods, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 通过商品ID获取商品详情
     *
     * @param dialog
     * @param id          商品ID
     * @param customer_id 用户ID
     * @param callback
     */
    public void getGoodsDetailsById(Dialog dialog, String id, String customer_id, final HttpCallBack callback) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", id);
        params.put("customer_id", customer_id);
        OkHttpHelp.getAsync(dialog, Constant.goods + "/" + id, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callback.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callback.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(dialog, Constant.goods + "/" + id, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callback.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callback.onFail(errorMsg);
//            }
//        });
    }

    //    customer_id  String  用户 ID
//    goods_id  String  商品 ID
//    goods_num  Integer  商品数量
//    spec_str  String
//    商品规格
//    （Specbaseid:规
//    格项）

    /**
     * 添加商品到购物车中
     *
     * @param dialog
     * @param customer_id 用户ID
     * @param goods_id    商品ID
     * @param goods_num   商品数量
     * @param spec_str    商品规格
     * @param callBack
     */
    public void cars_create(Dialog dialog, String customer_id, String goods_id, int goods_num, String spec_str, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        params.put("goods_id", goods_id);
        params.put("goods_num", String.valueOf(goods_num));
        params.put("spec_str", spec_str);
        OkHttpHelp.postAsync(dialog, Constant.cars_create, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().post(dialog, Constant.cars_create, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 获取购物车列表
     *
     * @param customer_id 用户ID
     * @param callBack
     */
    public void getCarsList(String customer_id, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        OkHttpHelp.getAsync(null, Constant.cars, params, new OkHttpHelp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                callBack.onFail(e);
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                callBack.onSuccess(result);
            }
        });
//        HttpHelp.getInstance().get(Constant.cars, params, new HttpHelp.XCallBack() {
//            @Override
//            public void onSuccess(String result) {
//                callBack.onSuccess(result);
//            }
//
//            @Override
//            public void onError(Throwable errorMsg) {
//                callBack.onFail(errorMsg);
//            }
//        });
    }

    /**
     * 下载图片
     */
//    public void downLoadImage(String url, String filepath, String fileName, final HttpHelp.XCallBack callback) {
//        RequestParams params = new RequestParams(url);
//        //设置断点续传
//        params.setAutoResume(true);
//        params.setSaveFilePath(filepath);
//        OkHttpHelp.downloadAsync(url, filepath, fileName, new OkHttpHelp.DataCallBack() {
//            @Override
//            public void requestFailure(Request request, IOException e) {
//                callback.onError(e);
//            }
//
//            @Override
//            public void requestSuccess(String result) throws Exception {
//                callback.onSuccess(result);
//            }
//        });
////        x.http().get(params, new Callback.CommonCallback<File>() {
////            @Override
////            public void onSuccess(File result) {
////                callback.onSuccess(result.getAbsolutePath());
////            }
////
////            @Override
////            public void onError(Throwable ex, boolean isOnCallback) {
////                callback.onError(ex);
////            }
////
////            @Override
////            public void onCancelled(CancelledException cex) {
////
////            }
////
////            @Override
////            public void onFinished() {
////
////            }
////        });
//    }
}
