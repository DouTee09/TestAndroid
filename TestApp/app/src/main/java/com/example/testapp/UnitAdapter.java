package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UnitAdapter extends ArrayAdapter<Unit> {
    private Context mContext;
    private int mResource;

    public UnitAdapter(Context context, int resource, List<Unit> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewUnitName = convertView.findViewById(R.id.tvTenDonVi);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Unit unit = getItem(position);
        if (unit != null) {
            viewHolder.textViewUnitName.setText(unit.getTenDonVi());
        }

        return convertView;
    }

    static class ViewHolder {
        TextView textViewUnitName;
    }
}
