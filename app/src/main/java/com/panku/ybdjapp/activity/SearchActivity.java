package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.panku.pkBaseLibrary.util.DividerGridItemDecoration;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.HotAdapter;
import com.panku.ybdjapp.adapter.SellingAdapter;
import com.panku.ybdjapp.biz.DrugInfo;


import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/10
 * Time: 14:15
 * author: hyn
 * 搜索药品
 */
public class SearchActivity extends Activity {
    private RecyclerView rv_hot;
    private RecyclerView rv_selling;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager1;
    private List<DrugInfo> list;
    private SellingAdapter sellingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_search);
        rv_hot = (RecyclerView) findViewById(R.id.rv_hot);
        rv_selling = (RecyclerView) findViewById(R.id.rv_selling);
        initRV();
    }

    private void initRV() {
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_hot.setLayoutManager(linearLayoutManager);
        List data = new ArrayList();
        data.add("宝宝常备");
        data.add("清热解毒");
        data.add("肠胃用药");
        data.add("外伤常备");
        data.add("营养保健");
        data.add("感冒咳嗽");
        data.add("电脑一族");
        data.add("外出旅游");
        data.add("五官用药");
        data.add("高血压");
        data.add("风湿骨伤");
        data.add("家用电器");
        rv_hot.setAdapter(new HotAdapter(R.layout.item_search_hot, data));
        list = new ArrayList();
        for (int i = 0; i < 11; i++) {
            DrugInfo info = new DrugInfo();
            info.setId(i);
            info.setIcon(R.mipmap.tu06);
            info.setName("药名" + i);
            info.setIntroduce("介绍==" + i);
            info.setPrice("￥ " + i);
            list.add(info);
        }
        linearLayoutManager1 = new GridLayoutManager(this, 2);
        sellingAdapter = new SellingAdapter(R.layout.item_search_selling, list);
        rv_selling.setLayoutManager(linearLayoutManager1);
        rv_selling.setAdapter(sellingAdapter);
        rv_selling.addItemDecoration(new DividerGridItemDecoration(this));
        sellingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                DrugInfo info = (DrugInfo) adapter.getItem(position);
                ToastUtils.showToast("加入" + info.getId());
            }
        });
    }
}
