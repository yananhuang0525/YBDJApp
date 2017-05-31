package com.panku.ybdjapp.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.panku.pkBaseLibrary.util.JsonMapUtil;
import com.panku.pkBaseLibrary.util.SharedPreferencesUtil;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.RollAdapter;
import com.panku.ybdjapp.adapter.SpecsAdapter;
import com.panku.ybdjapp.biz.GoodsDetailsInfo;
import com.panku.ybdjapp.core.UserInfo;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;
import com.panku.ybdjapp.utils.ImageLoaderUtil;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Date：2017/4/26
 * Time: 16:13
 * author: hyn
 * 商品详情界面
 */
public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout ll_back;
    private TextView tv_title;
    private RollPagerView rpv_img;
    private TextView tv_name;//药名
    private TextView tv_price;//价格
    private ImageView iv_shop;
    private TextView tv_shop_name;//药店名
    private TextView tv_shop_phone;//药店联系方式
    private TextView tv_shop_time;//营业时间
    private TextView tv_shop_address;//药店地址
    private RadioGroup rg;
    private RadioButton rb_collection;
    private TextView tv_add_car;

    private LinearLayoutManager rv_specsLinearLayoutManager;
    private HttpManager httpManager;
    private String goodsId;
    private RollAdapter rollAdapter;
    private SpecsAdapter specsAdapter;
    private GoodsDetailsInfo.ResponseBean responseBean;
    private PopupWindow popupWindow;
    //    private List<String> img_url = new ArrayList<>();

    private List<GoodsDetailsInfo.ResponseBean.Spec> specList = new ArrayList<>();
    private String[] img_url;
    private int num = 1;//商品数量 默认1
    private String keyName = null;
    private TextView tv_num;
    private TextView tv_confirm;

    private UserInfo userInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_goods_details);
        initView();
        init();
    }

    private void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        rpv_img = (RollPagerView) findViewById(R.id.rpv_img);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_price = (TextView) findViewById(R.id.tv_price);
        iv_shop = (ImageView) findViewById(R.id.iv_shop);
        tv_shop_name = (TextView) findViewById(R.id.tv_shop_name);
        tv_shop_phone = (TextView) findViewById(R.id.tv_shop_phone);
        tv_shop_time = (TextView) findViewById(R.id.tv_shop_time);
        tv_shop_address = (TextView) findViewById(R.id.tv_shop_address);
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_collection = (RadioButton) findViewById(R.id.rb_collection);
        tv_add_car = (TextView) findViewById(R.id.tv_add_car);
        ll_back.setOnClickListener(this);
        tv_add_car.setOnClickListener(this);
    }

    private void init() {
        tv_title.setText("商品详情");
        goodsId = getIntent().getStringExtra("GoodsID");
        httpManager = new HttpManager();
        userInfo = SharedPreferencesUtil.getMobileLoginInfo(this, UserInfo.class);
        if (goodsId != null && goodsId.length() > 0) {
            getGoodsInfo();
        }
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
        responseBean = goodsDetailsInfo.getResponse();
        img_url = new String[responseBean.getGoods_images().size()];
        for (int i = 0; i < responseBean.getGoods_images().size(); i++) {
            img_url[i] = responseBean.getGoods_images().get(i).getPicUrl();
        }
        rollAdapter = new RollAdapter(rpv_img, img_url);
        rpv_img.setAdapter(rollAdapter);
        tv_name.setText(responseBean.getName());
        tv_price.setText("￥ " + responseBean.getShop_price());
        Log.i("HYN", "店铺图片：" + responseBean.getOffice().getLogo());
        ImageLoaderUtil.loadNormalImg(iv_shop, responseBean.getOffice().getLogo(), R.mipmap.ic_default);
        tv_shop_name.setText(responseBean.getOffice().getName());
        tv_shop_phone.setText(responseBean.getOffice().getPhone());
        tv_shop_time.setText(responseBean.getOffice().getBusiness_start_time() + "--" + responseBean.getOffice().getBusiness_end_time());
        tv_shop_address.setText(responseBean.getOffice().getAddress());
        rb_collection.setChecked(responseBean.is_collect());
        if (responseBean.is_collect()) {
            rb_collection.setText("取消收藏");
        } else {
            rb_collection.setText("收藏");
        }
        specList = responseBean.getSpecs();
    }

    private void initPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_sel_layout, null);
        ImageView iv_img = (ImageView) view.findViewById(R.id.iv_img);
        final TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
        final TextView tv_stock = (TextView) view.findViewById(R.id.tv_stock);
        final TextView tv_specs = (TextView) view.findViewById(R.id.tv_specs);
        TextView tv_add = (TextView) view.findViewById(R.id.tv_add);
        TextView tv_reduce = (TextView) view.findViewById(R.id.tv_reduce);
        tv_num = (TextView) view.findViewById(R.id.tv_num);
        tv_confirm = (TextView) view.findViewById(R.id.tv_confirm);
        LinearLayout ll_close = (LinearLayout) view.findViewById(R.id.ll_close);
        RecyclerView rv_specs = (RecyclerView) view.findViewById(R.id.rv_specs);
        tv_add.setOnClickListener(new OnPopupWindowBtnListener());
        tv_reduce.setOnClickListener(new OnPopupWindowBtnListener());
        ll_close.setOnClickListener(new OnPopupWindowBtnListener());
        tv_confirm.setOnClickListener(new OnPopupWindowBtnListener());

        ImageLoaderUtil.loadNormalImg(iv_img, responseBean.getTitle_img(), R.mipmap.tu06);
        tv_price.setText("￥ " + responseBean.getShop_price());
        tv_stock.setText("库存" + responseBean.getTotal_store_count() + "件");
        rv_specsLinearLayoutManager = new LinearLayoutManager(this);
        rv_specs.setLayoutManager(rv_specsLinearLayoutManager);
        specsAdapter = new SpecsAdapter(R.layout.item_specs, responseBean.getSpecs());
        rv_specs.setAdapter(specsAdapter);
        final LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < responseBean.getSpecs().size(); i++) {
            hashMap.put(responseBean.getSpecs().get(i).getName(), "");
        }
        specsAdapter.selectSpecs(new SpecsAdapter.SelectSetSpecs() {
            @Override
            public void setSpecs(String name, String specs) {
                hashMap.put(name, specs);
                String keyname = "";
                Set<?> keySet = hashMap.keySet();
                for (Object key : keySet) {
                    if (hashMap.get(key).length() == 0) {
                        if (!key.equals(name))
                            keyname = keyname + " " + key;
                    }

                }
                if (keyname.length() > 0) {
                    tv_specs.setText("请选择" + keyname);
                } else {
                    List<GoodsDetailsInfo.ResponseBean.Stock> stock_list = responseBean.getStocks();
                    if (stock_list != null) {
                        for (int i = 0; i < stock_list.size(); i++) {
                            tv_specs.setText(JsonMapUtil.simpleMapToStr(hashMap));
                            String str = JsonMapUtil.simpleMapToStr(hashMap);
                            String str1 = stock_list.get(i).getKeyname();
                            if (str.equals(str1)) {
                                keyName = stock_list.get(i).getItems();
                                Log.e("HYN", str + "==" + str1);
                                tv_price.setText("￥ " + stock_list.get(i).getPrice());
                                tv_stock.setText("库存" + stock_list.get(i).getStore_count() + "件");
                            }
                        }
                    }
                }
            }
        });

        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);// 设置视图
        popupWindow.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT); // 设置弹出窗体的宽和高
        popupWindow.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);// 设置弹出窗体可点击
        ColorDrawable dw = new ColorDrawable(0xb0000000);// 实例化一个ColorDrawable颜色为半透明
        popupWindow.setBackgroundDrawable(dw);// 设置弹出窗体的背景
        popupWindow.setAnimationStyle(R.style.AnimBottom);// 设置弹出窗体显示时的动画，从底部向上弹出
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }


    public static String listToString(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        result.append("\"");
        boolean first = true;
        //第一个前面不拼接","
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        result.append("\"");
        return result.toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_add_car:
                initPopupWindow();
                break;
        }
    }

    private class OnPopupWindowBtnListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_close:
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    }
                    break;
                case R.id.tv_add:
                    num++;
                    tv_num.setText(num + "");
                    break;
                case R.id.tv_reduce:
                    if (num > 1) {
                        num--;
                    }
                    tv_num.setText(num + "");
                    break;
                case R.id.tv_confirm:
                    addToCars();
                    break;
            }
        }
    }

    private void addToCars() {
        if (keyName != null) {
            if (userInfo != null) {
                httpManager.cars_create(new DialogLoadingView(this), userInfo.getId(), responseBean.getId(), num, keyName, new HttpCallBack() {
                    @Override
                    public void onSuccess(String result) {
                        Log.i("HYN", "添加购物车：" + result);
                    }

                    @Override
                    public void onFail(Throwable errorMsg) {
                        Log.i("HYN", "添加购物车失败：" + errorMsg);
                    }
                });
            }
        } else {
            ToastUtils.showToast("请选择商品规格");
        }

    }
}
