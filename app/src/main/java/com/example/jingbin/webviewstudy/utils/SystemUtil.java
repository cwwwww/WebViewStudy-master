package com.example.jingbin.webviewstudy.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by MSI on 2018/6/24.
 */

public class SystemUtil {
    public static boolean isApiaLive(Context context, String str) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(str) || info.baseActivity.getPackageName().equals(str)) {
                isAppRunning = true;
                break;
            }
        }
        return isAppRunning;
    }
}
