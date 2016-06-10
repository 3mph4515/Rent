package io.flowerpower.rent.ui;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import io.flowerpower.rent.R;


public class MainActivity extends BaseActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private FragmentManager fragmentManager;
    private MapRentFragment mapFragment;
    private FiltersRentFragment filtersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fragmentManager = getSupportFragmentManager();
        mapFragment = getMapFragment();
        filtersFragment = getFiltersFragment();
        replaceFragment(R.id.map_container, mapFragment);
        replaceFragment(R.id.filters_container, filtersFragment);
    }

    private void initViews() {
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.filters_container));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.d("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.d("Bottom Sheet Behaviour", "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.d("Bottom Sheet Behaviour", "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.d("Bottom Sheet Behaviour", "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.d("Bottom Sheet Behaviour", "STATE_SETTLING");
                        break;
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });
    }

    private MapRentFragment getMapFragment() {
        mapFragment = (MapRentFragment) fragmentManager.findFragmentByTag(MapRentFragment.getFragmentTagForClass(MapRentFragment.class));
        if (mapFragment == null) {
            mapFragment = new MapRentFragment();
        }
        return mapFragment;
    }

    private FiltersRentFragment getFiltersFragment() {
        filtersFragment = (FiltersRentFragment) fragmentManager.findFragmentByTag(BaseFragment.getFragmentTagForClass(FiltersRentFragment.class));
        if (filtersFragment == null) {
            filtersFragment = new FiltersRentFragment();
        }
        return filtersFragment;
    }

}
