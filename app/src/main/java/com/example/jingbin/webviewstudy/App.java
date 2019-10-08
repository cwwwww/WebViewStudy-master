package com.example.jingbin.webviewstudy;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.wanjian.cockroach.Cockroach;

import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;


/**
 * @author jingbin
 * @data 2018/2/2
 */

public class App extends Application {

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
//        initX5();
        initOkGo();
        initCockroach();
        initJPush();
    }

    private void initOkGo() {
        OkGo.getInstance().init(this);
    }

    private void initJPush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        setStyleCustom();
    }

    private void setStyleCustom() {
        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(this, R.layout.customer_notitfication_layout, R.id.icon, R.id.title, R.id.text);
        builder.layoutIconDrawable = R.mipmap.app_logo;
        builder.developerArg0 = "developerArg2";
        JPushInterface.setPushNotificationBuilder(2, builder);
    }

    private void initCockroach() {
        Cockroach.install(new Cockroach.ExceptionHandler() {
            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Log.e("AndroidRuntime", "--->CockroachException:" + thread + "<---", throwable);
                            Toast.makeText(getApplicationContext(), "Exception Happend\n" + thread + "\n" + throwable.toString(),
                                    Toast.LENGTH_LONG).show();
//                            Toast.makeText(getApplicationContext(), "网络不给力！", Toast.LENGTH_LONG).show();
                        } catch (Throwable e) {
                        }
                    }
                });
            }
        });
    }


    public static App getInstance() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
