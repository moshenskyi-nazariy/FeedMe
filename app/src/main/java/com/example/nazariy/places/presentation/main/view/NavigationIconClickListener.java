package com.example.nazariy.places.presentation.main.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.example.nazariy.places.R;
import com.example.nazariy.places.domain.utils.Action;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NavigationIconClickListener implements View.OnClickListener {
    private static final String TAG = "NavigationIconClickListener";

    private final Context context;
    private final View sheet;
    private final Interpolator interpolator;
    private final Drawable openIcon;
    private final Drawable closeIcon;
    private final int height;
    private final View backdropLayout;
    private boolean isBackdropShown = false;

    private AnimatorSet animatorSet = new AnimatorSet();

    public NavigationIconClickListener(@NonNull Context context, @NonNull View sheet,
                                       @NonNull View backdropLayout) {
        this(context, sheet, backdropLayout, null, null, null);
    }

    public NavigationIconClickListener(@NonNull Context context, @NonNull View sheet,
                                       @NonNull View backdropLayout,
                                       @Nullable Interpolator interpolator,
                                       @Nullable Drawable openIcon, @Nullable Drawable closeIcon) {
        this.context = context;
        this.sheet = sheet;
        this.backdropLayout = backdropLayout;
        this.interpolator = interpolator;
        this.openIcon = openIcon;
        this.closeIcon = closeIcon;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
    }

    @Override
    public void onClick(View view) {
        isBackdropShown = !isBackdropShown;

        // Cancel the existing animations
        animatorSet.removeAllListeners();
        animatorSet.end();
        animatorSet.cancel();

        updateIcons(view);

        final int translateY = height -
                context.getResources().getDimensionPixelSize(R.dimen.venue_grid_reveal_height);

        ObjectAnimator animator = ObjectAnimator.ofFloat(sheet, "translationY", isBackdropShown ?
                translateY : 0);
        animator.setDuration(500);
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animatorSet.play(animator);
        animator.start();
    }

    private void updateIcons(View view) {
        if (openIcon != null && closeIcon != null) {
            if (!(view instanceof ImageView)) {
                throw new IllegalArgumentException("updateIcons() must be called on an ImageView");
            }
            ImageView iconImageView = (ImageView) view;
            if (isBackdropShown) {
                animateIconChange(iconImageView, () -> {
                    if(closeIcon != null) {
                        (iconImageView).setImageDrawable(closeIcon);
                        backdropLayout.setVisibility(View.VISIBLE);
                    }
                });
            } else {
                animateIconChange(iconImageView, () -> {
                    if(closeIcon != null) {
                        (iconImageView).setImageDrawable(openIcon);
                        backdropLayout.setVisibility(View.GONE);
                    }
                });
            }
        }
    }

    private void animateIconChange(ImageView imageView, Action imageViewFunction) {
        ObjectAnimator showAnim = ObjectAnimator.ofFloat(imageView, "alpha", 0);
        showAnim.setDuration(500);
        showAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageViewFunction.execute();
            }
        });

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 1);
        fadeIn.setDuration(500);

        if (interpolator != null) {
            fadeIn.setInterpolator(new DecelerateInterpolator());
            showAnim.setInterpolator(new DecelerateInterpolator());
        }
        animatorSet.play(showAnim).before(fadeIn);
        animatorSet.start();
    }
}
