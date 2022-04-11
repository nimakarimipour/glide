package com.bumptech.glide.load.engine.bitmap_recycle;

import androidx.annotation.Nullable;

/**
 * Adapter for handling primitive int arrays.
 */
@SuppressWarnings("PMD.UseVarargs")
public final class IntegerArrayAdapter implements ArrayAdapterInterface<int[]> {

    private static final String TAG = "IntegerArrayPool";

    @Override
    public String getTag() {
        return TAG;
    }

    @Override
    public int getArrayLength(@Nullable int[] array) {
        return array.length;
    }

    @Override
    public int[] newArray(int length) {
        return new int[length];
    }

    @Override
    public int getElementSizeInBytes() {
        return 4;
    }
}
