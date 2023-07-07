package com.rkgroup.base.basemodule.glide;


import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

/**
 * Second step create ThumbnailBuilderFactory
 */

public class PDFThumbnailBuilderFactory implements ModelLoaderFactory<String, Bitmap> {
    /**
     * {@link Context} that pass to {@link PDFThumbnailBuilder} class
     */
    private final Context mContext;
    public PDFThumbnailBuilderFactory(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ModelLoader<String, Bitmap> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new PDFThumbnailBuilder(mContext);
    }

    @Override
    public void teardown() {
        // empty

    }
}
