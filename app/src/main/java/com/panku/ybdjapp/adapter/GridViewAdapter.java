package com.panku.ybdjapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.panku.ybdjapp.R;
import com.panku.ybdjapp.biz.ItemInfo;

import java.util.List;

/**
 * author： hyn
 * e-mail: hyn_0525@sina.com
 * Date: 2017/4/6
 * Time: 10:21
 */
public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<ItemInfo> data_list;

    public GridViewAdapter(Context context, List<ItemInfo> data_list) {
        this.context = context;
        this.data_list = data_list;
    }

    @Override
    public int getCount() {
        return data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return data_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.iv_item);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.iv.setImageResource(data_list.get(position).getIcon());
        viewHolder.tv.setText(data_list.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
