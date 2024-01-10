package com.example.foodapp.ui.account;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp.Config;
import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentAccountBinding;
import com.example.foodapp.regestriAct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AccountFragment extends Fragment implements View.OnClickListener{

    public static SharedPreferences sharedPreferencesLogin;
    ProgressDialog progressDialog;
    private FragmentAccountBinding binding;
    public static String userName="";
    EditText rowtxtaddress;


    Button btnSubmit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccountViewModel accountViewModel =
                new ViewModelProvider(this).get(AccountViewModel.class);

        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnSubmit=binding.btnSubmit.findViewById(R.id.btnSubmit);
        rowtxtaddress=binding.rowtxtaddress.findViewById(R.id.rowtxtaddress);

        btnSubmit.setOnClickListener(this);

        RequestForDataUser();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.btnSubmit:
            {
                saveAddress();
                break;
            }


        }

    }

    void saveAddress()
    {
        progressDialog = new ProgressDialog(getContext(), R.style.MyTheme);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.saveAddress, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progressDialog.dismiss();
                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",regestriAct.keyname);
                params.put("address", rowtxtaddress.getText().toString().trim());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }

    void RequestForDataUser()
    {
        progressDialog = new ProgressDialog(getContext(), R.style.MyTheme);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST, Config.selectInfoUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                showJsonForDataUser(response);
                progressDialog.dismiss();
                //Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("flagUserName",regestriAct.keyname);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }

    private void showJsonForDataUser(String response)
    {


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                binding.rowtxtUserName.setText(jsonObject1.getString("user_name"));
                binding.rowtxtPass.setText(jsonObject1.getString("password"));
                binding.rowtxtdisplayname.setText(jsonObject1.getString("display_name"));
                binding.rowtxtphone.setText(jsonObject1.getString("phone"));
                binding.rowtxtaddress.setText(jsonObject1.getString("adress"));
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }


}