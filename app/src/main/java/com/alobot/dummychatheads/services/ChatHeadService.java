package com.alobot.dummychatheads.services;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.alobot.dummychatheads.R;

public class ChatHeadService extends Service {
    private ImageView imageViewChatHead;
    private WindowManager windowManager;
    private WindowManager.LayoutParams windowManagerLayoutParams;

    @Override

    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        imageViewChatHead = new ImageView(this);
        imageViewChatHead.setImageResource(R.mipmap.ic_launcher);

        windowManagerLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);

        windowManagerLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        windowManagerLayoutParams.x = 0;
        windowManagerLayoutParams.y = 100;

        // Handles the movement of the chat head
        imageViewChatHead.setOnTouchListener(new View.OnTouchListener() {
            private float initialTouchX;
            private float initialTouchY;
            private int initialX;
            private int initialY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = windowManagerLayoutParams.x;
                        initialY = windowManagerLayoutParams.y;

                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();

                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        windowManagerLayoutParams.x = initialX + (int) (event.getRawX() - initialTouchX);
                        windowManagerLayoutParams.y = initialY + (int) (event.getRawY() - initialTouchY);

                        windowManager.updateViewLayout(imageViewChatHead, windowManagerLayoutParams);

                        return true;
                }

                return false;
            }
        });

        windowManager.addView(imageViewChatHead, windowManagerLayoutParams);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (imageViewChatHead != null) {
            windowManager.removeView(imageViewChatHead);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
