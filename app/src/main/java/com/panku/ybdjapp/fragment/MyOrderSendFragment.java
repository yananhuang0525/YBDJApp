package com.panku.ybdjapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panku.ybdjapp.R;


/**
 * Date：2017/4/24
 * Time: 16:44
 * author: hyn
 */
public class MyOrderSendFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_my_oredr_send_goods, container, false);
        return view;
    }
}
