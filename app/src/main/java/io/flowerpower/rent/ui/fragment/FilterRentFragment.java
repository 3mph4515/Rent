package io.flowerpower.rent.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import io.flowerpower.rent.R;
import io.flowerpower.rent.ui.adapter.SpinnerAdapter;
import timber.log.Timber;

/**
 * Created by Raman Branavitski on 6/10/16.
 */
public class FilterRentFragment extends BaseFragment {

    @BindView(R.id.spn_metro) Spinner metroSpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filters, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<String> items = Arrays.asList(getResources().getStringArray(R.array.metro_type));
        SpinnerAdapter adapter = new SpinnerAdapter(getContext(), items);
        metroSpinner.setAdapter(adapter);
    }
}
