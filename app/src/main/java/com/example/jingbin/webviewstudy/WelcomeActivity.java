package com.example.jingbin.webviewstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.jingbin.webviewstudy.bean.QPData;
import com.example.jingbin.webviewstudy.utils.JudgeNewWorkUtil;
import com.example.jingbin.webviewstudy.utils.ToastUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

/**
 * Created by admin on 2017/10/13.
 */

public class WelcomeActivity extends AppCompatActivity {
    private Timer timer;
    private TimerTask timerTask;
    private int recLen = 4;
    private TextView tvTime;
    private QPData qpData;
    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvTime.setText("跳过" + recLen);
                    if (recLen < 1) {
                        if (timer != null) {
                            timer.cancel();
                        }
                        if (timerTask != null) {
                            timerTask.cancel();
                        }
                        Intent intent = new Intent(WelcomeActivity.this, WebViewActivity.class);
                        startActivity(intent);
                        finish();
                    }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否有网络
        if (!JudgeNewWorkUtil.isNetworkAvailable(this)) {
            setContentView(R.layout.view_nonetwork);
            ToastUtil.showUI(this, "网络异常,请检查设置！");
            return;
        }
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
                Intent intent = new Intent(WelcomeActivity.this, WebViewActivity.class);
                startActivity(intent);
                finish();
            }
        });
        init();
    }

    private void init() {
//        setTimer();
//        getData();
    }
//
//    private void getData() {
//        OkGo.<String>post("http://45.125.218.50:9638/api/line/queryAccessUrlH5")
//                .tag(this)
//                .execute(new AbsCallback<String>() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        qpData = new Gson().fromJson(response.body(), QPData.class);
//                        if (qpData.getCode().equals("00")) {
////                            Intent intent = new Intent(WelcomeActivity.this, WebViewActivity.class);
//                            Intent intent = new Intent(WelcomeActivity.this, X5WebViewActivity.class);
//                            intent.putExtra("mUrl", qpData.getData().getAccessUrl());
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            ToastUtil.showLong(WelcomeActivity.this, qpData.getMsg());
//                        }
//                    }
//
////                    @Override
////                    public String convertResponse(Response response) throws Throwable {
////                        return response.body().string();
////                    }
//                });
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                recLen--;
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer.schedule(timerTask, 0, 1000);
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