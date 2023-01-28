package com.bumptech.glide.request.target;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.annotation.Nullable;

/**
 * Efficiently displays multiple Drawables loaded serially into a single {@link android.view.View}.
 */
// Public API.
@SuppressWarnings("unused")
public class DrawableThumbnailImageViewTarget extends ThumbnailImageViewTarget<Drawable> {
  public DrawableThumbnailImageViewTarget(ImageView view) {
    super(view);
  }

  /**
   * @deprecated Use {@link #waitForLayout()} instead.
   */
  @Deprecated
  @SuppressWarnings("deprecation")
  public DrawableThumbnailImageViewTarget(ImageView view, boolean waitForLayout) {
    super(view, waitForLayout);
  }

  @Nullable @Override
  protected Drawable getDrawable(@Nullable Drawable resource) {
    return resource;
  }
}
