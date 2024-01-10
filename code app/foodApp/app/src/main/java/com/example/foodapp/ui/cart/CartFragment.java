package com.example.foodapp.ui.cart;



import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.foodapp.AdapterCart;
import com.example.foodapp.Config;
import com.example.foodapp.R;
import com.example.foodapp.databinding.FragmentCartBinding;
import com.example.foodapp.modelCart;
import com.example.foodapp.regestriAct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;

    public static RecyclerView rcCart;
    public static Spinner spFilter;
   public static TextView txtprice;
    public static ArrayList<modelCart> modelCarts = new ArrayList<>();
    public static int sum=0,  price;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CartViewModel cartViewModel =
                new ViewModelProvider(this).get(CartViewModel.class);

        binding = FragmentCartBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        rcCart=binding.rcCart.findViewById(R.id.rcCart);
        spFilter=binding.spFilter.findViewById(R.id.spFilter);
        txtprice=binding.txtprice.findViewById(R.id.txtprice);

       spFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                getCart();
                if(spFilter.getSelectedItem().equals("سبد خرید"))
                {
                    binding.btnSubmitCart.setEnabled(false);
                }
                else
                {
                    binding.btnSubmitCart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        shoDataCart();
        getCartPrice();

        binding.btnSubmitCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext())
                        .setTitle("ثبت سفارش")
                        .setMessage( "سفارش شما با موفقیت ثبت گردید . غذای شما تا نیم ساعت دیگر ارسال میشود"+"  "+regestriAct.keyname+ " " )
                        .setIcon(R.drawable.logo)
                        .setNegativeButton("لغو سفارش", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("تایید سفارش", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                submitCart();
                               // binding.btnSubmitCart.setEnabled(false);

                            }
                        });
                alertDialog.create().show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    public static void shoDataCart()
    {

        AdapterCart adapterCart =new AdapterCart(modelCarts, rcCart.getContext());
        rcCart.setLayoutManager(new LinearLayoutManager(rcCart.getContext()));
        rcCart.addItemDecoration(new DividerItemDecoration(rcCart.getContext(), LinearLayoutManager.VERTICAL));
        rcCart.setItemAnimator(new DefaultItemAnimator());
        rcCart.setAdapter(adapterCart);

    }

    public void getCart()
    {
        final ProgressDialog loading = new ProgressDialog(getContext(), R.style.MyTheme);
        loading.show();
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.getCart, new Response.Listener<String>() {
            @Override
            public void onResponse(String s)
            {
                loading.dismiss();
                 //Toast.makeText(getContext(),s, Toast.LENGTH_SHORT).show();
                showJSON(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                if(spFilter.getSelectedItem().equals("سبد خرید"))
                {
                    params.put("flag","1");
                }
                else if(spFilter.getSelectedItem().equals("سفارشات"))
                {
                    params.put("flag","0");
                }

                params.put("username", regestriAct.keyname);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    public void showJSON(String s){
        modelCarts.clear();
        String responseString = s;


        try {
            JSONObject jsonObject1 = new JSONObject(responseString);
            JSONArray result = jsonObject1.getJSONArray("result");
            for (int i = 0; i < result.length(); i++) {
                try {
                    JSONObject jsonObject = result.getJSONObject(i);

                    String id_user = (jsonObject.getString("id_user"));
                    String id_product = (jsonObject.getString("id_product"));
                     price = Integer.parseInt((jsonObject.getString("price")));
                     String pic = ((jsonObject.getString("pic_product")));


                    modelCart o = new modelCart(id_product,id_user,price,pic);
                    modelCarts.add(o);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            if (modelCarts.size() > 0)
            {
                shoDataCart();
                binding.btnSubmitCart.setEnabled(true);
                binding.txtnullcart.setVisibility(View.GONE);
            }
            else if(modelCarts.size()==0)
            {
                binding.btnSubmitCart.setEnabled(false);
                binding.txtnullcart.setVisibility(View.VISIBLE);

            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void getCartPrice()
    {
        final ProgressDialog loading = new ProgressDialog(getContext(), R.style.MyTheme);
        loading.show();
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.sumCart, new Response.Listener<String>() {
            @Override
            public void onResponse(String s)
            {
                loading.dismiss();
                try
                {
                    JSONObject jsonObject1 = new JSONObject(s);
                    JSONArray result = jsonObject1.getJSONArray("price");

                    for (int i = 0; i < result.length(); i++)
                    {
                        try
                        {
                            JSONObject jsonObject = result.getJSONObject(i);
                            txtprice.setText(jsonObject.getString("price")+ "  " +"مبلغ کل (تومان)");
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("username", regestriAct.keyname);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }

    public  void submitCart()
    {
        final ProgressDialog loading = new ProgressDialog(getContext(), R.style.MyTheme);
        loading.show();
        StringRequest request = new StringRequest(Request.Method.POST,
                Config.submitCart, new Response.Listener<String>() {
            @Override
            public void onResponse(String s)
            {
                loading.dismiss();
                Toast.makeText(getContext(),s, Toast.LENGTH_SHORT).show();
                if(s.equals("ارسال شد "))
                {
                    modelCarts.clear();
                    binding.btnSubmitCart.setEnabled(false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", regestriAct.keyname);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}