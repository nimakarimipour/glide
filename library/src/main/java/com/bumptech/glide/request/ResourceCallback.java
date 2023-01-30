package com.bumptech.glide.request;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import androidx.annotation.Nullable;

/**
 * A callback that listens for when a resource load completes successfully or fails due to an
 * exception.
 */
public interface ResourceCallback {

  /**
   * Called when a resource is successfully loaded.
   *
   * @param resource The loaded resource.
   */
  void onResourceReady(
      @Nullable Resource<?> resource, @Nullable DataSource dataSource, boolean isLoadedFromAlternateCacheKey);

  /**
   * Called when a resource fails to load successfully.
   *
   * @param e a non-null {@link GlideException}.
   */
  void onLoadFailed(@Nullable GlideException e);

  /** Returns the lock to use when notifying individual requests. */
  Object getLock();
}
