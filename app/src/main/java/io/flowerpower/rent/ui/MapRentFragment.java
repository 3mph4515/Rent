package io.flowerpower.rent.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.flowerpower.rent.model.ApartmentsResponse;
import io.flowerpower.rent.network.OnlinerApi;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Created by Andrew Kuksov on 6/7/16.
 */
public class MapRentFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
        final Subscription subscription = OnlinerApi.getInstance()
                .getApartments(53.782397985652366, 27.385482788085934, 54.013417725383434, 27.739105224609375)
                .observeOn(mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ApartmentsResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("onCompleted() called with: " + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("onError() called with: " + "e = [" + e + "]");
                    }

                    @Override
                    public void onNext(ApartmentsResponse response) {
                        Timber.d("Next " + response.getApartments().size());
                    }
                });
        unsubscribeOnStop(subscription);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
