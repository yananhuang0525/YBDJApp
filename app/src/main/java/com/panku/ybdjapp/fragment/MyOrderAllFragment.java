package com.panku.ybdjapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.panku.ybdjapp.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Date：2017/4/24
 * Time: 16:44
 * author: hyn
 */
@ContentView(R.layout.fg_my_oredr_all)
public class MyOrderAllFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        return view;
    }
}
