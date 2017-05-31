package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.panku.pkBaseLibrary.util.DividerGridItemDecoration;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.GoodsListAdapter;
import com.panku.ybdjapp.biz.GoodsDetailsInfo;
import com.panku.ybdjapp.biz.GoodsListInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;

import java.util.List;

/**
 * Date：2017/4/25
 * Time: 12:02
 * author: hyn
 * 商品列表
 */
public class GoodsListActivity extends Activity implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    private LinearLayout ll_back;//返回按钮
    private TextView tv_title;//标题
    private RecyclerView rv_goods;//商品列表
    private HttpManager httpManager;
    private GoodsListAdapter goodsListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String categoryId = null;//类目ID
    private String categoryName = "";//类目ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_goods_list);
        init();
    }

    private void init() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rv_goods = (RecyclerView) findViewById(R.id.rv_goods);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        httpManager = new HttpManager();
        categoryName = getIntent().getStringExtra("categoryName");
        categoryId = getIntent().getStringExtra("categoryId");
        if (categoryName != null && categoryName.toString().length() > 0) {
            tv_title.setText(categoryName);
        } else {
            tv_title.setText("商品列表");
        }
        if (categoryId != null && categoryId.toString().length() > 0) {
            getGoodsList("1");
        }

        linearLayoutManager = new GridLayoutManager(this, 2);
        rv_goods.setLayoutManager(linearLayoutManager);
        rv_goods.addItemDecoration(new DividerGridItemDecoration(this));
    }

    /**
     * 根据类目ID获取商品列表
     *
     * @param category_id
     */
    private void getGoodsList(String category_id) {
        httpManager.getGoodsList(new DialogLoadingView(this), category_id, "", 1, false, false, 1, 30, new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HYN", "商品列表：" + result);
                GoodsListInfo goodsListInfo = JSON.parseObject(result, GoodsListInfo.class);
                if (goodsListInfo.getStatus().toString().equals("ok")) {
                    List<GoodsListInfo.ResponseBean.GoodsBean> goodsBeanList = goodsListInfo.getResponse().getGoods();
                    goodsListAdapter = new GoodsListAdapter(R.layout.item_goods, goodsBeanList);
                    rv_goods.setAdapter(goodsListAdapter);
                    goodsListAdapter.setOnItemChildClickListener(GoodsListActivity.this);
                    goodsListAdapter.setOnItemClickListener(GoodsListActivity.this);
                }
            }

            @Override
            public void onFail(Throwable errorMsg) {

            }
        });
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsListInfo.ResponseBean.GoodsBean info = (GoodsListInfo.ResponseBean.GoodsBean) adapter.getItem(position);
//        Intent intent = new Intent(this, GoodsDetailsActivity.class);
//        intent.putExtra("ID", info.getId());
//        startActivity(intent);
        httpManager.getGoodsDetailsById(new DialogLoadingView(this), info.getId(), "", new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                Log.i("HYN", "详情：" + result);
                GoodsDetailsInfo goodsDetailsInfo = JSON.parseObject(result, GoodsDetailsInfo.class);
                ToastUtils.showToast("加入" + goodsDetailsInfo.getResponse().getName());
            }

            @Override
            public void onFail(Throwable errorMsg) {

            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsListInfo.ResponseBean.GoodsBean info = (GoodsListInfo.ResponseBean.GoodsBean) adapter.getItem(position);
        Intent intent = new Intent(this, GoodsDetailsActivity.class);
        intent.putExtra("GoodsID", info.getId());
        startActivity(intent);
    }
}
