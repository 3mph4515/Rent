package io.flowerpower.rent.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.flowerpower.rent.R;
import io.flowerpower.rent.model.Address;
import io.flowerpower.rent.ui.AddressFilter;

/**
 * Created by Raman Branavitski on 6/13/16.
 */
public class AddressAdapter extends ArrayAdapter<Address> {

    private AddressFilter.Searchable searchCallback;
    private List<Address> items;

    public AddressAdapter(Context context, AddressFilter.Searchable callback) {
        super(context, 0, new Address[]{});
        searchCallback = callback;
        items = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Filter getFilter() {
        return new AddressFilter(this, searchCallback);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        TextView tv = (TextView) inflater.inflate(R.layout.item_dropdown, parent, false);
        tv.setText(getItem(position).getDescription());
        return tv;
    }

    @Override
    public Address getItem(int position) {
        return items.get(position);
    }

    public void updateItems(List<Address> newItems) {
        items = newItems;
        notifyDataSetChanged();
    }
}
