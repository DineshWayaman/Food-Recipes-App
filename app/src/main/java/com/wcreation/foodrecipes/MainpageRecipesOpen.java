package com.wcreation.foodrecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainpageRecipesOpen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_recipes_open);

        getSupportActionBar().hide();


        String name  = getIntent().getExtras().getString("anime_name");
        String ingredients  = getIntent().getExtras().getString("anime_ingredients");
        String method  = getIntent().getExtras().getString("anime_method");
        String image_url = getIntent().getExtras().getString("anime_img") ;

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);


        ImageView img = findViewById(R.id.aa_thumbnail);
        TextView tv_ingredients = findViewById(R.id.aa_Ingredients);
        TextView tv_method = findViewById(R.id.aa_Method);

        tv_ingredients.setText(ingredients);
        tv_method.setText(method);
        collapsingToolbarLayout.setTitle(name);

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);


        Glide.with(this).load(image_url).apply(requestOptions).into(img);

    }
}
