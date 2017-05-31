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


/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/5
 * Time: 15:06
 */
public class DetailedListFragment extends Fragment {
    private SpringView springView;
    private RecyclerView rv_detailed_list;
    private HttpManager httpManager;
    private UserInfo userInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = x.view().inject(this, inflater, container);
        View view = inflater.inflate(R.layout.fg_detailed_list, container, false);
        initView(view);
        Log.i("HYN", "List==onCreate");
        init();
        return view;
    }

    private void initView(View view) {
        springView = (SpringView) view.findViewById(R.id.sv);
        rv_detailed_list = (RecyclerView) view.findViewById(R.id.rv_detailed_list);
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
