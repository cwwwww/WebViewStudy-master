package com.example.jingbin.webviewstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.jingbin.webviewstudy.bean.AppData;
import com.example.jingbin.webviewstudy.utils.JudgeNewWorkUtil;
import com.example.jingbin.webviewstudy.utils.ToastUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class WelcomeForWebActivity extends AppCompatActivity {
    private Timer timer;
    private TimerTask timerTask;
    private int recLen = 4;
    private TextView tvTime;
    private String baseUrl;
    private AppData appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否有网络
//        if (!JudgeNewWorkUtil.isNetworkAvailable(this)) {
//            setContentView(R.layout.view_nonetwork);
//            ToastUtil.showUI(this, "网络异常,请检查设置！");
//            return;
//        }
        setContentView(R.layout.welcome1);
        ButterKnife.bind(this);
        tvTime = findViewById(R.id.tv_time);
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timer != null) {
                    timer.cancel();
                }
                if (timerTask != null) {
                    timerTask.cancel();
                }
                Intent intent = new Intent(WelcomeForWebActivity.this, WebViewActivity.class);
                intent.putExtra("url", baseUrl);
                startActivity(intent);
                finish();
            }
        });
        baseUrl = getString(R.string.AliUrl);
        init();
    }


    private void getData() {
        OkGo.<String>get(getString(R.string.updateURL))
                .tag(this)
                .execute(new AbsCallback<String>() {
                    @Override
                    public String convertResponse(okhttp3.Response response) throws Throwable {
                        return response.body().string();
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        appData = new Gson().fromJson(response.body(), AppData.class);
                    }
                });

    }


    private void init() {
        setTimer();
//        getData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setTimer() {
        //检查网络
        if (!JudgeNewWorkUtil.isNetworkAvailable(this)) {
            setContentView(R.layout.view_nonetwork);
            ToastUtil.showUI(this, "网络异常,请检查设置！");
            return;
        }

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeForWebActivity.this, WebViewActivity.class);
//                if (appData != null) {
//                    if (appData.getIsChange().equals("y")) {
//                        intent.putExtra("url", appData.getAppUrl());
//                    } else {
//                    }
//                }
                intent.putExtra("url", baseUrl);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask, 2000);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
