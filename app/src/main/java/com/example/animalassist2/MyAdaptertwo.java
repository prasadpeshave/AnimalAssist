package com.example.animalassist2;

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

public class MyAdaptertwo extends RecyclerView.Adapter<MyAdaptertwo.MyViewHolder> {

    private ArrayList<Model> mList;
    private Context context;


    public MyAdaptertwo(Context context , ArrayList<Model> mList){

        this.context = context;
        this.mList = mList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item , parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(mList.get(position).getImgUrl()).into(holder.imageView);
        String ani= mList.get(position).getAnim();
        String locc= mList.get(position).getLocation();
        String des= mList.get(position).getDescp();
        String urge= mList.get(position).getUrgency();
        String fintext="Type of animal : " + ani + System.getProperty("line.separator") + "Location : " + locc +System.getProperty("line.separator")+ "Description " + des + locc +System.getProperty("line.separator")+locc +System.getProperty("line.separator")+ "Urgency " + urge;
        holder.text.setText(fintext);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView text;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.m_image);
            text= itemView.findViewById(R.id.deets);
        }
    }
}