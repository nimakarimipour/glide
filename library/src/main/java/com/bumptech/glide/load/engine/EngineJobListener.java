package com.bumptech.glide.load.engine;

import androidx.annotation.Nullable;
import com.bumptech.glide.load.Key;

interface EngineJobListener {

    void onEngineJobComplete(EngineJob<?> engineJob, Key key, @Nullable EngineResource<?> resource);

    void onEngineJobCancelled(EngineJob<?> engineJob, Key key);
}
