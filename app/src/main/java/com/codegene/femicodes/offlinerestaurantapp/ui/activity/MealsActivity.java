package com.codegene.femicodes.offlinerestaurantapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.codegene.femicodes.offlinerestaurantapp.R;
import com.codegene.femicodes.offlinerestaurantapp.adapter.MealAdapter;
import com.codegene.femicodes.offlinerestaurantapp.db.AppDatabase;
import com.codegene.femicodes.offlinerestaurantapp.model.Meal;
import com.codegene.femicodes.offlinerestaurantapp.util.CommonUtil;

import java.util.List;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MealsActivity extends AppCompatActivity {

    @BindView(R.id.rv_meal_list)
    RecyclerView mMealRecyclerView;
    MealAdapter mMealAdapter;
    List<Meal> mMealList;
    String menuName = null;
    int menu_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent != null){
            menuName = intent.getStringExtra("menuName");
            menu_id = intent.getIntExtra("menuId", 0);
            Toast.makeText(this, "id: "+menu_id, Toast.LENGTH_SHORT).show();
            getSupportActionBar().setTitle(menuName);
        }

        Executors.newSingleThreadExecutor().execute(() -> {

            mMealList = AppDatabase.getDatabase(getApplicationContext()).mMealDao().getAllMealsById(menu_id);
            runOnUiThread(new Thread(this::setupRecyclerView));

        });

    }

    public void setupRecyclerView(){
        mMealRecyclerView.setLayoutManager(new LinearLayoutManager(mMealRecyclerView.getContext()));
        mMealRecyclerView.setHasFixedSize(true);
        mMealAdapter = new MealAdapter(mMealRecyclerView.getContext(), mMealList);
        mMealRecyclerView.setAdapter(mMealAdapter);
        mMealAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_populate_meal)
    public void populateMealsDatabase() {

        CommonUtil.populateMealDatabase(getApplicationContext());
        setupRecyclerView();
    }






}
