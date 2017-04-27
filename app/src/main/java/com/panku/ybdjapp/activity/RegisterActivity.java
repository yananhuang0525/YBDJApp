package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.panku.pkBaseLibrary.util.BitmapCodeUtils;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.util.ValidatorUtil;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/6
 * Time: 15:27
 * 会员注册界面
 */
@ContentView(R.layout.ac_register)
public class RegisterActivity extends Activity {
    @ViewInject(R.id.et_phone)
    private EditText et_phone;//手机号码
    @ViewInject(R.id.et_code)
    private EditText et_code;//验证码
    @ViewInject(R.id.et_pass)
    private EditText et_pass;//密码
    @ViewInject(R.id.et_again)
    private EditText et_again;//再次输入密码
    @ViewInject(R.id.tv_title)
    private TextView tv_title;//标题
    @ViewInject(R.id.tv_get_code)
    private TextView tv_get_code;//获取验证码
    @ViewInject(R.id.tv_login)
    private TextView tv_login;//登陆
    @ViewInject(R.id.tv_register)
    private TextView tv_register;//注册
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;//返回按钮
    @ViewInject(R.id.iv_code)
    private ImageView iv_code;//验证码图片
    @ViewInject(R.id.cb)
    private CheckBox cb;
    private HttpManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        manager = new HttpManager();
        tv_title.setText("会员注册");
        iv_code.setImageBitmap(BitmapCodeUtils.getInstance().createBitmap());
    }

    @Event(value = {R.id.ll_back, R.id.iv_code, R.id.tv_login, R.id.tv_register})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_code:
                iv_code.setImageBitmap(BitmapCodeUtils.getInstance().createBitmap());
                break;
            case R.id.tv_login:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.tv_register:
                register();
                break;
        }
    }

    /**
     * 注册
     */
    private void register() {
        if (ValidatorUtil.isMobile(et_phone.getText().toString())) {//手机号码验证
            Log.i("HYN", BitmapCodeUtils.getInstance().getCode() + ":" + et_code.getText().toString());
            if (BitmapCodeUtils.getInstance().getCode().equals(et_code.getText().toString())) {//验证码验证
                if (et_pass.getText().toString().equals(et_again.getText().toString())) {//两次密码一致
                    if (cb.isChecked()) {

                        manager.register(new DialogLoadingView(this), et_phone.getText().toString(), et_pass.getText().toString(), new HttpCallBack() {
                            @Override
                            public void onSuccess(String result) {
                                Log.i("HYN", "注册成功==" + result);
                                ToastUtils.showToast("注册成功");
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }

                            @Override
                            public void onFail(Throwable errorMsg) {
                                Log.i("HYN", "注册失败==" + errorMsg.getMessage().toString());
                            }
                        });
                    } else {
                        ToastUtils.showToast("请同意协议");
                    }
                } else {
                    ToastUtils.showToast("两次密码不一致");
                    et_pass.setText("");
                    et_again.setText("");
                }
            } else {
                ToastUtils.showToast("验证码错误");
                iv_code.setImageBitmap(BitmapCodeUtils.getInstance().createBitmap());
                et_code.setText("");
            }
        } else {
            ToastUtils.showToast("请输入正确的手机号码");
        }

    }

    /**
     * 倒计时
     */
    private CountDownTimer timer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_get_code.setText(millisUntilFinished / 1000 + "s后重新获取");
        }

        @Override
        public void onFinish() {
            tv_get_code.setEnabled(true);
            tv_get_code.setBackgroundResource(R.drawable.sel_bg_get_code);
            tv_get_code.setText("获取验证码");
            timer.cancel();
        }
    };
}
