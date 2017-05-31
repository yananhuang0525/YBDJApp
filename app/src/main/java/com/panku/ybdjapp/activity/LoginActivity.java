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
/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/6
 * Time: 15:26
 * 登录界面
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private EditText et_phone;//手机号码
    private EditText et_pass;//密码
    private TextView tv_title;//标题
    private TextView tv_forget;//忘记密码
    private TextView tv_login;//登陆
    private TextView tv_register;//注册
    private LinearLayout ll_back;//返回按钮
    private LinearLayout ll_rl_login;//人脸识别登陆
    private LinearLayout ll_wx_login;//微信登陆
    private LinearLayout ll_zfb_login;//支付宝登陆

    private HttpManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        initView();
        manager = new HttpManager();
        tv_title.setText("账号登录");
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pass = (EditText) findViewById(R.id.et_pass);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_forget = (TextView) findViewById(R.id.tv_forget);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_rl_login = (LinearLayout) findViewById(R.id.ll_rl_login);
        ll_wx_login = (LinearLayout) findViewById(R.id.ll_wx_login);
        ll_zfb_login = (LinearLayout) findViewById(R.id.ll_zfb_login);
        ll_back.setOnClickListener(this);
        tv_forget.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        ll_rl_login.setOnClickListener(this);
        ll_wx_login.setOnClickListener(this);
        ll_zfb_login.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
}
