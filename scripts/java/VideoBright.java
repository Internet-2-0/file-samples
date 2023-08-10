package com.bytedance.bmf_mods;

import android.util.Log;
import com.bytedance.bmf_mods_api.VideoBrightAPI;
import com.bytedance.bmf_mods_api.VideoBrightCallbackAPI;
import com.google.gson.JsonObject;
import com.ss.android.ugc.aweme.framework.services.annotation.ServiceImpl;
@ServiceImpl
/* loaded from: /mnt/c/Users/rando/Downloads/lemon8_re/./classes5.dex */
public class VideoBright implements VideoBrightAPI {
    public static volatile boolean e = false;
    public JsonObject a = new JsonObject();
    public lv0 b = null;
    public kv0 c = null;
    public VideoBrightCallbackAPI d = null;

    static {
        try {
            System.loadLibrary("hmp");
            System.loadLibrary("bmf_module_sdk");
            System.loadLibrary("bmf_hydra");
            e = true;
            Log.d("bmf_mods", "video bright module is initialized");
        } catch (Throwable th) {
            Log.d("bmf_mods", "video bright module initialize occurs error, the msg =" + th.getMessage());
            e = false;
        }
    }

    public VideoBright() {
        Log.d("bmf_mods", "New VideoBright");
    }

    public void Free() {
        kv0 kv0Var = this.c;
        if (kv0Var != null) {
            kv0Var.c();
        }
    }

    public int GetStatus() {
        return -1;
    }

    public boolean Init(int i, int i2, int i3, int i4) {
        if (!e) {
            return false;
        }
        JsonObject jsonObject = this.a;
        jsonObject.i("init_fps", jsonObject.m(Integer.valueOf(i)));
        JsonObject jsonObject2 = this.a;
        jsonObject2.i("normal_fps", jsonObject2.m(Integer.valueOf(i2)));
        JsonObject jsonObject3 = this.a;
        jsonObject3.i("long_sw", jsonObject3.m(Integer.valueOf(i3)));
        JsonObject jsonObject4 = this.a;
        jsonObject4.i("lum_thre", jsonObject4.m(Integer.valueOf(i4)));
        this.b = new lv0("Brighten_Module", "c++", "libbmf_hydra.so", "");
        Class[] clsArr = {JsonObject.class};
        Class[] clsArr2 = {JsonObject.class};
        try {
            Log.d("bmf_mods", "Brighten_Module: load bright Module");
            this.c = new kv0(this.b, this.a, clsArr, clsArr2);
            Log.d("bmf_mods", "Brighten_Module: load bright Module success");
            return true;
        } catch (Exception e2) {
            Log.d("bmf_mods", "Brighten_Module: load bright Module failed," + e2.toString());
            return false;
        }
    }

    public int Process(int i, int i2, int i3, int i4, long j) {
        if (e && this.c != null) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.i("input_texture", jsonObject.m(Integer.valueOf(i)));
            jsonObject.i("output_texture", jsonObject.m(Integer.valueOf(i2)));
            jsonObject.i("width", jsonObject.m(Integer.valueOf(i3)));
            jsonObject.i("height", jsonObject.m(Integer.valueOf(i4)));
            jsonObject.i("timestamp", jsonObject.m(Long.valueOf(j)));
            try {
                JsonObject jsonObject2 = (JsonObject) this.c.b(jsonObject)[0];
                int c = jsonObject2.o("output_texture").c();
                int c2 = jsonObject2.o("status").c();
                if (c2 == 2) {
                    float b = jsonObject2.o("process_time").b();
                    float b2 = jsonObject2.o("input_texture_brightness").b();
                    float b3 = jsonObject2.o("output_texture_brightness").b();
                    VideoBrightCallbackAPI videoBrightCallbackAPI = this.d;
                    if (videoBrightCallbackAPI != null) {
                        videoBrightCallbackAPI.callback(c2, b, b2, b3);
                    }
                }
                return c;
            } catch (Exception e2) {
                Log.d("bmf_mods", "Brighten_Module: call bright module failed," + e2.toString());
                e2.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    public void SetCallback(VideoBrightCallbackAPI videoBrightCallbackAPI) {
        this.d = videoBrightCallbackAPI;
    }
}
