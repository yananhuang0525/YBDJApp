package com.panku.ybdjapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jude.rollviewpager.RollPagerView;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.RollAdapter;
import com.panku.ybdjapp.biz.GoodsDetailsInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;
import com.panku.ybdjapp.utils.ImageLoaderUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Date：2017/4/26
 * Time: 16:13
 * author: hyn
 */
@ContentView(R.layout.ac_goods_details)
public class GoodsDetailsActivity extends AppCompatActivity {
    @ViewInject(R.id.ll_back)
    private LinearLayout ll_back;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.rpv_img)
    private RollPagerView rpv_img;
    @ViewInject(R.id.tv_name)
    private TextView tv_name;//药名
    @ViewInject(R.id.tv_price)
    private TextView tv_price;//价格
    @ViewInject(R.id.iv_shop)
    private ImageView iv_shop;
    @ViewInject(R.id.tv_shop_name)
    private TextView tv_shop_name;//药店名
    @ViewInject(R.id.tv_shop_phone)
    private TextView tv_shop_phone;//药店联系方式
    @ViewInject(R.id.tv_shop_time)
    private TextView tv_shop_time;//营业时间
    @ViewInject(R.id.tv_shop_address)
    private TextView tv_shop_address;//药店地址
    @ViewInject(R.id.rg)
    private RadioGroup rg;
    @ViewInject(R.id.rb_collection)
    private RadioButton rb_collection;

    private HttpManager httpManager;
    private String goodsId;
    private RollAdapter rollAdapter;

    //    private List<String> img_url = new ArrayList<>();
    private String[] img_url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        init();
    }

    private void init() {
        tv_title.setText("商品详情");
        goodsId = getIntent().getStringExtra("GoodsID");
        httpManager = new HttpManager();
        if (goodsId != null && goodsId.length() > 0) {
            getGoodsInfo();
        }
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case R.id.rb_collection:
//
//                        break;
//                }
//            }
//        });

        rb_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb_collection.getText().toString().equals("收藏")) {
                    rb_collection.setChecked(true);
                    rb_collection.setText("取消收藏");
                } else if (rb_collection.getText().toString().equals("取消收藏")) {
                    rg.clearCheck();
                    rb_collection.setText("收藏");
                }
            }
        });
    }

    private void getGoodsInfo() {
        httpManager.getGoodsDetailsById(new DialogLoadingView(this), goodsId, "", new HttpCallBack() {
            @Override
            public void onSuccess(String result) {
                setData(result);
            }

            @Override
            public void onFail(Throwable errorMsg) {

            }
        });
    }

    private void setData(String result) {
        Log.i("HYN", "详情：" + result);
        GoodsDetailsInfo goodsDetailsInfo = JSON.parseObject(result, GoodsDetailsInfo.class);
//        ToastUtils.showToast("商品名称：" + goodsDetailsInfo.getResponse().getName());
        img_url = new String[goodsDetailsInfo.getResponse().getGoods_images().size()];
        for (int i = 0; i < goodsDetailsInfo.getResponse().getGoods_images().size(); i++) {
            img_url[i] = goodsDetailsInfo.getResponse().getGoods_images().get(i).getPicUrl();
        }
        rollAdapter = new RollAdapter(rpv_img, img_url);
        rpv_img.setAdapter(rollAdapter);
        tv_name.setText(goodsDetailsInfo.getResponse().getName());
        tv_price.setText("￥" + goodsDetailsInfo.getResponse().getShop_price());
        Log.i("HYN", "店铺图片：" + goodsDetailsInfo.getResponse().getOffice().getLogo());
        ImageLoaderUtil.loadNormalImg(iv_shop, goodsDetailsInfo.getResponse().getOffice().getLogo(), R.mipmap.ic_default);
        tv_shop_name.setText(goodsDetailsInfo.getResponse().getOffice().getName());
        tv_shop_phone.setText(goodsDetailsInfo.getResponse().getOffice().getPhone());
        tv_shop_time.setText(goodsDetailsInfo.getResponse().getOffice().getBusiness_start_time() + "--" + goodsDetailsInfo.getResponse().getOffice().getBusiness_end_time());
        tv_shop_address.setText(goodsDetailsInfo.getResponse().getOffice().getAddress());
        rb_collection.setChecked(goodsDetailsInfo.getResponse().is_collect());
        if (goodsDetailsInfo.getResponse().is_collect()) {
            rb_collection.setText("取消收藏");
        } else {
            rb_collection.setText("收藏");
        }
    }
}
