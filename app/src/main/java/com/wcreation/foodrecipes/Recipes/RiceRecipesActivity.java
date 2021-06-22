package com.wcreation.foodrecipes.Recipes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.wcreation.foodrecipes.MainActivity;
import com.wcreation.foodrecipes.R;
import com.wcreation.foodrecipes.adapter.MainPageRecyclerViewAdapter;
import com.wcreation.foodrecipes.adapter.RicePageRecyclerViewAdapter;
import com.wcreation.foodrecipes.modal.Anime;
import com.wcreation.foodrecipes.modal.RiceAnime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RiceRecipesActivity extends AppCompatActivity {

    private final String JSON_URL = "https://drive.google.com/u/0/uc?id=1srLEEGedCgN3la1VhlzlvDKst8nPjKr9&export=download";
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<RiceAnime> lstAnime ;
    private RecyclerView recyclerView ;
    ProgressBar progressBar;

    private final String TAG = CakeRecipesActivity.class.getSimpleName();
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice_recipes);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("\uD83C\uDF5B\uD83C\uDF72 Rice Recipes \uD83C\uDF72\uD83C\uDF5B");


        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        lstAnime = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewiRice);
        jsonrequest();



        AudienceNetworkAds.initialize(this);

        interstitialAd = new InterstitialAd(this, "725967878189700_725968501522971");
        // Set listeners for the Interstitial Ad
        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.e(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.e(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        });

        // For auto play video ads, it's recommended to load the ad
        // at least 30 seconds before it is shown
        interstitialAd.loadAd();




    }



    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;
                        RiceAnime anime = new RiceAnime() ;
                        anime.setName(jsonObject.getString("name"));
                        anime.setIngredients(jsonObject.getString("Ingredients"));
                        anime.setMethod(jsonObject.getString("Method"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setImage_URI(jsonObject.getString("img"));
                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstAnime);
                progressBar.setVisibility(View.INVISIBLE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(RiceRecipesActivity.this);
        requestQueue.add(request) ;
    }

    private void setuprecyclerview(List<RiceAnime> lstAnime) {


        RicePageRecyclerViewAdapter myadapter = new RicePageRecyclerViewAdapter(this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}
