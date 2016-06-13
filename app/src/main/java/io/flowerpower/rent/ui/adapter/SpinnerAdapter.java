package io.flowerpower.rent.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.flowerpower.rent.R;

/**
 * Created by Raman Branavitski on 6/10/16.
 */
public class SpinnerAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<?> items;

    public SpinnerAdapter(Context context, List<?> items) {
        layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        TextView v = (TextView) layoutInflater.inflate(R.layout.item_spinner, parent, false);
        v.setText(getItem(position).toString());
        return v;
    }


    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        TextView v = (TextView) layoutInflater.inflate(R.layout.item_dropdown, parent, false);
        v.setText(getItem(position).toString());
        return v;
    }
}
