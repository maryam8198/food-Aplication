package com.example.foodapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodapp.ui.cart.CartFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyHolder>
{
    private ArrayList<modelCart> modelCarts;
    private Context c;
    int position1;

    public AdapterCart(ArrayList<modelCart> modelCarts, Context c) {
        this.modelCarts = modelCarts;
        this.c = c;
    }

    @NonNull
    @Override
    public AdapterCart.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(c).inflate
                (R.layout.layout_cart, parent, false);
        return new AdapterCart.MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.MyHolder holder, int position)
    {
        modelCart s = modelCarts.get(position);
        position1=(position);

        holder.txtfooName.setText(s.getId_product());
        holder.txtfoodPrice.setText(s.getPrice()+"");

        String pic = s.getPic_product();

        new AdapterCart.uncocoderDownloadImage(holder.imgPicProduct).execute(pic);

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog loading = new ProgressDialog(c,R.style.MyTheme);
                loading.show();

                StringRequest request = new StringRequest(Request.Method.POST,
                        Config.deleteProductCart, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String s)
                    {
                        loading.dismiss();

                            if(s.equals("محصول با موفقیت حذف شد"))
                            {
                                Toast.makeText(c, s+"", Toast.LENGTH_SHORT).show();

                                modelCarts.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position1,modelCarts.size());
                                getCartPrice();
                            }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(c, volleyError.toString(), Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("username",s.getId_user());
                        params.put("id_product",s.getId_product());


                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(c);
                requestQueue.add(request);


            }
        });


    }

    @Override
    public int getItemCount() {
        return modelCarts.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView txtfooName,txtfoodPrice;
        ImageView btndelete,imgPicProduct;

        public MyHolder(@NonNull View itemView)
        {
            super(itemView);


            txtfooName=itemView.findViewById(R.id.txtfooName);
            txtfoodPrice=itemView.findViewById(R.id.txtfoodPrice);
            btndelete=itemView.findViewById(R.id.btndelete);
            imgPicProduct=itemView.findViewById(R.id.imgPicProduct);
        }
    }


    public void getCartPrice()
    {
        final ProgressDialog loading = new ProgressDialog(c, R.style.MyTheme);
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
                            CartFragment.txtprice.setText(jsonObject.getString("price"));
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
                Toast.makeText(c, volleyError.toString(), Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(request);
    }

    private class uncocoderDownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public uncocoderDownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
