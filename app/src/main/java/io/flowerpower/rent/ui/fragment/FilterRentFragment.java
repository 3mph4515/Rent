package io.flowerpower.rent.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Spinner;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.flowerpower.rent.R;
import io.flowerpower.rent.ui.adapter.SpinnerAdapter;
import io.flowerpower.rent.ui.view.AddressTextView;

/**
 * Created by Raman Branavitski on 6/10/16.
 */
public class FilterRentFragment extends BaseFragment {

    @BindView(R.id.tv_price) TextView tvPrice;
    @BindView(R.id.rnb_price) RangeBar priceRangeBar;
    @BindView(R.id.spn_metro) Spinner metroSpinner;
    @BindView(R.id.tv_address) AddressTextView tvAddress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupMetroSpinner();
        setupPriceRangeBar();
        updatePriceLabel();

    }

    private void setupMetroSpinner() {
        List<String> items = Arrays.asList(getResources().getStringArray(R.array.metro_type));
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), items);
        metroSpinner.setAdapter(adapter);
        metroSpinner.setSelection(adapter.getCount());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            metroSpinner.setDropDownWidth(metroSpinner.getWidth());
            metroSpinner.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    metroSpinner.setDropDownWidth(metroSpinner.getWidth());
                    metroSpinner.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }

    private void setupPriceRangeBar() {
        priceRangeBar.setDrawTicks(false);
        priceRangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                updatePriceLabel();
            }
        });
    }

    private void updatePriceLabel() {
        int from = Integer.parseInt(priceRangeBar.getLeftPinValue());
        int to = Integer.parseInt(priceRangeBar.getRightPinValue());
        tvPrice.setText(String.format(getString(R.string.price_label), from, to));
    }
}
