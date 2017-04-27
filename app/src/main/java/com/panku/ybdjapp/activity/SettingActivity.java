package com.panku.ybdjapp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.panku.pkBaseLibrary.http.HttpHelp;
import com.panku.pkBaseLibrary.util.FileHelp;
import com.panku.pkBaseLibrary.util.PermissionUtils;
import com.panku.pkBaseLibrary.util.SharedPreferencesUtil;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.pkBaseLibrary.view.SelectDateDialog;
import com.panku.pkBaseLibrary.view.SimplePromptDialog;
import com.panku.pkBaseLibrary.view.photo.PhotoGetHelper;
import com.panku.pkBaseLibrary.view.photo.ResultInfo;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.core.Constant;
import com.panku.ybdjapp.core.UserInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;
import com.panku.ybdjapp.utils.ImageLoaderUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * Date：2017/4/20
 * Time: 9:54
 * author: hyn
 */
@ContentView(R.layout.ac_setting)
public class SettingActivity extends AppCompatActivity {
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_user_head)
    private ImageView iv_user_head;//头像
    @ViewInject(R.id.tv_select)
    private TextView tv_select;//选择图片
    @ViewInject(R.id.tv_user_name)
    private TextView tv_user_name;//用户名
    @ViewInject(R.id.tv_birthday)
    private TextView tv_birthday;//出生日期
    @ViewInject(R.id.tv_change)
    private TextView tv_change;//修改信息
    @ViewInject(R.id.tv_out)
    private TextView tv_out;//退出登录
    @ViewInject(R.id.radioGroup)
    private RadioGroup radioGroup;
    private PhotoGetHelper photoGetHelper;
    private HttpManager httpManager;
    private ResultInfo resultInfo = null;//相机相册选取结果
    private File uploadfile = null;//上传的图片
    private String sex = "";//性别 0 女 1 男
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PermissionUtils.requestPermission(this, PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE, permissionGrant);
    }

    private void init() {
        photoGetHelper = new PhotoGetHelper(this);
        httpManager = new HttpManager();
        tv_title.setText("基本信息管理");
        userInfo = SharedPreferencesUtil.getMobileLoginInfo(this, UserInfo.class);
        if (userInfo != null) {
            String savePath = FileHelp.getSaveFilePath() + "photo_img.png";
//            String savePath = "http://www.kongtu.com/9387630196_image.jpg";
            if (userInfo.getHead_pic().length() > 0) {
                httpManager.downLoadImage(Constant.BASE_URL + userInfo.getHead_pic(), savePath, new HttpHelp.XCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e("HYN", "头像下载：" + result);
                        uploadfile = new File(result);
                    }

                    @Override
                    public void onError(Throwable errorMsg) {
                        Log.e("HYN", "头像失败：" + errorMsg);
                    }
                });
            }
            ImageLoaderUtil.loadCircularHeadImg(iv_user_head, userInfo.getHead_pic(), true);
            tv_user_name.setText(userInfo.getUsername());
            tv_birthday.setText(userInfo.getBirthday());
            sex = userInfo.getSex();
        }

        if (sex.equals("0")) {
            radioGroup.check(R.id.rb_woman);
        } else {
            radioGroup.check(R.id.rb_man);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_man:
                        sex = "1";
                        break;
                    case R.id.rb_woman:
                        sex = "0";
                        break;
                }
            }
        });
    }

    @Event(value = {R.id.ll_back, R.id.tv_select, R.id.tv_birthday, R.id.tv_change, R.id.tv_out})
    private void Event(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_select:
                PermissionUtils.requestPermission(this, PermissionUtils.CODE_CAMERA, permissionGrant);
//                showDlg();
                break;
            case R.id.tv_birthday:
                final SelectDateDialog selectDateDialog = new SelectDateDialog(this);
                selectDateDialog.setDialogButtonListener(new SelectDateDialog.DialogButtonListener() {
                    @Override
                    public void positiveClick() {
                        selectDateDialog.dismiss();
                        tv_birthday.setText(selectDateDialog.setDate());
                    }

                    @Override
                    public void negativeClick() {
                        selectDateDialog.dismiss();
                    }
                });
                selectDateDialog.show();
                break;
            case R.id.tv_change:
                if (uploadfile != null) {
                    changeInfo();
                } else {
                    ToastUtils.showToast("请设置头像");
                }
                break;
            case R.id.tv_out:
                try {
                    SharedPreferencesUtil.setMobileLoginInfo(this, "");
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void showDlg() {
        //检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                //已经禁止提示了
//                Toast.makeText(this, "您已禁止该权限，需要重新开启。", Toast.LENGTH_SHORT).show();
                SimplePromptDialog dialog = new SimplePromptDialog(this);
                dialog.setContentText("您已禁止相机权限，需要重新开启。");
                dialog.setShowCancel();
                dialog.setOnSelectMenuListener(new SimplePromptDialog.OnSelectMenuListener() {
                    @Override
                    public void onYes() {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", SettingActivity.this.getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });
                dialog.show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 321);
            }
        } else {
            photoGetHelper.showDialog();
        }
    }

    /**
     * 修改信息
     */
    private void changeInfo() {
        httpManager.update_profile(new DialogLoadingView(this), userInfo.getId(), sex, tv_birthday.getText().toString(), uploadfile, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HYN", "修改信息：" + result);
                JSONObject object = null;
                try {
                    object = new JSONObject(result);
                    String status = object.getString("status");
                    if (status.equals("ok")) {
                        ToastUtils.showToast("修改成功");
                        JSONObject jsonObject = new JSONObject(object.getString("response"));
                        UserInfo userInfo = SharedPreferencesUtil.getMobileLoginInfo(SettingActivity.this, UserInfo.class);
                        userInfo.setHead_pic(jsonObject.getString("head_pic"));
                        userInfo.setBirthday(tv_birthday.getText().toString());
                        userInfo.setSex(sex);
                        SharedPreferencesUtil.setMobileLoginInfo(SettingActivity.this, JSON.toJSONString(userInfo));
                        finish();
                    } else {
                        ToastUtils.showToast("修改失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFail(Throwable errorMsg) {
                Log.i("HYN", "修改" + errorMsg.getMessage().toString());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        resultInfo = photoGetHelper.operationResult(requestCode, resultCode, data, iv_user_head);
        if (resultInfo != null && resultInfo.isDealFinished()) {
            uploadfile = new File(resultInfo.getFilePath());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private PermissionUtils.PermissionGrant permissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_CAMERA:
                    photoGetHelper.showDialog();
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    Log.i("HYN", "储存权限");
                    break;
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.requestPermissionsResult(this, requestCode, permissions, grantResults, permissionGrant);
    }
}
