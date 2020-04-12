package com.tanaka.mazivanhanga.youtubeplaylist.utils;

import android.app.ProgressDialog;
import android.content.Context;

import static com.tanaka.mazivanhanga.youtubeplaylist.utils.Constants.LOADING;

public class ProgDialog {
    private ProgressDialog progressDialog;
    private Context context;

    public ProgDialog(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(LOADING);
    }

    public void show() {
        progressDialog.show();
    }

    public void hide() {
        progressDialog.hide();
        progressDialog.dismiss();
    }
}
