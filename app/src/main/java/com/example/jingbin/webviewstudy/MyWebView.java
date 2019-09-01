package com.example.jingbin.webviewstudy;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.tencent.smtt.sdk.WebView;

public class MyWebView extends WebView {


    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (swipeRefreshLayout != null) {
            Log.e("cww", "l:" + l + ",,,t:" + t + ",,,oldl:" + oldl + ",,,oldt:" + oldt);
            if (t== 0) {
                Log.e("cww", this.getScrollY() + ":qqqqqqqqqqqqqqqqqqqqqqq");
                swipeRefreshLayout.setEnabled(true);
            } else {
                Log.e("cww", this.getScrollY() + ":eeeeeeeeeeeeeeeeeeeeeee");
                swipeRefreshLayout.setEnabled(false);
            }
        }

    }


    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (this.getScrollY() <= 0)
                    this.scrollTo(0, 1);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }
}
