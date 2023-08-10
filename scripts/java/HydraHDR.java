package com.bytedance.bmf_mods;

import android.util.Log;
import com.bytedance.bmf_mods_api.HydraHDRAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ss.android.ugc.aweme.framework.services.annotation.ServiceImpl;
@ServiceImpl
/* loaded from: /mnt/c/Users/rando/Downloads/lemon8_re/./classes5.dex */
public class HydraHDR implements HydraHDRAPI {
    public static volatile boolean d = false;
    public JsonObject a = new JsonObject();
    public lv0 b = null;
    public kv0 c = null;

    static {
        try {
            System.loadLibrary("hmp");
            System.loadLibrary("bmf_module_sdk");
            System.loadLibrary("bmf_hydra");
            d = true;
            Log.d("bmf_mods", "hydra hdr is initialized");
        } catch (Throwable th) {
            Log.d("bmf_mods", "hydra hdr initialize occurs error, the msg = " + th.getMessage());
            d = false;
        }
    }

    public HydraHDR() {
        Log.d("bmf_mods", "New HydraHDR");
    }

    public void Free() {
        kv0 kv0Var = this.c;
        if (kv0Var != null) {
            kv0Var.c();
        }
    }

    public boolean Init(boolean z, float f, boolean z2, int i) {
        if (!d) {
            return false;
        }
        JsonObject jsonObject = this.a;
        jsonObject.i("enable_profile", jsonObject.m(Boolean.valueOf(z)));
        JsonObject jsonObject2 = this.a;
        jsonObject2.i("blend_alpha", jsonObject2.m(Float.valueOf(f)));
        JsonObject jsonObject3 = this.a;
        jsonObject3.i("fast_mode", jsonObject3.m(Boolean.valueOf(z2)));
        JsonObject jsonObject4 = this.a;
        jsonObject4.i("input_texture_type", jsonObject4.m(Integer.valueOf(i)));
        this.b = new lv0("HydraHDRModule", "c++", "libbmf_hydra.so", "");
        Class[] clsArr = {JsonObject.class};
        Class[] clsArr2 = {JsonObject.class};
        try {
            Log.d("bmf_mods", "HydraHDR: load HDR Module");
            this.c = new kv0(this.b, this.a, clsArr, clsArr2);
            Log.d("bmf_mods", "HydraHDR: load HDR Module success");
            return true;
        } catch (Exception e) {
            Log.d("bmf_mods", "HydraHDR: load HDR Module failed," + e.toString());
            return false;
        }
    }

    public int Process(int i, int i2, int i3, int i4) {
        return Process(i, i2, i3, i4, false);
    }

    public int oesProcess(int i, int i2, int i3, int i4, float[] fArr) {
        if (d && this.c != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.i("input_texture", jsonObject.m(Integer.valueOf(i)));
            jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(i2)));
            jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
            jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
            JsonArray jsonArray = new JsonArray();
            for (float f : fArr) {
                jsonArray.j(Float.valueOf(f));
            }
            jsonObject.i("matrix", jsonArray);
            try {
                JsonObject jsonObject2 = (JsonObject) this.c.b(jsonObject)[0];
                return i2;
            } catch (Exception e) {
                Log.d("bmf_mods", "HydraHDR: call HDR module failed," + e.toString());
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public int Process(int i, int i2, int i3, int i4, boolean z) {
        if (d && this.c != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.i("input_texture", jsonObject.m(Integer.valueOf(i)));
            jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(i2)));
            jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
            jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
            jsonObject.i("first_frame", jsonObject.m(Boolean.valueOf(z)));
            try {
                JsonObject jsonObject2 = (JsonObject) this.c.b(jsonObject)[0];
                return i2;
            } catch (Exception e) {
                Log.d("bmf_mods", "HydraHDR: call HDR module failed," + e.toString());
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }
}
