package io.flowerpower.rent.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.flowerpower.rent.R;
import io.flowerpower.rent.model.apartment.SpecificAdvert;
import io.flowerpower.rent.model.clustering.AdvertPoint;
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
public class MapRentFragment extends BaseFragment implements OnMapReadyCallback, ClusterManager.OnClusterItemClickListener<AdvertPoint> {

    private final LatLng MINSK_LOCATION = new LatLng(53.9045, 27.5615);
    private final float DEFAULT_ZOOM = 11.0f;
    private ClusterManager<AdvertPoint> clusterManager;
    private GoogleMap googleMap;
    private Marker selectedMarker;
    private AdvertPoint selectedAdvertPoint;
    private SpecificAdvert specificAdvert;

    @BindView(R.id.map_view) MapView mapView;
    @BindColor(R.color.bgColor) int bgColor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate() called with: " + "savedInstanceState = [" + savedInstanceState + "]");
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

    private void loadPoints() {
        final Subscription subscription = OnlinerApi.getInstance()
                .getPoints(53.782397985652366, 27.385482788085934, 54.013417725383434, 27.739105224609375, 100, 300)
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
                        Timber.d("Next " + response.getAdvertPoints().size());
                        clusterManager.addItems(response.getAdvertPoints());
                        clusterManager.cluster();
                    }
                });
        unsubscribeOnStop(subscription);
    }

    private void loadSpecificAdvert(final long advertId) {
        final Subscription subscription = OnlinerApi.getInstance()
                .getSpecificAdvert(advertId)
                .observeOn(mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SpecificAdvert>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("onCompleted() called with: " + "");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("onError() called with: " + "e = [" + e + "]");
                    }

                    @Override
                    public void onNext(SpecificAdvert response) {
                        Timber.d("Next " + response);
                        specificAdvert = response;
                        selectedMarker.showInfoWindow();
                    }
                });
        unsubscribeOnStop(subscription);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        MapsInitializer.initialize(this.getActivity());
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MINSK_LOCATION)
                .zoom(DEFAULT_ZOOM)
                .build();
        clusterManager = new ClusterManager<>(getActivity(), this.googleMap);
       /* clusterManager.getMarkerCollection().setOnInfoWindowAdapter(new ApartmentsMarkerInfoAdapter());
        clusterManager.setOnClusterClickListener(MapRentFragment.this);
        clusterManager.setOnClusterInfoWindowClickListener(MapRentFragment.this);
        clusterManager.setOnClusterItemInfoWindowClickListener(MapRentFragment.this);*/
        //googleMap.clear();
        clusterManager.setOnClusterItemClickListener(this);
        this.googleMap.setOnCameraChangeListener(clusterManager);
        googleMap.setOnMarkerClickListener(clusterManager);
        this.googleMap.setInfoWindowAdapter(clusterManager.getMarkerManager());
        clusterManager.getMarkerCollection().setOnInfoWindowAdapter(new ApartmentsMarkerInfoAdapter());
        this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        loadPoints();
    }

    public MarkerOptions getMarker(List<Double> geometry, int drawableId) {
        return new MarkerOptions()
                .position(new LatLng(geometry.get(0), geometry.get(1)))
                .icon(BitmapDescriptorFactory.fromResource(drawableId));
    }

    @Override
    public boolean onClusterItemClick(AdvertPoint item) {
        Timber.d("onClusterItemClick() called with: " + "item = [" + item + "]");
        selectedAdvertPoint = item;
        return false;
    }

    public class ApartmentsMarkerInfoAdapter implements GoogleMap.InfoWindowAdapter {

        public ApartmentsMarkerInfoAdapter() {
            Timber.d("constructor() called with: " + "");
        }

        @Override
        public View getInfoWindow(final Marker marker) {
            if (specificAdvert != null) {
                return null;
            }
            selectedMarker = marker;
            Timber.d("getInfoWindow() called with: " + "marker = [" + marker + "]");
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.map_info_bubble, null);
            loadSpecificAdvert(selectedAdvertPoint.getId());
            return v;
        }

        @Override
        public View getInfoContents(Marker marker) {
            Timber.d("getInfoContents() called with: " + "marker = [" + marker + "]");
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.map_info_bubble, null);
            TextView tvTitle = ButterKnife.findById(v, R.id.tv_title);
            LinearLayout contentContainer = ButterKnife.findById(v, R.id.content_container);
            contentContainer.setVisibility(View.VISIBLE);
            FrameLayout progressContainer = ButterKnife.findById(v, R.id.progress_container);
            progressContainer.setVisibility(View.GONE);
            ImageView imgAdvertImage = ButterKnife.findById(v, R.id.img_image);

            Picasso.with(getActivity())
                    .load(specificAdvert.getPhoto())
                    .fit()
                    .placeholder(android.R.drawable.ic_btn_speak_now)
                    .into(imgAdvertImage);
            tvTitle.setText(specificAdvert.getCreatedAt() + "");
            //code for initializing view part
            return v;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            mapView.onDestroy();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
