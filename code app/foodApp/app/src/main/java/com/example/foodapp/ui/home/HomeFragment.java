package com.example.foodapp.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp.AdapterProduct;
import com.example.foodapp.Config;
import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentHomeBinding;
import com.example.foodapp.modelProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;

    ImageView btnPiza,btnAstak,btnBerger;
    ProgressDialog progressDialog;
    public static ArrayList<modelProduct> modelProducts = new ArrayList<>();
    public static RecyclerView rcproduct;

    public String type ="برگر";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rcproduct= binding.rcproduct.findViewById(R.id.rcproduct);

        btnPiza=binding.btnPiza.findViewById(R.id.btnPiza);
        btnAstak=binding.btnAstak.findViewById(R.id.btnAstak);
        btnBerger=binding.btnBerger.findViewById(R.id.btnBerger);

        btnBerger.setOnClickListener(this);
        btnAstak.setOnClickListener(this);
        btnPiza.setOnClickListener(this);
        modelProducts.clear();
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
            case R.id.btnPiza:
            {
                modelProducts.clear();
                RequestForDataUser();
                type ="پیتزا";
                break;
            }
            case R.id.btnAstak:
            {
                modelProducts.clear();
                RequestForDataUser();
                type ="استیک";
                break;
            }
            case R.id.btnBerger:
            {
                modelProducts.clear();
                RequestForDataUser();
                type ="برگر";
                break;
            }

        }
    }
    public static void Showfood()
    {
        AdapterProduct adapterComment =new AdapterProduct(modelProducts, rcproduct.getContext());
        rcproduct.setLayoutManager(new LinearLayoutManager(rcproduct.getContext()));
        rcproduct.addItemDecoration(new DividerItemDecoration(rcproduct.getContext(), LinearLayoutManager.VERTICAL));
        rcproduct.setItemAnimator(new DefaultItemAnimator());
        rcproduct.setAdapter(adapterComment);

    }

    void RequestForDataUser()
    {
        progressDialog = new ProgressDialog(getContext(), R.style.MyTheme);
        progressDialog.show();
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.selectfood, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                showJSON(response);
                progressDialog.dismiss();
                  // Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
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
              params.put("type", type);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);

    }

    public void showJSON(String s){
        modelProducts.clear();



        try {
            JSONObject jsonObject1 = new JSONObject(s);
            JSONArray result = jsonObject1.getJSONArray("response");
            for (int i = 0; i < result.length(); i++)
            {
                try
                {

                    JSONObject jsonObject = result.getJSONObject(i);
                    String name_product =(jsonObject.getString("name_product"));
                    String price_product = (jsonObject.getString("price_product"))+"تومان";
                    String dis_product = (jsonObject.getString("dis_product"));
                    String pic_product=(jsonObject.getString("pic_product"));



                    modelProduct o = new modelProduct(name_product,price_product,dis_product,pic_product);
                    modelProducts.add(o);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            if (modelProducts.size() > 0)
            {
                Showfood();
                Toast.makeText(getContext(),modelProducts.size()+"",Toast.LENGTH_SHORT);
            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

}