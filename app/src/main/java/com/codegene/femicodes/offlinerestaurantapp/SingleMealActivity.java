package com.codegene.femicodes.offlinerestaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleMealActivity extends AppCompatActivity {

    @BindView(R.id.tv_meal_price_d)
    TextView mMealPriceD;

    @BindView(R.id.tv_meal_description_d)
    TextView mMealDescriptionD;

    @BindView(R.id.iv_meal_image_d)
    ImageView mMealImageD;



    String name, description;
    int image;
    Float price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if(intent != null){

            name = intent.getStringExtra("name");
            description = intent.getStringExtra("description");
            image = intent.getIntExtra("image", 0);
            price = intent.getFloatExtra("price", 0);
            getSupportActionBar().setTitle(name);
            mMealDescriptionD.setText(description);
            Picasso.get().load(image).into(mMealImageD);
            mMealPriceD.setText(String.valueOf(price));

        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.btn_add_to_cart)
    public void addToCart(){
        Toast.makeText(this, "added to cart", Toast.LENGTH_SHORT).show();
    }

}
