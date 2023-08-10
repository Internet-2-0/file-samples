package com.bytedance.bmf_mods;

import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.bytedance.bmf_mods_api.VideoSRLutAPI;
import com.google.gson.JsonObject;
import com.ss.android.ugc.aweme.framework.services.annotation.ServiceImpl;
@ServiceImpl
/* loaded from: /mnt/c/Users/rando/Downloads/lemon8_re/./classes5.dex */
public class VideoSRLut implements VideoSRLutAPI {
    public static volatile boolean g = false;
    public JsonObject a = new JsonObject();
    public lv0 b = null;
    public kv0 c = null;
    public int d;
    public int e;
    public int f;

    static {
        try {
            System.loadLibrary("hmp");
            System.loadLibrary("bmf_module_sdk");
            System.loadLibrary("bmf_hydra");
            g = true;
            Log.d("bmf_mods", "video srlut is initialized");
        } catch (Throwable th) {
            Log.d("bmf_mods", "video srlut initialize occurs error, the msg = " + th.getMessage());
            g = false;
        }
    }

    public VideoSRLut() {
        Log.d("bmf_mods", "New VideoSRLut");
    }

    public int AsyncProcess(int i, int i2, int i3, int i4, boolean z) {
        if (this.c == null) {
            return -1;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.i("input_texture", jsonObject.m(Integer.valueOf(i)));
        jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(i2)));
        jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
        jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
        jsonObject.i("async", jsonObject.m(1));
        try {
            Log.d("bmf_mods", "VideoSRLut: async call");
            Object[] b = this.c.b(jsonObject);
            Log.d("bmf_mods", "VideoSRLut: async call end");
            JsonObject jsonObject2 = (JsonObject) b[0];
            return i2;
        } catch (Exception e) {
            Log.d("bmf_mods", "VideoSRLut: async call VRSR module failed, " + e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    public int AsyncWait() {
        if (this.c == null) {
            return -1;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.i("async_wait", jsonObject.m(1));
        try {
            Log.d("bmf_mods", "VideoSRLut: async process, wait.");
            Object[] b = this.c.b(jsonObject);
            Log.d("bmf_mods", "VideoSRLut: async wait finish.");
            JsonObject jsonObject2 = (JsonObject) b[0];
            return 0;
        } catch (Exception e) {
            Log.d("bmf_mods", "VideoSRLut: call VRSR module failed, %s" + e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    public int ClearClBuffer() {
        if (this.c == null) {
            return -1;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.i("clear_cl_buffer", jsonObject.m(1));
        try {
            Log.d("bmf_mods", "VideoSRLut: clear opencl buffer start.");
            Object[] b = this.c.b(jsonObject);
            Log.d("bmf_mods", "VideoSRLut: clear opencl buffer finish.");
            JsonObject jsonObject2 = (JsonObject) b[0];
            return 0;
        } catch (Exception e) {
            Log.d("bmf_mods", "VideoSRLut: clear opencl buffer failed," + e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    public void Free() {
        kv0 kv0Var = this.c;
        if (kv0Var != null) {
            kv0Var.c();
        }
    }

    public boolean GlSrInit(String str, int i, boolean z, int i2, int i3) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!g) {
            return false;
        }
        if (i == 0) {
            JsonObject jsonObject = this.a;
            jsonObject.i("scale", jsonObject.m(Double.valueOf(2.0d)));
            JsonObject jsonObject2 = this.a;
            jsonObject2.i("gl_sr", jsonObject2.m(1));
            this.b = new lv0("SR_LUT_Module", "c++", "libbmf_hydra.so", "");
            Class[] clsArr = {JsonObject.class};
            Class[] clsArr2 = {JsonObject.class};
            try {
                Log.d("bmf_mods", "VideoSRLut: load VRSR Module");
                this.c = new kv0(this.b, this.a, clsArr, clsArr2);
                Log.d("bmf_mods", "VideoSRLut: load VRSR Module success");
                Log.d("bmf_mods", String.format("BMF_gl_sr proces time:%d ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis)));
                return true;
            } catch (Exception e) {
                Log.d("bmf_mods", String.format("VideoSRLut: load video sr lut Module failed, %s", e.toString()));
                return false;
            }
        }
        Log.d("bmf_mods", String.format("VideoSRLut: unsupported algoType=%d", Integer.valueOf(i)));
        return false;
    }

    public boolean Init(int i) {
        if (!g) {
            return false;
        }
        if (i == 0) {
            JsonObject jsonObject = this.a;
            jsonObject.i("scale", jsonObject.m(Double.valueOf(2.0d)));
            JsonObject jsonObject2 = this.a;
            jsonObject2.i("data_type", jsonObject2.m(1));
            this.b = new lv0("SR_LUT_Module", "c++", "libbmf_hydra.so", "");
            Class[] clsArr = {JsonObject.class};
            Class[] clsArr2 = {JsonObject.class};
            try {
                Log.d("bmf_mods", "VideoSRLut: load SRLut Module");
                this.c = new kv0(this.b, this.a, clsArr, clsArr2);
                Log.d("bmf_mods", "VideoSRLut: load SRLut Module success");
                return true;
            } catch (Exception e) {
                Log.d("bmf_mods", "VideoSRLut: load video sr lut Module failed," + e.toString());
                return false;
            }
        }
        Log.d("bmf_mods", "VideoSRLut: unsupported algoType=" + i);
        return false;
    }

    public int Process(int i, int i2, int i3, int i4, boolean z) {
        if (g && this.c != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.i("input_texture", jsonObject.m(Integer.valueOf(i)));
            jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(i2)));
            jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
            jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
            try {
                JsonObject jsonObject2 = (JsonObject) this.c.b(jsonObject)[0];
                return i2;
            } catch (Exception e) {
                Log.d("bmf_mods", "VideoSRLut: call SRLut module failed, " + e.toString());
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public boolean Init(String str, int i, boolean z, int i2, int i3) {
        if (!g) {
            return false;
        }
        if (i == 0) {
            JsonObject jsonObject = this.a;
            jsonObject.i("scale", jsonObject.m(Double.valueOf(2.0d)));
            this.b = new lv0("SR_LUT_Module", "c++", "libbmf_hydra.so", "");
            Class[] clsArr = {JsonObject.class};
            Class[] clsArr2 = {JsonObject.class};
            try {
                Log.d("bmf_mods", "VideoSRLut: load SRLut Module");
                this.c = new kv0(this.b, this.a, clsArr, clsArr2);
                Log.d("bmf_mods", "VideoSRLut: load SRLut Module success");
                return true;
            } catch (Exception e) {
                Log.d("bmf_mods", "VideoSRLut: load video sr lut Module failed," + e.toString());
                return false;
            }
        }
        Log.d("bmf_mods", "VideoSRLut: unsupported algoType=" + i);
        return false;
    }

    public int Process(long j, long j2, long j3, int i, int i2, int i3, int i4) {
        if (g && this.c != null) {
            if (i3 != this.d || i4 != this.e) {
                int i5 = this.f;
                if (i5 > 0) {
                    GLES20.glDeleteTextures(1, new int[i5], 0);
                }
                this.d = i3;
                this.e = i4;
                int i6 = (int) (i3 * 2.0d);
                int i7 = (int) (2.0d * i4);
                int[] iArr = new int[1];
                GLES20.glGenTextures(1, iArr, 0);
                int glGetError = GLES20.glGetError();
                if (glGetError != 0) {
                    Log.d("bmf_mods", " GLUtils: glGenTextures : " + GLUtils.getEGLErrorString(glGetError));
                }
                if (iArr[0] != 0) {
                    GLES20.glBindTexture(3553, iArr[0]);
                    GLES20.glTexParameteri(3553, 10242, 33071);
                    GLES20.glTexParameteri(3553, 10243, 33071);
                    GLES20.glTexParameteri(3553, 10241, 9729);
                    GLES20.glTexParameteri(3553, 10240, 9729);
                }
                int i8 = iArr[0];
                this.f = i8;
                GLES20.glBindTexture(3553, i8);
                GLES20.glTexImage2D(3553, 0, 6408, i6, i7, 0, 6408, 5121, null);
                GLES20.glBindTexture(3553, 0);
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.i("y_ptr", jsonObject.m(Long.valueOf(j)));
            jsonObject.i("u_ptr", jsonObject.m(Long.valueOf(j2)));
            jsonObject.i("v_ptr", jsonObject.m(Long.valueOf(j3)));
            jsonObject.i("color_space", jsonObject.m(Integer.valueOf(i)));
            jsonObject.i("color_range", jsonObject.m(Integer.valueOf(i2)));
            jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(this.f)));
            jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
            jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
            try {
                JsonObject jsonObject2 = (JsonObject) this.c.b(jsonObject)[0];
                return this.f;
            } catch (Exception e) {
                Log.d("bmf_mods", "VideoSRLut: call SRLut module failed, " + e.toString());
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public boolean Init(String str, int i, boolean z, int i2, int i3, String str2) {
        if (!g) {
            return false;
        }
        if (i == 0) {
            JsonObject jsonObject = this.a;
            jsonObject.i("scale", jsonObject.m(Double.valueOf(2.0d)));
            JsonObject jsonObject2 = this.a;
            jsonObject2.i("license_module_name", jsonObject2.m(str2));
            this.b = new lv0("SR_LUT_Module", "c++", "libbmf_hydra.so", "");
            Class[] clsArr = {JsonObject.class};
            Class[] clsArr2 = {JsonObject.class};
            try {
                Log.d("bmf_mods", "VideoSRLut: load SRLut Module");
                this.c = new kv0(this.b, this.a, clsArr, clsArr2);
                Log.d("bmf_mods", "VideoSRLut: load SRLut Module success");
                return true;
            } catch (Exception e) {
                Log.d("bmf_mods", String.format("VideoSRLut: load video sr lut Module failed, %s", e.toString()));
                return false;
            }
        }
        Log.d("bmf_mods", String.format("VideoSRLut: unsupported algoType=%d", Integer.valueOf(i)));
        return false;
    }
}
