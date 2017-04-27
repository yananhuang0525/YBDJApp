package com.panku.ybdjapp.http.Interface;

import com.panku.ybdjapp.biz.DrugType;

import java.util.List;

/**
 * Date：2017/4/7
 * Time: 13:39
 * author: hyn
 */

public interface HttpCallBack {
    void onSuccess(String result);

    void onFail(Throwable errorMsg);
}
