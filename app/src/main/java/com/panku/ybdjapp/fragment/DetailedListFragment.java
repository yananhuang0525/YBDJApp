package com.panku.ybdjapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.widget.SpringView;
import com.panku.pkBaseLibrary.util.SharedPreferencesUtil;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.core.UserInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/5
 * Time: 15:06
 */
@ContentView(R.layout.fg_detailed_list)
public class DetailedListFragment extends Fragment {
    @ViewInject(R.id.sv)
    private SpringView springView;
    @ViewInject(R.id.rv_detailed_list)
    private RecyclerView rv_detailed_list;
    private HttpManager httpManager;
    private UserInfo userInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        Log.i("HYN", "List==onCreate");
        init();
        return view;
    }

    private void init() {
        httpManager = new HttpManager();
        userInfo = SharedPreferencesUtil.getMobileLoginInfo(getActivity(), UserInfo.class);
        if (userInfo != null) {
            httpManager.getOrdersList(userInfo.getId(), 0, 10, "0", "-1", "", new HttpCallBack() {
                @Override
                public void onSuccess(String result) {

                }

                @Override
                public void onFail(Throwable errorMsg) {

                }
            });
        }
    }
}
