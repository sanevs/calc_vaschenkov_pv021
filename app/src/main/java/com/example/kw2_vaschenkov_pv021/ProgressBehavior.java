package com.example.kw2_vaschenkov_pv021;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import java.util.List;

public class ProgressBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private int firstVisibleItem = 0;
    private int totalItems = 0;

    public ProgressBehavior()
    {
    }

    public ProgressBehavior(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull V child, @NonNull View dependency) {
        return dependency instanceof ListView;
    }

    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull V child, @NonNull View dependency) {
        ListView lv = (ListView) dependency;
        this.totalItems = lv.getAdapter().getCount();
        this.firstVisibleItem = lv.getFirstVisiblePosition();
        CoordinatorLayout.LayoutParams LP =
                (CoordinatorLayout.LayoutParams) child.
                        getLayoutParams();
        LP.width = this.calculateWidth(
                this.firstVisibleItem, this.totalItems,
                parent.getWidth());
        child.setLayoutParams(LP);
        return true;
    }
    private int calculateWidth(int curValue,
                               int maxValue, int widgetWidth)
    {
        int width = (curValue * widgetWidth) / maxValue;
        if (width < 4) width = 4;
        return width;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return directTargetChild instanceof ListView;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        CoordinatorLayout.LayoutParams LP =
                (CoordinatorLayout.LayoutParams) child.
                        getLayoutParams();
        this.firstVisibleItem += dyConsumed;
        LP.width = this.calculateWidth(this.
                        firstVisibleItem,
                this.totalItems, coordinatorLayout.
                        getWidth());
        child.setLayoutParams(LP);
    }
}
