package com.panku.pkBaseLibrary.util;

import android.widget.Toast;

import com.panku.pkBaseLibrary.base.PanKuApplication;

/**
 * Created by hyn on 2017/3/1.
 */

public class ToastUtils {
    /**
     * 显示提示信息
     *
     * @param message 信息内容
     */
    public static void showToast(String message) {
        Toast.makeText(PanKuApplication.context(), message, Toast.LENGTH_SHORT).show();
    }
}
