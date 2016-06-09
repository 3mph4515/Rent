package io.flowerpower.rent.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Andrew Kuksov on 6/7/16.
 */
public abstract class BaseFragment extends Fragment {

    private final CompositeSubscription compositeSubscriptionForOnStop = new CompositeSubscription();

    protected void unsubscribeOnStop(Subscription subscription) {
        compositeSubscriptionForOnStop.add(subscription);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViews(view);
    }

    @Override
    public void onStop() {
        compositeSubscriptionForOnStop.clear();
        super.onStop();
    }

    private void bindViews(final View view) {
        ButterKnife.bind(this, view);
    }

}
