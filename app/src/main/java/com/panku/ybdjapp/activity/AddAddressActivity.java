package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.panku.pkBaseLibrary.util.SharedPreferencesUtil;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.AddressInfo;
import com.panku.ybdjapp.core.UserInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;
import com.panku.ybdjapp.http.OkHttpHelp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;

/**
 * Date：2017/4/21
 * Time: 14:06
 * author: hyn
 * 添加修改收货地址
 */
public class AddAddressActivity extends Activity implements View.OnClickListener {
    private LinearLayout ll_back;//返回按钮
    private TextView tv_title;//标题
    private LinearLayout ll_save;//保存
    private EditText et_name;//收货人姓名
    private EditText et_phone;//收货人电话
    private TextView tv_area;//所在地区
    private EditText et_address;//详细地址
    private CheckBox cb_default;//是否设置为默认地址

    private HttpManager httpManager;
    private UserInfo userInfo;
    private boolean isDefault = false;//是否是默认地址
    private String address_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_address_change);
        initView();
        init();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_area = (TextView) findViewById(R.id.tv_area);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_address = (EditText) findViewById(R.id.et_address);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_save = (LinearLayout) findViewById(R.id.ll_save);
        cb_default = (CheckBox) findViewById(R.id.cb_default);
        cb_default.setOnClickListener(this);
        ll_back.setOnClickListener(this);
        ll_save.setOnClickListener(this);
        tv_area.setOnClickListener(this);
    }

    private void init() {
        httpManager = new HttpManager();
        userInfo = SharedPreferencesUtil.getMobileLoginInfo(this, UserInfo.class);
        address_id = getIntent().getStringExtra("address_id");
        if (address_id.length() > 0) {
            tv_title.setText("修改收货地址");
            getAddressById(address_id);
        } else {
            tv_title.setText("添加收货地址");
        }
    }

    private void selectCity() {
        CityPicker cityPicker = new CityPicker.Builder(this).province("河南省").provinceCyclic(true).
                city("郑州市").cityCyclic(false).
                districtCyclic(false).
                confirTextColor("#403E3B")
                .build();
        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... strings) {
                tv_area.setText(strings[0] + "-" + strings[1] + "-" + strings[2]);
            }

            @Override
            public void onCancel() {

            }
        });
    }

    /**
     * 获取单条收货信息
     *
     * @param address_id
     */
    private void getAddressById(String address_id) {
        httpManager.getAddressById(address_id, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("status").equals("ok")) {
                        JSONObject object = new JSONObject(jsonObject.getString("response"));
                        String name = object.getString("consignee");
                        String phone = object.getString("phone");
                        String address = object.getString("address");
                        boolean is_default = object.getBoolean("is_default");
                        et_name.setText(name);
                        et_address.setText(address);
                        et_phone.setText(phone);
                        cb_default.setChecked(is_default);
                        isDefault = is_default;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(Throwable errorMsg) {

            }
        });
    }

    /**
     * 添加新的收货地址
     */
    private void save() {
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();
        String area = tv_area.getText().toString();
        String str_address = et_address.getText().toString();
        if (name.length() == 0) {
            ToastUtils.showToast("请输入收货人姓名");
            return;
        }
        if (phone.length() == 0) {
            ToastUtils.showToast("请输入收货人联系方式");
            return;
        }
        if (area.length() == 0) {
            ToastUtils.showToast("请输入收货人所在地区");
            return;
        }
        if (str_address.length() == 0) {
            ToastUtils.showToast("请输入收货人详细地址");
            return;
        }
        if (tv_title.getText().toString().equals("修改收货地址")) {
            httpManager.updateAddress(new DialogLoadingView(this), address_id, name, area + str_address, phone, "", "", isDefault, new HttpCallBack() {
                @Override
                public void onSuccess(String result) {
                    Log.i("HYN", "更新：" + result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.getString("status").equals("ok")) {
                            JSONObject object = new JSONObject(jsonObject.getString("response"));
                            if (object.getString("message").equals("success")) {
                                ToastUtils.showToast("收货地址修改成功");
                                finish();
                            } else {
                                ToastUtils.showToast("收货地址修改失败");
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(Throwable errorMsg) {

                }
            });
        } else {
            httpManager.addNewAddress(new DialogLoadingView(this), userInfo.getId(), name, area + str_address, phone, "", "", isDefault, new OkHttpHelp.DataCallBack() {
                @Override
                public void requestFailure(Request request, IOException e) {

                }

                @Override
                public void requestSuccess(String result) throws Exception {
                    Log.i("HYN", "添加：" + result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        if (jsonObject.getString("status").equals("ok")) {
                            JSONObject object = new JSONObject(jsonObject.getString("response"));
                            if (object.getString("message").equals("success")) {
                                ToastUtils.showToast("收货地址添加成功");
                                finish();
                            } else {
                                ToastUtils.showToast("收货地址添加失败");
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
//                @Override
//                public void onSuccess(String result) {
//                    Log.i("HYN", "添加：" + result);
//                    try {
//                        JSONObject jsonObject = new JSONObject(result);
//                        if (jsonObject.getString("status").equals("ok")) {
//                            JSONObject object = new JSONObject(jsonObject.getString("response"));
//                            if (object.getString("message").equals("success")) {
//                                ToastUtils.showToast("收货地址添加成功");
//                                finish();
//                            } else {
//                                ToastUtils.showToast("收货地址添加失败");
//                            }
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//                @Override
//                public void onFail(Throwable errorMsg) {
//                    Log.e("HYN", errorMsg.getMessage().toString());
//                    ToastUtils.showToast("服务器连接失败");
//                }
//            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String tv = data.getStringExtra("Address");
                tv_area.setText(tv);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_save:
                save();
                break;
            case R.id.tv_area:
                startActivityForResult(new Intent(this, NearAddressActivity.class), 1000);
//                selectCity();
                break;
            case R.id.cb_default:
                isDefault = cb_default.isChecked();
                break;
        }
    }
}
