package com.bytedance.hmp;

import android.graphics.Bitmap;
/* loaded from: /mnt/c/Users/rando/Downloads/lemon8_re/./classes5.dex */
public class Api {
    static {
        System.loadLibrary("_jhmp");
    }

    public static native int color_model_ctc(long j);

    public static native void color_model_free(long j);

    public static native long color_model_make(int i, int i2, int i3, int i4);

    public static native int color_model_primaries(long j);

    public static native int color_model_range(long j);

    public static native int color_model_space(long j);

    public static native int device_count(int i);

    public static native void device_free(long j);

    public static native void device_guard_free(long j);

    public static native long device_guard_make(long j);

    public static native int device_index(long j);

    public static native long device_make(int i, int i2);

    public static native long device_make(String str);

    public static native String device_stringfy(long j);

    public static native int device_type(long j);

    public static native long frame_clone(long j);

    public static native void frame_copy_from(long j, long j2);

    public static native long frame_crop(long j, int i, int i2, int i3, int i4);

    public static native boolean frame_defined(long j);

    public static native int frame_device_index(long j);

    public static native int frame_device_type(long j);

    public static native int frame_dtype(long j);

    public static native int frame_format(long j);

    public static native void frame_free(long j);

    public static native long frame_from_image(long j, long j2);

    public static native int frame_height(long j);

    public static native long frame_make(int i, int i2, long j, String str);

    public static native long frame_make(Bitmap bitmap);

    public static native long frame_make(long[] jArr, int i, int i2, long j);

    public static native long frame_make(long[] jArr, long j);

    public static native int frame_nplanes(long j);

    public static native long frame_pix_info(long j);

    public static native long frame_plane(long j, int i);

    public static native String frame_stringfy(long j);

    public static native long frame_to_device(long j, String str, boolean z);

    public static native long frame_to_image(long j, int i);

    public static native void frame_unlock(Bitmap bitmap);

    public static native int frame_width(long j);

    public static native int image_cdim(long j);

    public static native long image_clone(long j);

    public static native long image_color_model(long j);

    public static native void image_copy_from(long j, long j2);

    public static native long image_crop(long j, int i, int i2, int i3, int i4);

    public static native long image_data(long j);

    public static native boolean image_defined(long j);

    public static native int image_device_index(long j);

    public static native int image_device_type(long j);

    public static native int image_dtype(long j);

    public static native int image_format(long j);

    public static native void image_free(long j);

    public static native int image_hdim(long j);

    public static native int image_height(long j);

    public static native long image_make(int i, int i2, int i3, int i4, int i5, String str, boolean z);

    public static native long image_make(long j, int i);

    public static native long image_make(long j, int i, long j2);

    public static native int image_nchannels(long j);

    public static native void image_set_color_model(long j, long j2);

    public static native String image_stringfy(long j);

    public static native long image_to_device(long j, String str, boolean z);

    public static native long image_to_dtype(long j, int i);

    public static native int image_wdim(long j);

    public static native int image_width(long j);

    public static native int pixel_format_desc_channels(long j, int i);

    public static native int pixel_format_desc_dtype(long j);

    public static native int pixel_format_desc_format(long j);

    public static native void pixel_format_desc_free(long j);

    public static native int pixel_format_desc_infer_height(long j, int i, int i2);

    public static native int pixel_format_desc_infer_nitems(long j, int i, int i2);

    public static native int pixel_format_desc_infer_nitems(long j, int i, int i2, int i3);

    public static native int pixel_format_desc_infer_width(long j, int i, int i2);

    public static native long pixel_format_desc_make(int i);

    public static native int pixel_format_desc_nplanes(long j);

    public static native long pixel_info_color_model(long j);

    public static native int pixel_info_ctc(long j);

    public static native int pixel_info_format(long j);

    public static native void pixel_info_free(long j);

    public static native int pixel_info_infer_space(long j);

    public static native boolean pixel_info_is_rgbx(long j);

    public static native long pixel_info_make(int i, int i2, int i3);

    public static native long pixel_info_make(int i, long j);

    public static native int pixel_info_primaries(long j);

    public static native int pixel_info_range(long j);

    public static native int pixel_info_space(long j);

    public static native String pixel_info_stringfy(long j);

    public static native long scalar(double d);

    public static native long scalar(long j);

    public static native long scalar(boolean z);

    public static native void scalar_free(long j);

    public static native long stream_current(int i);

    public static native int stream_device_index(long j);

    public static native int stream_device_type(long j);

    public static native void stream_free(long j);

    public static native long stream_guard_create(long j);

    public static native void stream_guard_free(long j);

    public static native long stream_handle(long j);

    public static native long stream_make(int i, long j);

    public static native boolean stream_query(long j);

    public static native void stream_set_current(long j);

    public static native void stream_synchronize(long j);

    public static native long tensor_alias(long j);

    public static native long tensor_arange(long j, long j2, long j3, int i, String str, boolean z);

    public static native long tensor_clone(long j);

    public static native void tensor_copy_from(long j, long j2);

    public static native long tensor_data_ptr(long j);

    public static native boolean tensor_defined(long j);

    public static native int tensor_device_index(long j);

    public static native int tensor_device_type(long j);

    public static native long tensor_dim(long j);

    public static native int tensor_dtype(long j);

    public static native long tensor_empty(long[] jArr, int i, String str, boolean z);

    public static native void tensor_fill(long j, long j2);

    public static native void tensor_free(long j);

    public static native long tensor_from_file(String str, int i, long j, long j2);

    public static native boolean tensor_is_contiguous(long j);

    public static native long tensor_itemsize(long j);

    public static native long tensor_nbytes(long j);

    public static native long tensor_nitems(long j);

    public static native long tensor_permute(long j, long[] jArr);

    public static native long tensor_reshape(long j, long[] jArr);

    public static native long tensor_select(long j, long j2, long j3);

    public static native long tensor_size(long j, long j2);

    public static native long tensor_slice(long j, long j2, long j3, long j4, long j5);

    public static native long tensor_squeeze(long j, long j2);

    public static native long tensor_stride(long j, long j2);

    public static native String tensor_stringfy(long j);

    public static native long tensor_to_device(long j, String str, boolean z);

    public static native long tensor_to_dtype(long j, int i);

    public static native void tensor_to_file(long j, String str);

    public static native long tensor_unsqueeze(long j, long j2);

    public static native long tensor_view(long j, long[] jArr);
}
