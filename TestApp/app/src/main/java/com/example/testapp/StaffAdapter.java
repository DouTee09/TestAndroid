package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class StaffAdapter extends ArrayAdapter<Staff> {
    private Context context;
    private List<Staff> itemList;
    public StaffAdapter(@NonNull Context context, ArrayList<Staff> itemList) {
        super(context, R.layout.item_listview, itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = convertView.findViewById(R.id.tvTenNhanVienItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Staff currentItem = itemList.get(position);
        viewHolder.tv_name.setText(currentItem.getTenNhanVien());

        return convertView;
    }
    static class ViewHolder {
        TextView tv_name;
    }
}
