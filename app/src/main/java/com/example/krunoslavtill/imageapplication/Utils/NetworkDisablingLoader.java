package com.example.krunoslavtill.imageapplication.Utils;

/**
 * Created by krunoslavtill on 26/07/16.
 */

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.IOException;
import java.io.InputStream;

public class NetworkDisablingLoader implements StreamModelLoader<String> {
    @Override public DataFetcher<InputStream> getResourceFetcher(final String model, int width, int height) {
        return new DataFetcher<InputStream>() {
            @Override public InputStream loadData(Priority priority) throws Exception {
                throw new IOException("Forced Glide network failure");
            }
            @Override public void cleanup() { }
            @Override public String getId() { return model; }
            @Override public void cancel() { }
        };
    }
}
