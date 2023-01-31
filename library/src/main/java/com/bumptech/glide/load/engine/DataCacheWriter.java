package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;
import androidx.annotation.Nullable;
import com.bumptech.glide.NullUnmarked;

/**
 * Writes original source data or downsampled/transformed resource data to cache using the provided
 * {@link com.bumptech.glide.load.Encoder} or {@link com.bumptech.glide.load.ResourceEncoder} and
 * the given data or {@link com.bumptech.glide.load.engine.Resource}.
 *
 * @param <DataType> The type of data that will be encoded (InputStream, ByteBuffer,
 *     Resource<Bitmap> etc).
 */
class DataCacheWriter<DataType> implements DiskCache.Writer {
  @Nullable private final Encoder<DataType> encoder;
  @Nullable private final DataType data;
  @Nullable private final Options options;

  DataCacheWriter(@Nullable Encoder<DataType> encoder, @Nullable DataType data, @Nullable Options options) {
    this.encoder = encoder;
    this.data = data;
    this.options = options;
  }

  @NullUnmarked @Override
  public boolean write(@NonNull File file) {
    return encoder.encode(data, file, options);
  }
}
