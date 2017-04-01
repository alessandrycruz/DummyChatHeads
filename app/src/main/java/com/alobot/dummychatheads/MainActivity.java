package com.alobot.dummychatheads;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alobot.dummychatheads.interfaces.SimpleDialog_Interface;
import com.alobot.dummychatheads.services.ChatHeadService;
import com.alobot.dummychatheads.utilities.Dialog_Utility;

public class MainActivity extends AppCompatActivity {
    private static final int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 666;

    private Context mContext;
    private Dialog_Utility mDialog_Utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        mDialog_Utility = new Dialog_Utility();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Settings.canDrawOverlays(mContext)) {
            startService(new Intent(mContext, ChatHeadService.class));
        } else {
            showDrawingOtherAppsDialog();
        }
    }

    private void showDrawingOtherAppsDialog() {
        mDialog_Utility.simpleDialog("To show chat heads, allow access to drawing other apps.", "CONTINUE", "NOT NOW", mContext, new SimpleDialog_Interface() {
            @Override
            public void onPositiveClick() {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }

            @Override
            public void onNegativeClick() {
                Toast.makeText(mContext, "Permission to allow access to drawing other apps is necessary.", Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onDismiss() {
            }
        });
    }
}
