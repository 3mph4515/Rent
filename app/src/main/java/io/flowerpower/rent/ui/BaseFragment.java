package io.flowerpower.rent.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Andrew Kuksov on 6/7/16.
 */
public abstract class BaseFragment extends Fragment {

    private final CompositeSubscription compositeSubscriptionForOnStop = new CompositeSubscription();
    private Unbinder unbinder;

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void unsubscribeOnStop(Subscription subscription) {
        compositeSubscriptionForOnStop.add(subscription);
    }

    public static String getFragmentTagForClass(Class<? extends Fragment> clazz) {
        return clazz.getCanonicalName();
    }

    private void bindViews(final View view) {
        unbinder = ButterKnife.bind(this, view);
    }

}
