package com.example.foodapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

public class spelashAct extends AppCompatActivity {

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelash);

        checkConnection();

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {

            return false;
        }
    }

    public void checkConnection() {
        if (isOnline()) {
            splashshow();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("اتصال به اینترنت")
                    .setMessage("اتصال به اینترنت برقرار نمی‌باشد")
//                    .setIcon(android.R.drawable.)
                    .setPositiveButton("سعی مجدد", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkConnection();
                        }
                    })
                    .setNegativeButton("خروج از برنامه", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    void splashshow() {
        progressDialog = new ProgressDialog(this,R.style.MyTheme);
        progressDialog.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.

                Intent mInHome = new Intent(spelashAct.this, regestriAct.class);
                spelashAct.this.startActivity(mInHome);
                progressDialog.dismiss();
                spelashAct.this.finish();


            }
        }, 3000);
    }
}