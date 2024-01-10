package com.example.foodapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyHolder>
{
    private ArrayList<modelProduct> modelProducts;
    private Context c;
    private Bitmap bitmap;

    public AdapterProduct(ArrayList<modelProduct> modelProducts, Context c) {
        this.modelProducts = modelProducts;
        this.c = c;
    }

    @NonNull
    @Override
    public AdapterProduct.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate
                (R.layout.layout_list, parent, false);
        return new MyHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.MyHolder holder, int position)
    {
        modelProduct s = modelProducts.get(position);
        holder.txtfooName.setText(s.getName_product());
        holder.txtfoodDis.setText(s.getDis_product());
        holder.txtfoodPrice.setText(s.getPrice_product());


        String pic = s.getPic_product();

        new uncocoderDownloadImage(holder.imgPicProduct).execute(pic);


    //  Toast.makeText(c,imageName+"",Toast.LENGTH_SHORT).show();

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    final ProgressDialog loading = new ProgressDialog(c,R.style.MyTheme);
                    loading.show();

                    StringRequest request = new StringRequest(Request.Method.POST,
                            Config.cartFood, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            loading.dismiss();

                            if(s.equals("falid"))
                            {
                                Toast.makeText(c, "دوباره امتحان کنید ", Toast.LENGTH_SHORT).show();
                            }
                            else if (s.equals("ok"))
                            {
                                Toast.makeText(c, "به سبد خرید شما اضافه شد", Toast.LENGTH_SHORT).show();
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

                            params.put("id",s.getName_product());
                            params.put("user_name",regestriAct.keyname);
                            params.put("price", s.getPrice_product());
                            params.put("pic", pic);

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
        return modelProducts.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView txtfooName,txtfoodDis,txtfoodPrice;
        ImageView btnAdd,imgPicProduct;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            txtfooName=itemView.findViewById(R.id.txtfooName);
            txtfoodDis=itemView.findViewById(R.id.txtfoodDis);
            txtfoodPrice=itemView.findViewById(R.id.txtfoodPrice);
            btnAdd=itemView.findViewById(R.id.btndelete);
            imgPicProduct=itemView.findViewById(R.id.imgPicProduct);
        }
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
