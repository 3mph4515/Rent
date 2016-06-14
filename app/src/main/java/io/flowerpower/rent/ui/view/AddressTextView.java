package io.flowerpower.rent.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import io.flowerpower.rent.R;
import io.flowerpower.rent.ui.AddressFilter;
import io.flowerpower.rent.ui.adapter.AddressAdapter;

/**
 * Created by Raman Branavitski on 6/13/16.
 */
public class AddressTextView extends LinearLayout implements AddressFilter.Searchable {

    private AddressAdapter adapter;
    private AutoCompleteTextView tvAddress;
    private ProgressBar progress;

    public AddressTextView(Context context) {
        this(context, null);
    }

    public AddressTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddressTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AddressTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.tv_address, this, true);
        tvAddress = (AutoCompleteTextView) findViewById(R.id.tv_autocomplete);
        progress = (ProgressBar) findViewById(R.id.progress);
        setupAddressText();
    }

    private void setupAddressText() {
        adapter = new AddressAdapter(getContext(), this);
        tvAddress.setAdapter(adapter);
    }

    @Override
    public void onSearchStarted() {
        if (progress.getVisibility() != VISIBLE) {
            progress.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onSearchCompleted() {
        if (progress.getVisibility() != GONE) {
            progress.setVisibility(GONE);
        }
    }
}
