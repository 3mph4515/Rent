package io.flowerpower.rent.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import io.flowerpower.rent.R;

/**
 * Created by Raman Branavitski on 6/13/16.
 */
public class ShadowView extends View {

    private ShadowDirection shadowDirection;

    public ShadowView(Context context) {
        this(context, null);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ShadowView, 0, 0);
            try {
                shadowDirection = ShadowDirection.values()[ta.getInt(R.styleable.ShadowView_shadow_direction, 0)];
            } finally {
                ta.recycle();
            }
        }
        setViewBackground();
    }

    private void setViewBackground() {
        GradientDrawable viewBackground = new GradientDrawable(
                shadowDirection.gradientOrientation, new int[]{0x00000000, 0x30000000});
        setBackground(viewBackground);
    }

    private enum ShadowDirection {
        LEFT_RIGHT(GradientDrawable.Orientation.LEFT_RIGHT),
        RIGHT_LEFT(GradientDrawable.Orientation.RIGHT_LEFT),
        TOP_BOTTOM(GradientDrawable.Orientation.TOP_BOTTOM),
        BOTTOM_TOP(GradientDrawable.Orientation.BOTTOM_TOP);

        private GradientDrawable.Orientation gradientOrientation;

        ShadowDirection(GradientDrawable.Orientation orientation) {
            this.gradientOrientation = orientation;
        }

        public GradientDrawable.Orientation getGradientOrientation() {
            return gradientOrientation;
        }
    }

    @Override
    public void setBackground(Drawable drawable) {
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion >= 15) {
            super.setBackground(drawable);
        } else {
            super.setBackgroundDrawable(drawable);
        }
    }


}
