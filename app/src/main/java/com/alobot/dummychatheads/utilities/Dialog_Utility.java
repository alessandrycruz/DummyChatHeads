package com.alobot.dummychatheads.utilities;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.alobot.dummychatheads.interfaces.SimpleDialog_Interface;


public class Dialog_Utility {
    public void simpleDialog(String messageText, String positiveButtonText, String negativeButtonText, Context context,
                             final SimpleDialog_Interface simpleDialog_Interface) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageText);
        builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                simpleDialog_Interface.onPositiveClick();
            }
        });
        builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                simpleDialog_Interface.onNegativeClick();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                simpleDialog_Interface.onNegativeClick();
            }
        });
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                simpleDialog_Interface.onDismiss();
            }
        });
        builder.create();
        builder.show();
    }
}
