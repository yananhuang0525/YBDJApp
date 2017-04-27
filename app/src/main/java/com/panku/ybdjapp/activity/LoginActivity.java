package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.panku.ybdjapp.core.Constant;
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
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/6
 * Time: 15:26
 * 登录界面
 */
@ContentView(R.layout.ac_login)
public class LoginActivity extends Activity {
    @ViewInject(R.id.et_phone)
    private EditText et_phone;//手机号码
    @ViewInject(R.id.et_pass)
    private EditText et_pass;//密码
    @ViewInject(R.id.tv_title)
    private TextView tv_title;//标题
    @ViewInject(R.id.tv_forget)
    private TextView tv_forget;//忘记密码
    @ViewInject(R.id.tv_login)
    private TextView tv_login;//登陆
    @ViewInject(R.id.tv_register)
    private TextView tv_register;//注册
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;//返回按钮
    @ViewInject(R.id.ll_rl_login)
    private LinearLayout ll_rl_login;//人脸识别登陆
    @ViewInject(R.id.ll_wx_login)
    private LinearLayout ll_wx_login;//微信登陆
    @ViewInject(R.id.ll_zfb_login)
    private LinearLayout ll_zfb_login;//支付宝登陆

    private HttpManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        manager = new HttpManager();
        tv_title.setText("账号登录");
    }

    @Event(value = {R.id.ll_back, R.id.tv_forget, R.id.tv_login, R.id.tv_register, R.id.ll_rl_login, R.id.ll_wx_login, R.id.ll_zfb_login})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_forget:
                startActivity(new Intent(this, ForgetActivity.class));
                break;
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.ll_rl_login:
                break;
            case R.id.ll_wx_login:
                break;
            case R.id.ll_zfb_login:
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        String phone = et_phone.getText().toString();
        String pass = et_pass.getText().toString();
        if (phone.length() > 0) {
            if (pass.length() > 0) {
                manager.login(new DialogLoadingView(this), phone, pass, new HttpCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("HYN", "登录成功" + result);
                        try {
                            JSONObject object = new JSONObject(result);
                            String status = object.getString("status");
                            if (status.equals("ok")) {
//                                Constant.UserInfo = JSON.parseObject(object.getString("response"), UserInfo.class);
                                ToastUtils.showToast("登录成功");
                                SharedPreferencesUtil.setMobileLoginInfo(LoginActivity.this, object.getString("response"));
                                finish();
                            } else {
                                JSONObject jsonObject = new JSONObject(object.getString("response"));
                                ToastUtils.showToast(jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(Throwable errorMsg) {

                    }
                });
            } else {
                ToastUtils.showToast("请输入密码");
            }
        } else {
            ToastUtils.showToast("请输入用户名密码");
        }
    }
}
