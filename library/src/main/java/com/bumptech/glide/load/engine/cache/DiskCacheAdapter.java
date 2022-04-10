package com.bumptech.glide.load.engine.cache;

import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;
import java.io.File;

/**
 * A simple class that returns null for all gets and ignores all writes.
 */
public class DiskCacheAdapter implements DiskCache {

    @Override
    @Nullable
    public File get(Key key) {
        // no op, default for overriders
        return null;
    }

    @Override
    public void put(Key key, Writer writer) {
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

    /**
     * Default factory for {@link DiskCacheAdapter}.
     */
    public static final class Factory implements DiskCache.Factory {

        @Override
        public DiskCache build() {
            return new DiskCacheAdapter();
        }
    }
}
