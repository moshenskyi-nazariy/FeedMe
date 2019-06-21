package com.example.nazariy.places.presentation.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

public class CustomDividerItemDecoration extends DividerItemDecoration {
    private static final int PADDING_PX = 16;
    private final int padding;
    private final Drawable divider;

    /**
     * Custom divider will be used
     */
    public CustomDividerItemDecoration(Context context, int orientation, Drawable divider, float density) {
        super(context, orientation);
        padding = (int) (density * PADDING_PX);

        this.divider = divider;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int right = parent.getWidth() - padding;

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(padding, top, right, bottom);
            divider.draw(c);
        }
    }
}