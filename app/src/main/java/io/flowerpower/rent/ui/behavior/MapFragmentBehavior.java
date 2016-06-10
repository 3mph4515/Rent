package io.flowerpower.rent.ui.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import io.flowerpower.rent.R;
import io.flowerpower.rent.ui.view.MapViewContainer;

/**
 * Created by Raman Branavitski on 6/10/16.
 */
public class MapFragmentBehavior extends CoordinatorLayout.Behavior<MapViewContainer> {

    private final float PARALLAX_FACTOR = 1.9f;
    private float dependencyInitialTop = -1;

    public MapFragmentBehavior() {
    }

    public MapFragmentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, MapViewContainer child, View dependency) {
        return dependency.getId() == R.id.filters_container;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, MapViewContainer child, View dependency) {
        float translationY = 0;
        if (dependencyInitialTop == -1) {
            dependencyInitialTop = dependency.getTop();
        } else {
            translationY = (dependency.getTop() - dependencyInitialTop) / PARALLAX_FACTOR;
        }
        child.setTranslationY(translationY);
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
