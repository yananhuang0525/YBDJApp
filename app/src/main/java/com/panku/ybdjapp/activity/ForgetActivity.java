package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.util.ValidatorUtil;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.SBCardInfo;
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
 * Time: 15:28
 * 忘记密码界面
 */
@ContentView(R.layout.ac_forget)
public class ForgetActivity extends Activity {
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;//返回按钮
    @ViewInject(R.id.tv_title)
    private TextView tv_title;//标题
    @ViewInject(R.id.tv_two)
    private TextView tv_two;//
    @ViewInject(R.id.tv_two_text)
    private TextView tv_two_text;//
    @ViewInject(R.id.ll_one)
    private LinearLayout ll_one;//获取验证码布局
    @ViewInject(R.id.et_phone)
    private EditText et_phone;//手机号码
    @ViewInject(R.id.et_code)
    private EditText et_code;//验证码
    @ViewInject(R.id.tv_get_code)
    private TextView tv_get_code;//获取验证码
    @ViewInject(R.id.ll_two)
    private LinearLayout ll_two;//设置新密码布局
    @ViewInject(R.id.et_pass)
    private EditText et_pass;
    @ViewInject(R.id.et_pass_again)
    private EditText et_pass_again;
    @ViewInject(R.id.tv_next)
    private TextView tv_next;//下一步
    private HttpManager httpManager;

    private String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        tv_title.setText("找回密码");
        httpManager = new HttpManager();
    }

    @Event(value = {R.id.ll_back, R.id.tv_get_code, R.id.tv_next})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_get_code:
                getCode();
                break;
            case R.id.tv_next:
                next();
                break;
        }
    }

    private void next() {
        if (tv_next.getText().toString().equals("下一步")) {
            if (et_phone.getText().toString().length() == 0) {
                ToastUtils.showToast("请输入手机号码");
                return;
            } else if (et_code.getText().length() == 0) {
                ToastUtils.showToast("请输入验证码");
                return;
            }
//            if (code.equals(et_code.getText().toString())) {
            ll_one.setVisibility(View.GONE);
            ll_two.setVisibility(View.VISIBLE);
            tv_two.setBackgroundResource(R.drawable.shape_yes);
            tv_two_text.setTextColor(getResources().getColor(R.color.btn_register_color));
            tv_next.setText("确定");
//            } else {
//                ToastUtils.showToast("验证码错误");
//            }
        } else if (tv_next.getText().toString().equals("确定")) {
            String pass = et_pass.getText().toString();
            String passAgain = et_pass_again.getText().toString();
            if (pass.length() == 0) {
                ToastUtils.showToast("请输入新密码");
                return;
            } else if (passAgain.length() == 0) {
                ToastUtils.showToast("请再次输入新密码");
                return;
            }
            if (!pass.equals(passAgain)) {
                ToastUtils.showToast("两次密码输入不一致请重新输入");
                et_pass.setText("");
                et_pass_again.setText("");
                return;
            }
            httpManager.change_pwd(new DialogLoadingView(this), et_phone.getText().toString(), pass, new HttpCallBack() {
                @Override
                public void onSuccess(String result) {
                    Log.i("HYN", "修改密码：" + result);
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject object = new JSONObject(jsonObject.getString("response"));
                        if (jsonObject.getString("status").equals("ok")) {
                            if (object.getString("message").equals("success")) {
                                ToastUtils.showToast("密码修改成功");
                                finish();
                            }
                        } else if (jsonObject.getString("status").equals("failed")) {
                            ToastUtils.showToast(object.getString("message"));
                            finish();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(Throwable errorMsg) {
                    Log.e("HYN", "修改密码失败信息：" + errorMsg.getMessage().toString());
                }
            });
        }
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        if (et_phone.getText().length() > 0) {
            if (ValidatorUtil.isMobile(et_phone.getText().toString())) {
                timer.start();
                httpManager.getCode(et_phone.getText().toString(), new HttpCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("HYN", "获取验证码：" + result);
                        try {
                            JSONObject object = new JSONObject(result);
                            if (object.getString("status").equals("ok")) {
                                JSONObject jsonObject = new JSONObject(object.getString("response"));
                                code = jsonObject.getString("verification_code");
                            }
//                            else if (object.getString("status").equals("failed")) {
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(Throwable errorMsg) {
                        Log.i("HYN", "获取验证码：" + errorMsg.getMessage().toString());
                    }
                });
            } else {
                ToastUtils.showToast("请输入正确的手机号码");
            }
        } else {
            ToastUtils.showToast("请输入手机号码");
        }
    }

    /**
     * 倒计时
     */
    private CountDownTimer timer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_get_code.setText(millisUntilFinished / 1000 + "s后重新获取");
            tv_get_code.setEnabled(false);
            tv_get_code.setBackgroundResource(R.color.bg_color);
        }

        @Override
        public void onFinish() {
            tv_get_code.setEnabled(true);
            tv_get_code.setBackgroundResource(R.drawable.sel_bg_get_code);
            tv_get_code.setText("重新获取验证码");
            timer.cancel();
        }
    };
}
