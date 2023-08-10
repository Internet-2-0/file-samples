package com.bytedance.bmf_mods;

import android.util.Log;
import com.bytedance.bmf_mods_api.ColorShiftAPI;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ss.android.ugc.aweme.framework.services.annotation.ServiceImpl;
@ServiceImpl
/* loaded from: /mnt/c/Users/rando/Downloads/lemon8_re/./classes5.dex */
public class ColorShift implements ColorShiftAPI {
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
            Log.d("bmf_mods", "color shift is initialized");
        } catch (Throwable th) {
            Log.d("bmf_mods", "color shift initialize occurs error, the msg = " + th.getMessage());
            d = false;
        }
    }

    public ColorShift() {
        Log.d("bmf_mods", "New ColorShift");
    }

    public void Free() {
        kv0 kv0Var = this.c;
        if (kv0Var != null) {
            kv0Var.c();
        }
    }

    public boolean Init(boolean z, float f, float f2, float[] fArr, float[] fArr2, int i) {
        if (!d) {
            return false;
        }
        JsonObject jsonObject = this.a;
        jsonObject.i("enable_profile", jsonObject.m(Boolean.valueOf(z)));
        JsonObject jsonObject2 = this.a;
        jsonObject2.i("bright", jsonObject2.m(Float.valueOf(f)));
        JsonObject jsonObject3 = this.a;
        jsonObject3.i("saturation", jsonObject3.m(Float.valueOf(f2)));
        JsonObject jsonObject4 = this.a;
        jsonObject4.i("input_texture_type", jsonObject4.m(Integer.valueOf(i)));
        JsonArray jsonArray = new JsonArray();
        for (float f3 : fArr) {
            jsonArray.j(Float.valueOf(f3));
        }
        this.a.i("shift_positive", jsonArray);
        JsonArray jsonArray2 = new JsonArray();
        for (float f4 : fArr2) {
            jsonArray2.j(Float.valueOf(f4));
        }
        this.a.i("shift_negative", jsonArray2);
        this.b = new lv0("ColorShiftModule", "c++", "libbmf_hydra.so", "");
        Class[] clsArr = {JsonObject.class};
        Class[] clsArr2 = {JsonObject.class};
        try {
            Log.d("bmf_mods", "ColorShift: load ColorShift Module");
            this.c = new kv0(this.b, this.a, clsArr, clsArr2);
            Log.d("bmf_mods", "ColorShift: load ColorShift Module success");
            return true;
        } catch (Exception e) {
            Log.d("bmf_mods", "ColorShift: load ColorShift Module failed," + e.toString());
            return false;
        }
    }

    public int Process(int i, int i2, int i3, int i4) {
        if (d && this.c != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.i("input_texture", jsonObject.m(Integer.valueOf(i)));
            jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(i2)));
            jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
            jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
            try {
                JsonObject jsonObject2 = (JsonObject) this.c.b(jsonObject)[0];
                return i2;
            } catch (Exception e) {
                Log.d("bmf_mods", "ColorShift: call ColorShift module failed," + e.toString());
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
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
                Log.d("bmf_mods", "ColorShift: call ColorShift module failed," + e.toString());
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }
}
