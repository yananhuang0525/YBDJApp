package com.panku.ybdjapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.panku.pkBaseLibrary.util.FileHelp;
import com.panku.pkBaseLibrary.util.ToastUtils;
import com.panku.pkBaseLibrary.view.DialogLoadingView;
import com.panku.ybdjapp.R;
import com.panku.ybdjapp.adapter.CategoryAdapter;
import com.panku.ybdjapp.biz.CategoryBasesInfo;
import com.panku.ybdjapp.biz.DrugType;
import com.panku.ybdjapp.biz.GroupItem;
import com.panku.ybdjapp.fragment.HomeFragment;
import com.panku.ybdjapp.http.HttpManager;
import com.panku.ybdjapp.http.Interface.HttpCallBack;


import java.util.ArrayList;
import java.util.List;

/**
 * Date：2017/4/10
 * Time: 9:33
 * author: hyn
 * 商品类目
 */
public class CategoryActivity extends Activity {
    private LinearLayout ll_back;
    private EditText et_search;
    private RecyclerView rv;
    private CategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_category);
        initView();
        rv.setLayoutManager(new LinearLayoutManager(this));
//        Log.e("HYN", "More" + HomeFragment.categories);
        adapter = new CategoryAdapter(HomeFragment.categories);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DrugType drugType = (DrugType) adapter.getItem(position);
                Intent intent = new Intent(CategoryActivity.this, GoodsListActivity.class);
                intent.putExtra("categoryId", drugType.getId());
                intent.putExtra("categoryName", drugType.getTypeName());
                startActivity(intent);
            }
        });
//        expandAll();
    }

    private void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        et_search = (EditText) findViewById(R.id.et_search);
        rv = (RecyclerView) findViewById(R.id.rv);
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 展开所有菜单
     */
//    private void expandAll() {
//        for (int i = 0; i < list.size(); i++) {
//            adapter.expand(i + adapter.getHeaderLayoutCount(), false, false);
//        }
//    }
    private ArrayList<MultiItemEntity> getListData() {
        int[] group_logo_array = new int[]{R.mipmap.listico01, R.mipmap.listico02, R.mipmap.listico03,
                R.mipmap.listico04, R.mipmap.listico05, R.mipmap.listico06, R.mipmap.listico07,
                R.mipmap.listico08, R.mipmap.listico09, R.mipmap.listico10, R.mipmap.listico11,
                R.mipmap.listico12,};
        // 一级标签上的标题数据源
        String[] group_title_arry = new String[]{"宝宝常备", "清热解毒", "肠胃用药",
                "外伤常备", "营养保健", "感冒咳嗽", "电脑一族",
                "外出旅游", "五官用药", "高血压", "风湿骨伤", "家用电器"};

        // 子视图显示文字
        String[] child_text_array = new String[]{
                "退热", "清热解毒", "肠胃用药", "外伤常备", "营养保健", "感冒咳嗽", "电脑一族"
                , "外出旅游", "五官用药", "高血压", "风湿骨伤", "家用医疗器械"
        };
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < group_logo_array.length; i++) {
            GroupItem groupItem = new GroupItem();
            groupItem.setIcon(group_logo_array[i]);
            groupItem.setTitle(group_title_arry[i]);
            groupItem.setSubTitle("3种");
            for (int j = 0; j < 3; j++) {
                DrugType type = new DrugType();
                type.setTypeName(child_text_array[i]);
                groupItem.addSubItem(type);
            }
            res.add(groupItem);
        }
        return res;
    }

    public static List<MultiItemEntity> getCategoryListFor(String parent_cid, String office_id, int level) {
        final ArrayList<MultiItemEntity> categories = new ArrayList<>();
        CategoryBasesInfo categoryBasesInfo = JSON.parseObject(FileHelp.readAsSetFile("category.json"), CategoryBasesInfo.class);
        CategoryBasesInfo categoryBasesInfo1 = JSON.parseObject(FileHelp.readAsSetFile("category1.json"), CategoryBasesInfo.class);
        if (categoryBasesInfo.getStatus().equals("ok")) {
            for (int i = 0; i < categoryBasesInfo.getResponse().getCategorybases().size(); i++) {
                CategoryBasesInfo.ResponseBean.Category category = categoryBasesInfo.getResponse().getCategorybases().get(i);
                for (int j = 0; j < 2; j++) {
                    CategoryBasesInfo.ResponseBean.Category category1 = null;
                    DrugType drugType = null;
                    if (categoryBasesInfo1.getStatus().equals("ok")) {
                        category1 = categoryBasesInfo1.getResponse().getCategorybases().get(j);
                        drugType = new DrugType();
                        drugType.setTypeName(category1.getName());
                    }
                    category.addSubItem(drugType);
                }
                categories.add(category);
            }
        }
        return categories;
    }
}
