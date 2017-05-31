package com.panku.ybdjapp.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps2d.SupportMapFragment;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.ListDrugShopAdapter;
import com.panku.ybdjapp.biz.DrugStoreInfo;
import com.panku.ybdjapp.biz.NearbyShopInfo;


import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/11
 * Time: 13:42
 * author: hyn
 * 附近药店列表
 */
public class ListDrugStoreFragment extends SupportMapFragment {
    private RecyclerView rv_list;
    private ListDrugShopAdapter listDrugShopAdapter;
    private List<DrugStoreInfo> list;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
//        View view = x.view().inject(this, layoutInflater, viewGroup);
        View view = layoutInflater.inflate(R.layout.fg_nearby_list, viewGroup, false);
        rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        init();
        return view;
    }

    private void init() {
        list = new ArrayList<>();
        NearbyShopInfo info = MapFragment.nearbyShopInfo;
        if (info != null) {
            if (info.getStatus().equals("ok")) {
                for (int i = 0; i < info.getResponse().getOfficeList().size(); i++) {
                    DrugStoreInfo storeInfo = new DrugStoreInfo();
                    storeInfo.setDrugName(info.getResponse().getOfficeList().get(i).getName());
                    storeInfo.setAddress(info.getResponse().getOfficeList().get(i).getAddress());
                    storeInfo.setDistance(info.getResponse().getOfficeList().get(i).getDistance());
                    storeInfo.setPhone(info.getResponse().getOfficeList().get(i).getPhone());
                    list.add(storeInfo);
                }
                listDrugShopAdapter = new ListDrugShopAdapter(R.layout.item_list_shop, list);
                rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv_list.setAdapter(listDrugShopAdapter);
//                rv_list.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
            }
        }

    }
}
