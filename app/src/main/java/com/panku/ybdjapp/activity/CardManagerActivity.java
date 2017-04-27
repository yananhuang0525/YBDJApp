package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.fastjson.JSON;
import com.panku.pkBaseLibrary.util.SharedPreferencesUtil;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.SBCardInfo;
import com.panku.ybdjapp.core.UserInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Date：2017/4/20
 * Time: 14:18
 * author: hyn
 */
@ContentView(R.layout.ac_card_manager)
public class CardManagerActivity extends Activity {
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.ll_input)
    private LinearLayout ll_input;//输入布局
    @ViewInject(R.id.et_name)
    private EditText et_name;//姓名
    @ViewInject(R.id.et_card_num)
    private EditText et_card_num;//社保号码
    @ViewInject(R.id.et_card_code)
    private EditText et_card_code;//社保卡号
    @ViewInject(R.id.ll_show)
    private LinearLayout ll_show;//显示布局
    @ViewInject(R.id.tv_name)
    private TextView tv_name;
    @ViewInject(R.id.tv_card_num)
    private TextView tv_card_num;
    @ViewInject(R.id.tv_card_code)
    private TextView tv_card_code;
    @ViewInject(R.id.tv_btn)
    private TextView tv_btn;

    private HttpManager httpManager;
    private UserInfo userInfo;
    private SBCardInfo sbCardInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    private void init() {
        tv_title.setText("社保卡管理");
        httpManager = new HttpManager();
        userInfo = SharedPreferencesUtil.getMobileLoginInfo(this, UserInfo.class);
        if (userInfo != null) {
            getCardInfo();
            if (sbCardInfo != null) {
                ll_input.setVisibility(View.GONE);
                ll_show.setVisibility(View.VISIBLE);
                tv_btn.setText("解除绑定");
                tv_name.setText(sbCardInfo.getResponse().getName());
                tv_card_code.setText(sbCardInfo.getResponse().getInsurance_card());
                tv_card_num.setText(sbCardInfo.getResponse().getId_card());
            } else {
                ll_input.setVisibility(View.VISIBLE);
                ll_show.setVisibility(View.GONE);
                tv_btn.setText("绑定社保卡");
            }
        }
    }

    @Event(value = {R.id.ll_back, R.id.tv_btn})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_btn:
                if (tv_btn.getText().toString().equals("解除绑定")) {
                    unbindCard();
                } else if (tv_btn.getText().toString().equals("绑定社保卡")) {
                    bindCard();
                }
                break;
        }
    }

    /**
     * 绑定社保卡
     */
    private void bindCard() {
        if (et_name.getText().length() == 0) {
            ToastUtils.showToast("请输入姓名");
            return;
        } else if (et_card_code.getText().length() == 0) {
            ToastUtils.showToast("请输入社保卡号");
            return;
        } else if (et_card_num.getText().length() == 0) {
            ToastUtils.showToast("请输入社保号码");
            return;
        }
        httpManager.bind(new DialogLoadingView(this), userInfo.getId(), et_name.getText().toString(), et_card_num.getText().toString(), et_card_code.getText().toString(), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HYN", "绑定社保卡：" + result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("status").equals("ok")) {
                        sbCardInfo = JSON.parseObject(result, SBCardInfo.class);
                    } else if (object.getString("status").equals("failed")) {
                        ToastUtils.showToast("社保卡绑定失败，请检查填写信息是否正确");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(Throwable errorMsg) {
                Log.e("HYN", "绑定社保卡失败：" + errorMsg);
            }
        });
    }

    /**
     * 解除社保卡绑定
     */
    private void unbindCard() {
        httpManager.unbind(new DialogLoadingView(this), userInfo.getId(), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HYN", "解绑社保卡：" + result);
            }

            @Override
            public void onFail(Throwable errorMsg) {
                Log.i("HYN", "解绑社保卡失败：" + errorMsg);
            }
        });
    }

    /**
     * 获取社保卡信息
     */
    private void getCardInfo() {
        httpManager.query(userInfo.getId(), new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.e("HYN", "社保卡获取" + result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getString("status").equals("ok")) {
                        sbCardInfo = JSON.parseObject(result, SBCardInfo.class);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFail(Throwable errorMsg) {
                Log.e("HYN", "社保卡获取失败" + errorMsg.getMessage().toString());
            }
        });
    }
}
