package com.example.jingbin.webviewstudy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jingbin.webviewstudy.tencentx5.X5WebViewActivity;
import com.example.jingbin.webviewstudy.utils.JudgeNewWorkUtil;
import com.example.jingbin.webviewstudy.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;

public class WelcomeForWebActivity extends AppCompatActivity {
    private Timer timer;
    private TimerTask timerTask;
    private int recLen = 4;
    private TextView tvTime;
    private String baseUrl;

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

    private void init() {
        setTimer();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeForWebActivity.this, WebViewActivity.class);
                intent.putExtra("url", baseUrl);
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(timerTask,2000);
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
