package com.bytedance.bmf_mods;

import android.graphics.Bitmap;
import android.util.Log;
import com.bytedance.bmf.API;
import com.bytedance.bmf_mods_api.VideoOCLSRAPI;
import com.bytedance.hmp.Api;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ss.android.ugc.aweme.framework.services.annotation.ServiceImpl;
@ServiceImpl
/* loaded from: /mnt/c/Users/rando/Downloads/lemon8_re/./classes5.dex */
public class VideoOCLSR implements VideoOCLSRAPI {
    public static volatile boolean e = false;
    public JsonObject a = new JsonObject();
    public lv0 b = null;
    public kv0 c = null;
    public int d = 0;

    static {
        try {
            System.loadLibrary("hmp");
            System.loadLibrary("bmf_module_sdk");
            System.loadLibrary("vrsr");
            e = true;
            Log.d("bmf_mods", "videoOCLSR is initialized");
        } catch (Throwable th) {
            Log.d("bmf_mods", "videoOCLSR initialize occurs error, the msg = " + th.getMessage());
            e = false;
        }
    }

    public VideoOCLSR() {
        Log.d("bmf_mods", "New VideoOCLSR");
    }

    public void Free() {
        kv0 kv0Var = this.c;
        if (kv0Var != null) {
            kv0Var.c();
        }
    }

    public boolean Init(String str, int i, boolean z, int i2, int i3) {
        return Init(str, i, z, i2, i3, 0);
    }

    public int OesProcess(int i, int i2, int i3, int i4, float[] fArr, boolean z) {
        if (e && this.c != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.i("input_texture", jsonObject.m(Integer.valueOf(i)));
            jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(i2)));
            jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
            jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
            if (this.d == 1) {
                JsonArray jsonArray = new JsonArray();
                for (float f : fArr) {
                    jsonArray.j(Float.valueOf(f));
                }
                jsonObject.i("matrix", jsonArray);
            }
            try {
                JsonObject jsonObject2 = (JsonObject) this.c.b(jsonObject)[0];
                return i2;
            } catch (Exception e2) {
                Log.d("bmf_mods", "VideoOCLSR: call VRSR module failed," + e2.toString());
                e2.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int Process(int i, int i2, int i3, int i4, boolean z) {
        if (e && this.c != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.i("input_texture", jsonObject.m(Integer.valueOf(i)));
            jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(i2)));
            jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
            jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
            try {
                JsonObject jsonObject2 = (JsonObject) this.c.b(jsonObject)[0];
                return i2;
            } catch (Exception e2) {
                Log.d("bmf_mods", "VideoOCLSR: call VRSR module failed, %s" + e2.toString());
                e2.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public boolean Init(String str, int i, boolean z, int i2, int i3, int i4) {
        if (!e) {
            return false;
        }
        if (z) {
            this.d = 1;
        }
        JsonObject jsonObject = this.a;
        jsonObject.i("config_path", jsonObject.m(str));
        if (i == 0) {
            JsonObject jsonObject2 = this.a;
            jsonObject2.i("scale", jsonObject2.m(Double.valueOf(2.0d)));
        } else if (i == 4) {
            JsonObject jsonObject3 = this.a;
            jsonObject3.i("scale", jsonObject3.m(Double.valueOf(1.5d)));
        } else {
            Log.d("bmf_mods", "VideoOCLSR: unsupported algoType=%d" + i);
            return false;
        }
        JsonObject jsonObject4 = this.a;
        jsonObject4.i("max_input_width", jsonObject4.m(Integer.valueOf(i2)));
        JsonObject jsonObject5 = this.a;
        jsonObject5.i("max_input_height", jsonObject5.m(Integer.valueOf(i3)));
        JsonObject jsonObject6 = this.a;
        jsonObject6.i("filter_path", jsonObject6.m(""));
        JsonObject jsonObject7 = this.a;
        jsonObject7.i("format", jsonObject7.m(Integer.valueOf(i4)));
        JsonObject jsonObject8 = this.a;
        jsonObject8.i("color", jsonObject8.m(1));
        JsonObject jsonObject9 = this.a;
        jsonObject9.i("power", jsonObject9.m(4));
        JsonObject jsonObject10 = this.a;
        jsonObject10.i("backend", jsonObject10.m(2));
        JsonObject jsonObject11 = this.a;
        jsonObject11.i("pipeline", jsonObject11.m(1));
        JsonObject jsonObject12 = this.a;
        jsonObject12.i("oes_flag", jsonObject12.m(Integer.valueOf(this.d)));
        this.b = new lv0("VRSR_Module", "c++", "libvrsr.so", "");
        Class[] clsArr = {JsonObject.class};
        Class[] clsArr2 = {JsonObject.class};
        if (i4 == 1) {
            clsArr[0] = nv0.class;
            clsArr2[0] = nv0.class;
        }
        try {
            Log.d("bmf_mods", "VideoOCLSR: load VRSR Module");
            this.c = new kv0(this.b, this.a, clsArr, clsArr2);
            Log.d("bmf_mods", "VideoOCLSR: load VRSR Module success");
            return true;
        } catch (Exception e2) {
            Log.d("bmf_mods", "VideoOCLSR: load VRSR Module failed," + e2.toString());
            return false;
        }
    }

    public Bitmap Process(Bitmap bitmap, int i, int i2, boolean z) {
        if (e && this.c != null) {
            nv0 nv0Var = new nv0(new zz1(bitmap));
            try {
                nv0 nv0Var2 = (nv0) this.c.b(nv0Var)[0];
                Bitmap createBitmap = Bitmap.createBitmap(API.bmf_vf_width(nv0Var2.a), API.bmf_vf_height(nv0Var2.a), Bitmap.Config.ARGB_8888);
                long bmf_vf_from_frame = API.bmf_vf_from_frame(Api.frame_make(createBitmap));
                API.bmf_vf_copy_from(bmf_vf_from_frame, nv0Var2.a);
                nv0Var.a();
                nv0Var2.a();
                API.bmf_vf_free(bmf_vf_from_frame);
                return createBitmap;
            } catch (Exception e2) {
                nv0Var.a();
                Log.d("bmf_mods", "VideoOCLSR: call VRSR module failed," + e2.toString());
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
