package io.flowerpower.rent.ui;

import android.os.Handler;
import android.os.Looper;
import android.widget.Filter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.flowerpower.rent.model.Address;
import io.flowerpower.rent.network.GooglePlacesApi;
import io.flowerpower.rent.ui.adapter.AddressAdapter;

/**
 * Created by Raman Branavitski on 6/13/16.
 */
public class AddressFilter extends Filter {


    private Handler mainThreadHandler;
    private AddressAdapter adapter;
    private Searchable searchCallback;

    public AddressFilter(AddressAdapter adapter, Searchable searchCallback) {
        super();
        this.adapter = adapter;
        this.searchCallback = searchCallback;
        mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        final FilterResults results = new FilterResults();

        if (constraint.length() > 3) {
            if (searchCallback != null) {
                mainThreadHandler.post(() -> searchCallback.onSearchStarted());

            }

            GooglePlacesApi.getInstance()
                    .getAutocompleteAddress(constraint.toString())
                    .debounce(1000, TimeUnit.MILLISECONDS)
                    .subscribe(
                            autoCompleteAddressResponse -> {
                                results.values = autoCompleteAddressResponse.getPredictions();
                                results.count = autoCompleteAddressResponse.getPredictions().size();
                            },
                            error -> {
                                //do nothing
                            },
                            () -> {
                                if (searchCallback != null) {
                                    mainThreadHandler.post(() -> searchCallback.onSearchCompleted());

                                }
                            });
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.updateItems((List<Address>) results.values);
    }

    public interface Searchable {
        void onSearchStarted();

        void onSearchCompleted();
    }
}
