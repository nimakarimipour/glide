package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Key;
import java.security.MessageDigest;
import androidx.annotation.Nullable;
import com.bumptech.glide.NullUnmarked;

/** A cache key for original source data + any requested signature. */
final class DataCacheKey implements Key {

  @Nullable private final Key sourceKey;
  @Nullable private final Key signature;

  DataCacheKey(@Nullable Key sourceKey, @Nullable Key signature) {
    this.sourceKey = sourceKey;
    this.signature = signature;
  }

  @Nullable Key getSourceKey() {
    return sourceKey;
  }

  @NullUnmarked @Override
  public boolean equals(@Nullable Object o) {
    if (o instanceof DataCacheKey) {
      DataCacheKey other = (DataCacheKey) o;
      return sourceKey.equals(other.sourceKey) && signature.equals(other.signature);
    }
    return false;
  }

  @NullUnmarked @Override
  public int hashCode() {
    int result = sourceKey.hashCode();
    result = 31 * result + signature.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "DataCacheKey{" + "sourceKey=" + sourceKey + ", signature=" + signature + '}';
  }

  @NullUnmarked @Override
  public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    sourceKey.updateDiskCacheKey(messageDigest);
    signature.updateDiskCacheKey(messageDigest);
  }
}
