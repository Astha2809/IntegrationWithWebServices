package com.example.integrationwithwebservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    ArrayList<Post> arrayList = new ArrayList<>();
    Context context;


              public MyAdapter(ArrayList<Post> arrayList, Context context) {
                  this.context=context;
                  this.arrayList=arrayList;

              }

              @NonNull
              @Override
              public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                  View view= LayoutInflater.from(context).inflate(R.layout.list_display,parent,false);

                  return new ViewHolder(view);
              }

              @Override
              public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
                  Post listItems = arrayList.get(position);
                  String name=listItems.getName();
                  String  message=listItems.getMessage();
                  String url=listItems.getProfile();
                  holder.textviewname.setText(name);
                  holder.textviewmessage.setText(message);
                  Glide.with(context)
                          .load(url)
                          .into(holder.imageView);




              }

              @Override
              public int getItemCount() {
                  return arrayList.size();
              }

              public class ViewHolder extends RecyclerView.ViewHolder {
                  TextView textviewname;
                  TextView textviewmessage;
                  ImageView imageView;

                  public ViewHolder(@NonNull View itemView) {
                      super(itemView);
                      textviewmessage=itemView.findViewById(R.id.message);
                      textviewname=itemView.findViewById(R.id.name);
                      imageView=itemView.findViewById(R.id.imageview);

                  }
              }
          }

