package com.bumptech.glide.load.resource;

import android.graphics.ColorSpace;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder$OnHeaderDecodedListener;
import android.graphics.ImageDecoder$OnPartialImageListener;
import android.os.Build;
import android.util.Log;
import android.util.Size;
import bitter.jnibridge.a$$ExternalSyntheticApiModelOutline0;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.PreferredColorSpace;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.HardwareConfigState;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ImageDecoderResourceDecoder<T> implements ResourceDecoder<ImageDecoder.Source, T> {
    private static final String TAG = "ImageDecoder";
    final HardwareConfigState hardwareConfigState = HardwareConfigState.getInstance();

    protected abstract Resource<T> decode(ImageDecoder.Source source, int i, int i2, ImageDecoder$OnHeaderDecodedListener imageDecoder$OnHeaderDecodedListener) throws IOException;

    /* JADX INFO: renamed from: handles, reason: avoid collision after fix types in other method */
    public final boolean handles2(ImageDecoder.Source source, Options options) {
        return true;
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ Resource decode(ImageDecoder.Source source, int i, int i2, Options options) throws IOException {
        return decode2(a$$ExternalSyntheticApiModelOutline0.m199m((Object) source), i, i2, options);
    }

    @Override // com.bumptech.glide.load.ResourceDecoder
    public /* bridge */ /* synthetic */ boolean handles(ImageDecoder.Source source, Options options) throws IOException {
        return handles2(a$$ExternalSyntheticApiModelOutline0.m199m((Object) source), options);
    }

    /* JADX INFO: renamed from: decode, reason: avoid collision after fix types in other method */
    public final Resource<T> decode2(ImageDecoder.Source source, final int i, final int i2, Options options) throws IOException {
        final DecodeFormat decodeFormat = (DecodeFormat) options.get(Downsampler.DECODE_FORMAT);
        final DownsampleStrategy downsampleStrategy = (DownsampleStrategy) options.get(DownsampleStrategy.OPTION);
        final boolean z = options.get(Downsampler.ALLOW_HARDWARE_CONFIG) != null && ((Boolean) options.get(Downsampler.ALLOW_HARDWARE_CONFIG)).booleanValue();
        final PreferredColorSpace preferredColorSpace = (PreferredColorSpace) options.get(Downsampler.PREFERRED_COLOR_SPACE);
        return decode(source, i, i2, new ImageDecoder$OnHeaderDecodedListener() { // from class: com.bumptech.glide.load.resource.ImageDecoderResourceDecoder.1
            public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source2) {
                if (ImageDecoderResourceDecoder.this.hardwareConfigState.isHardwareConfigAllowed(i, i2, z, false)) {
                    imageDecoder.setAllocator(3);
                } else {
                    imageDecoder.setAllocator(1);
                }
                if (decodeFormat == DecodeFormat.PREFER_RGB_565) {
                    imageDecoder.setMemorySizePolicy(0);
                }
                imageDecoder.setOnPartialImageListener(new ImageDecoder$OnPartialImageListener() { // from class: com.bumptech.glide.load.resource.ImageDecoderResourceDecoder.1.1
                    public boolean onPartialImage(ImageDecoder.DecodeException decodeException) {
                        return false;
                    }
                });
                Size size = imageInfo.getSize();
                int width = i;
                if (width == Integer.MIN_VALUE) {
                    width = size.getWidth();
                }
                int height = i2;
                if (height == Integer.MIN_VALUE) {
                    height = size.getHeight();
                }
                float scaleFactor = downsampleStrategy.getScaleFactor(size.getWidth(), size.getHeight(), width, height);
                int iRound = Math.round(size.getWidth() * scaleFactor);
                int iRound2 = Math.round(size.getHeight() * scaleFactor);
                if (Log.isLoggable(ImageDecoderResourceDecoder.TAG, 2)) {
                    Log.v(ImageDecoderResourceDecoder.TAG, "Resizing from [" + size.getWidth() + "x" + size.getHeight() + "] to [" + iRound + "x" + iRound2 + "] scaleFactor: " + scaleFactor);
                }
                imageDecoder.setTargetSize(iRound, iRound2);
                if (Build.VERSION.SDK_INT >= 28) {
                    imageDecoder.setTargetColorSpace(ColorSpace.get((preferredColorSpace == PreferredColorSpace.DISPLAY_P3 && imageInfo.getColorSpace() != null && imageInfo.getColorSpace().isWideGamut()) ? ColorSpace.Named.DISPLAY_P3 : ColorSpace.Named.SRGB));
                } else if (Build.VERSION.SDK_INT >= 26) {
                    imageDecoder.setTargetColorSpace(ColorSpace.get(ColorSpace.Named.SRGB));
                }
            }
        });
    }
}
