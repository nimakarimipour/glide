package com.bumptech.glide.load.engine.bitmap_recycle;
import androidx.annotation.Nullable;
import org.jspecify.annotations.NullUnmarked;

/** Adapter for handling primitive byte arrays. */
@SuppressWarnings("PMD.UseVarargs")
public final class ByteArrayAdapter implements ArrayAdapterInterface<byte[]> {
  private static final String TAG = "ByteArrayPool";

  @Override
  public String getTag() {
    return TAG;
  }

  @NullUnmarked @Override
  public int getArrayLength(@Nullable byte[] array) {
    return array.length;
  }

  @Override
  public byte[] newArray(int length) {
    return new byte[length];
  }

  @Override
  public int getElementSizeInBytes() {
    return 1;
  }
}
