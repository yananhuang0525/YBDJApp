package com.panku.ybdjapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panku.ybdjapp.R;

/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/5
 * Time: 15:06
 */
public class BuyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_buy, container, false);
        Log.i("HYN", "Buy==onCreate");
        return view;
    }
}
