package com.panku.ybdjapp.http;

import android.app.Dialog;

import com.panku.pkBaseLibrary.http.HttpHelp;
import com.panku.ybdjapp.core.Constant;
import com.panku.ybdjapp.http.Interface.HttpCallBack;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
        HttpHelp.getInstance().post(dialog, Constant.PATH_LOGIN, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().post(dialog, Constant.PATH_REGISTER, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        params.put("level", level + "");
        HttpHelp.getInstance().get(Constant.getCategoryBases, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().get(Constant.offices, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        Map<String, File> fileMap = new HashMap<>();
        fileMap.put("head_pic", head_pic);
        HttpHelp.getInstance().upLoadFile(dialog, Constant.update_profile, params, fileMap, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().post(dialog, Constant.bind, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().post(dialog, Constant.unbind, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().get(Constant.query, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
    }

    /**
     * 获取验证码
     *
     * @param phone    手机号
     * @param callBack
     */
    public void getCode(String phone, final HttpCallBack callBack) {
        HttpHelp.getInstance().get(Constant.get_code + phone, null, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().post(dialog, Constant.change_pwd, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
    }

    /**
     * 获取收货地址列表
     *
     * @param customer_id 用户ID
     * @param callBack
     */
    public void getAddressList(String customer_id, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        HttpHelp.getInstance().get(Constant.addresses, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
    public void addNewAddress(Dialog dialog, String customer_id, String consignee, String address, String phone, String lat, String lng, boolean is_default, final HttpCallBack callBack) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("customer_id", customer_id);
        params.put("consignee", consignee);
        params.put("address", address);
        params.put("phone", phone);
        params.put("lat", lat);
        params.put("lng", lng);
        params.put("is_default", is_default + "");
        HttpHelp.getInstance().post(dialog, Constant.address_create, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().post(dialog, Constant.remove, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().post(dialog, Constant.update, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().get(Constant.addresses + url, null, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().get(Constant.orders, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().get(dialog, Constant.goods, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callBack.onFail(errorMsg);
            }
        });
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
        HttpHelp.getInstance().get(dialog, Constant.goods + "/" + id, params, new HttpHelp.XCallBack() {
            @Override
            public void onSuccess(String result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable errorMsg) {
                callback.onFail(errorMsg);
            }
        });
    }

    /**
     * 下载图片
     */
    public void downLoadImage(String url, String filepath, final HttpHelp.XCallBack callback) {
        RequestParams params = new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        x.http().get(params, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                callback.onSuccess(result.getAbsolutePath());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onError(ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
