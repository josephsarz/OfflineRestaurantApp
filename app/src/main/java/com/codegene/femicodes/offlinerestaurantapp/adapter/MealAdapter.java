package com.codegene.femicodes.offlinerestaurantapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codegene.femicodes.offlinerestaurantapp.R;
import com.codegene.femicodes.offlinerestaurantapp.SingleMealActivity;
import com.codegene.femicodes.offlinerestaurantapp.model.Meal;
import com.codegene.femicodes.offlinerestaurantapp.model.Menu;
import com.codegene.femicodes.offlinerestaurantapp.ui.activity.MealsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by femicodes on 5/2/2018.
 */

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    Context mContext;
    List<Meal> mMealList;

    public MealAdapter(Context context, List<Meal> mealList) {
        mContext = context;
        mMealList = mealList;
    }

    @Override
    public MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_row, parent, false);
        return new MealViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MealViewHolder holder, int position) {

        Picasso.get().load(mMealList.get(position).getImage()).into(holder.mMealImage);
        holder.mMealName.setText(mMealList.get(position).getName());
        holder.mMealPrice.setText(String.valueOf(mMealList.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return mMealList.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_meal_name)
        TextView mMealName;

        @BindView(R.id.tv_meal_price)
        TextView mMealPrice;

        @BindView(R.id.iv_meal_image)
        ImageView mMealImage;

        public MealViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener( view ->{

                Meal meal = mMealList.get(getAdapterPosition());

                Intent i = new Intent(mContext, SingleMealActivity.class);
                i.putExtra("name", meal.getName());
                i.putExtra("image", meal.getImage());
                i.putExtra("description", meal.getDescription());
                i.putExtra("price", meal.getPrice());

                mContext.startActivity(i);

            });

        }
    }
}