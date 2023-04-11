package com.example.doanmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanmobile.R;
import com.example.doanmobile.ViewDetailProductActivity;
import com.example.doanmobile.fragment.CartFragment;
import com.example.doanmobile.model.Product;
import com.example.doanmobile.model.ProductCart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private ArrayList<Product> productList;
    private Context context;


    public ProductListAdapter(ArrayList<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.product_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.name.setText(product.getName());
        Picasso.get().load(product.getImg()).resize(300, 300).centerCrop().into(holder.img);
        holder.price.setText("$" + product.getPrice());
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewCartAct = new Intent(context, CartFragment.class);
                context.startActivity(viewCartAct);
                Toast.makeText(context, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewDetailProductActivity.class);
                intent.putExtra("imageSrc", product.getImg());
                intent.putExtra("name", product.getName());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("price", product.getPrice());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView img, btnAdd;
        public TextView price;
        public RelativeLayout productList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productName);
            img = itemView.findViewById(R.id.imageViewSrc);
            price = itemView.findViewById(R.id.productPrice);
            productList = itemView.findViewById(R.id.productListView);
            btnAdd = itemView.findViewById(R.id.addBtn);
        }
    }
}
