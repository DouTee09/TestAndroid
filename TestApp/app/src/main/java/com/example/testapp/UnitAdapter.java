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
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(mResource, null);
        }

        Unit unit = getItem(position);
        if (unit != null) {
            TextView textViewUnitName = view.findViewById(R.id.tvTenDonVi);
            if (textViewUnitName != null) {
                textViewUnitName.setText(unit.getTenDonVi());
            }
        }

        return view;
    }
}
