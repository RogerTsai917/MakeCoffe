package com.roger.makecoffee.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class KnowledgeItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    private int mMiddleSpace;

    public KnowledgeItemDecoration(int space) {
        mSpace = space;
        mMiddleSpace = space / 2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position == 0 || position == 1) {
            outRect.top = mSpace;
        }
        outRect.bottom = mSpace;

        if ((position % 2) == 0) {
            outRect.left = mSpace;
            outRect.right = mMiddleSpace;
        } else {
            outRect.left = mMiddleSpace;
            outRect.right = mSpace;
        }
    }
}
