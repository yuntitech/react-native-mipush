package com.ichong.zzy.mipush;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.mipush.sdk.MiPushMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.facebook.react.common.ReactConstants.TAG;

/**
 * Created by zzy on 16/8/11.
 * Date : 16/8/11 13:36
 */
public class MIPushPackage implements ReactPackage {

    public static ReactApplicationContext sReactContext;
    public static MiPushMessage sMiPushMessage;

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {

        List<NativeModule> modules = new ArrayList<>();


        try {
            String appId = MIPushHelper.getMIPushValue(reactContext.getApplicationContext(), "MIPUSH_APPID");
            String appKey = MIPushHelper.getMIPushValue(reactContext.getApplicationContext(), "MIPUSH_APPKEY");

            if (shouldInit(reactContext.getApplicationContext())) {

                MiPushClient.registerPush(reactContext.getApplicationContext(), appId, appKey);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sReactContext = reactContext;

        modules.add(new MIPushModule(reactContext));

        return modules;
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    private boolean shouldInit(Context context) {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    public static void reInitPush(Context ctx) {
        try {
            String appId = MIPushHelper.getMIPushValue(ctx.getApplicationContext(), "MIPUSH_APPID");
            String appKey = MIPushHelper.getMIPushValue(ctx.getApplicationContext(), "MIPUSH_APPKEY");
            MiPushClient.registerPush(ctx.getApplicationContext(), appId, appKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
