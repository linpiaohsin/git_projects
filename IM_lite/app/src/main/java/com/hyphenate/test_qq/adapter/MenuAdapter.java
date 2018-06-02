package com.hyphenate.test_qq.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.hyphenate.test_qq.R;
import com.hyphenate.test_qq.ui.MainActivity;
import com.hyphenate.test_qq.view.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anyway on 2016/2/2.
 */
public class MenuAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public MenuAdapter(Context context,List list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        String item = list.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            viewHolder.tv_item = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_item.setText(item);
        

        return convertView;
    }

    static class ViewHolder {
        TextView tv_item;
    }
}
