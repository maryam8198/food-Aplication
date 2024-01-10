package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class regestriAct extends AppCompatActivity implements View.OnClickListener {

    public static SharedPreferences sharedPreferencesLogin;
    EditText edUserName,eddisplayname,edphone,edPassword;
    Button btnLogin;
    public static String keyname ="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regestri);
        edUserName=findViewById(R.id.edUserName);
        eddisplayname=findViewById(R.id.eddisplayname);
        edphone=findViewById(R.id.eddphone);
        edPassword=findViewById(R.id.edPassword);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

        sharedPreferencesLogin=getSharedPreferences("login",MODE_PRIVATE);
        if(sharedPreferencesLogin.getString("status","").equals("ok"))
        {
            ChengeActivity();
        }

    }

    @Override
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.btnLogin:
            {
                RequestLogin();
                break;
            }
        }
    }

    public void RequestLogin()
    {

        final ProgressDialog loading = new ProgressDialog(this,R.style.MyTheme);
        loading.show();

        StringRequest request = new StringRequest(Request.Method.POST,
                Config.registerApi, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                loading.dismiss();

           //     Toast.makeText(regestriAct.this, s+"", Toast.LENGTH_SHORT).show();

                if(s.equals("empity"))
                {
                    Toast.makeText(getApplicationContext(), "اطلاعات خود را وارد کنید ", Toast.LENGTH_SHORT).show();
                }
                else if (s.equals("user is added"))
                {Toast.makeText(regestriAct.this, s+"", Toast.LENGTH_SHORT).show();
                    StateLogin();
                    startActivity(new Intent(regestriAct.this, MainActivity.class));
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(regestriAct.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                keyname=edUserName.getText().toString();
                params.put("username", edUserName.getText().toString().trim());
                params.put("display_name", eddisplayname.getText().toString().trim());
                params.put("phone", edphone.getText().toString().trim());
                params.put("password", edPassword.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(regestriAct.this);
        requestQueue.add(request);

    }

    public void StateLogin()
    {
        sharedPreferencesLogin.edit().putString("status","ok")
                .putString("username",edUserName.getText().toString().trim()).commit();
        ChengeActivity();
    }

    private void ChengeActivity()
    {
        keyname=sharedPreferencesLogin.getString("username","");
        startActivity(new Intent(getBaseContext(),MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        // this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}