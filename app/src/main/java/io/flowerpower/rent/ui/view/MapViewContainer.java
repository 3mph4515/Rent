package io.flowerpower.rent.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import io.flowerpower.rent.ui.behavior.MapFragmentBehavior;

/**
 * Created by Raman Branavitski on 6/10/16.
 */
@CoordinatorLayout.DefaultBehavior(MapFragmentBehavior.class)
public class MapViewContainer extends FrameLayout {

    public MapViewContainer(Context context) {
        super(context);
    }

    public MapViewContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MapViewContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MapViewContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
