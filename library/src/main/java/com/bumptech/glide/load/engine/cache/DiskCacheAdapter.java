package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import java.io.File;
import androidx.annotation.Nullable;

/** A simple class that returns null for all gets and ignores all writes. */
public class DiskCacheAdapter implements DiskCache {
  @Nullable @Override
  public File get(Key key) {
    // no op, default for overriders
    return null;
  }

  @Override
  public void put(@Nullable Key key, Writer writer) {
    // no op, default for overriders
  }

  @Override
  public void delete(Key key) {
    // no op, default for overriders
  }

  @Override
  public void clear() {
    // no op, default for overriders
  }

  /** Default factory for {@link DiskCacheAdapter}. */
  public static final class Factory implements DiskCache.Factory {
    @Override
    public DiskCache build() {
      return new DiskCacheAdapter();
    }
  }
}
