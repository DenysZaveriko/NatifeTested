package com.example.natifetested.recycleservice;

import android.content.Intent;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.imagepipeline.nativecode.DalvikPurgeableDecoder;

public class SpaceItemDecoration  extends RecyclerView.ItemDecoration{
    private int space;
    public SpaceItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.left = space;
        outRect.bottom = space;
        outRect.right = space;

        if(parent.getChildLayoutPosition(view)==0){
            outRect.top=space;
        }else {
            outRect.top=0;
        }
    }


}
