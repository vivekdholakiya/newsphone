package com.example.newsify;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{

    Context context;
    ArrayList<Modal>modalArrayList;

    public adapter(Context context, ArrayList<Modal> modalArrayList) {
        this.context = context;
        this.modalArrayList = modalArrayList;
    }

    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layoutitem, null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,webview.class);
                intent.putExtra("url",modalArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });


        holder.mtime.setText("Published At:"+modalArrayList.get(position).getPublishedAt());
        holder.mauthor.setText(modalArrayList.get(position).getAuthor());
        holder.mheading.setText(modalArrayList.get(position).getTitle());
        holder.mcontent.setText(modalArrayList.get(position).getDescription());
        Glide.with(context).load(modalArrayList.get(position).getUrlToImage()).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return modalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mheading,mcontent,mauthor,mtime;
        CardView cardView;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mheading= itemView.findViewById(R.id.mainh);
            mcontent= itemView.findViewById(R.id.content);
            mauthor= itemView.findViewById(R.id.author);
            mtime= itemView.findViewById(R.id.time);
            imageView= itemView.findViewById(R.id.img);
            cardView= itemView.findViewById(R.id.cardview);
        }
    }
}

