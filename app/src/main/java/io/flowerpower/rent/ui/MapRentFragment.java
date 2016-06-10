package io.flowerpower.rent.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import butterknife.BindView;
import io.flowerpower.rent.R;
import io.flowerpower.rent.model.clustering.Feature;
import io.flowerpower.rent.model.clustering.PointsResponse;
import io.flowerpower.rent.network.OnlinerApi;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;

/**
 * Created by Andrew Kuksov on 6/7/16.
 */
public class MapRentFragment extends BaseFragment implements OnMapReadyCallback {

    private final LatLng MINSK_LOCATION = new LatLng(53.9045, 27.5615);
    private final float DEFAULT_ZOOM = 11.0f;

    private GoogleMap googleMap;
    @BindView(R.id.map_view) MapView mapView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");

        final Subscription subscription = OnlinerApi.getInstance()
                .getPoints(53.782397985652366, 27.385482788085934, 54.013417725383434, 27.739105224609375)
                .observeOn(mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<PointsResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("onCompleted() called with: " + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("onError() called with: " + "e = [" + e + "]");
                    }

                    @Override
                    public void onNext(PointsResponse response) {
                        Timber.d("Next " + response.getFeatures().size());
                        for (Feature feature : response.getFeatures()) {
                            googleMap.addMarker(getMarker(feature.getGeometry().getCoordinates(), 1));
                        }
                    }
                });
        unsubscribeOnStop(subscription);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = (MapView) view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapView.onResume();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MINSK_LOCATION)
                .zoom(DEFAULT_ZOOM)
                .build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public MarkerOptions getMarker(List<Double> geometry, int drawableId) {
        return new MarkerOptions()
                .position(new LatLng(geometry.get(0), geometry.get(1)))
                .icon(BitmapDescriptorFactory.fromResource(drawableId));
    }
}
