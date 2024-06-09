package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StaffAdapter extends ArrayAdapter<Staff> {
    private Context mContext;
    private int mResource;

    public StaffAdapter(Context context, int resource, List<Staff> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(mResource, null);
        }

        Staff staff = getItem(position);
        if (staff != null) {
            TextView textViewName = view.findViewById(R.id.tvTenNhanVienItem);
            if (textViewName != null) {
                textViewName.setText(staff.getTenNhanVien());
            }
        }

        return view;
    }
}
