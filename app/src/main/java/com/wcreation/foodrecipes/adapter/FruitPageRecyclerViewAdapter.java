package com.wcreation.foodrecipes.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wcreation.foodrecipes.MainpageRecipesOpen;
import com.wcreation.foodrecipes.R;
import com.wcreation.foodrecipes.modal.FruitAnime;
import com.wcreation.foodrecipes.modal.RiceAnime;

import java.util.List;

/**
 * Created by Dinesh Wayaman from W Creation on 7/10/2020.
 */

public class FruitPageRecyclerViewAdapter extends RecyclerView.Adapter<FruitPageRecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<FruitAnime> mData ;
    RequestOptions option;

    public FruitPageRecyclerViewAdapter(Context mContext, List<FruitAnime> mData){
        this.mContext = mContext;
        this.mData = mData;

        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.rice_page_anim,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MainpageRecipesOpen.class);
                i.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("anime_ingredients",mData.get(viewHolder.getAdapterPosition()).getIngredients());
                i.putExtra("anime_method",mData.get(viewHolder.getAdapterPosition()).getMethod());
                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_URI());
                mContext.startActivity(i);
            }
        });


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRating());

        Glide.with(mContext).load(mData.get(position).getImage_URI()).apply(option).into(holder.img_thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

       TextView tv_name;
       TextView tv_rating;
       ImageView img_thumbnail;
       LinearLayout view_container;

       public MyViewHolder(@NonNull View itemView) {
           super(itemView);

           view_container = (itemView).findViewById(R.id.containernew);
           tv_name = itemView.findViewById(R.id.anime_name);
           tv_rating = itemView.findViewById(R.id.rating);
           img_thumbnail = itemView.findViewById(R.id.thumbnail);
       }
   }
}
