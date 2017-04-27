package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.panku.pkBaseLibrary.util.SharedPreferencesUtil;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.pkBaseLibrary.view.SimplePromptDialog;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.AddressManagerAdapter;
import com.panku.ybdjapp.biz.AddressInfo;
import com.panku.ybdjapp.core.UserInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/21
 * Time: 11:19
 * author: hyn
 */
@ContentView(R.layout.ac_address_manager)
public class AddressManagerActivity extends Activity {
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;//返回按钮
    @ViewInject(R.id.tv_title)
    private TextView tv_title;//标题
    @ViewInject(R.id.tv_prompt)
    private TextView tv_prompt;//当没有设置收货地址时显示的提示信息
    @ViewInject(R.id.rv_address)
    private RecyclerView rv_address;
    @ViewInject(R.id.tv_add_address)
    private TextView tv_add_address;//添加新收货地址

    private HttpManager httpManager;
    private UserInfo userInfo;
    private List<AddressInfo.ResponseBean.Address> addressList;
    private AddressManagerAdapter addressManagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        tv_title.setText("收货地址管理");
        httpManager = new HttpManager();
        userInfo = SharedPreferencesUtil.getMobileLoginInfo(this, UserInfo.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddressList();
    }

    @Event(value = {R.id.ll_back, R.id.tv_add_address})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_add_address:
                Intent intent = new Intent(AddressManagerActivity.this, AddAddressActivity.class);
                intent.putExtra("address_id", "");
                startActivity(intent);
                break;
        }
    }

    /**
     * 获取收货地址列表
     */
    private void getAddressList() {
        httpManager.getAddressList(userInfo.getId(), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HYN", "收货地址：" + result);
                setData(result);
            }

            @Override
            public void onFail(Throwable errorMsg) {

            }
        });
    }

    private void setData(String result) {
        AddressInfo addressInfo = JSON.parseObject(result, AddressInfo.class);
        if (addressInfo.getStatus().equals("ok")) {
            addressList = addressInfo.getResponse().getAddresses();
            if (addressList == null) {
                rv_address.setVisibility(View.GONE);
                tv_prompt.setVisibility(View.VISIBLE);
                return;
            } else {
                rv_address.setVisibility(View.VISIBLE);
                tv_prompt.setVisibility(View.GONE);
            }
            addressManagerAdapter = new AddressManagerAdapter(R.layout.item_address_manager, addressList);
            rv_address.setLayoutManager(new LinearLayoutManager(this));
            rv_address.setAdapter(addressManagerAdapter);
            addressManagerAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    AddressInfo.ResponseBean.Address address = (AddressInfo.ResponseBean.Address) adapter.getItem(position);
                    switch (view.getId()) {
                        case R.id.ll_address:
                        case R.id.tv_change:
                            Intent intent = new Intent(AddressManagerActivity.this, AddAddressActivity.class);
                            intent.putExtra("address_id", address.getId());
                            startActivity(intent);
                            break;
                        case R.id.tv_delete://删除
                            deleteAddress(address.getId());
                            break;
                        case R.id.cb_default:
                            updateAddress(address);
                            break;
                    }

                }
            });
        } else {

        }
    }

    /**
     * 删除收货地址
     *
     * @param address_id
     */
    private void deleteAddress(final String address_id) {
        SimplePromptDialog simplePromptDialog = new SimplePromptDialog(this);
        simplePromptDialog.setShowCancel();
        simplePromptDialog.setContentText("你确定删除此条收货地址？");
        simplePromptDialog.setOnSelectMenuListener(new SimplePromptDialog.OnSelectMenuListener() {
            @Override
            public void onYes() {
                httpManager.deleteAddress(new DialogLoadingView(AddressManagerActivity.this), address_id, new HttpCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("HYN", "删除成功" + result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getString("status").equals("ok")) {
                                JSONObject object = new JSONObject(jsonObject.getString("response"));
                                if (object.getString("message").equals("success")) {
                                    ToastUtils.showToast("收货地址删除成功");
                                    getAddressList();
                                } else {
                                    ToastUtils.showToast("收货地址删除失败");
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
            }
        });
        simplePromptDialog.show();
    }

    private void updateAddress(AddressInfo.ResponseBean.Address address) {
        httpManager.updateAddress(new DialogLoadingView(this), address.getId(), address.getConsignee(), address.getAddress(), address.getPhone(), "", "", !address.is_default(), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HYN", "更新：" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("status").equals("ok")) {
                        getAddressList();
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
}
