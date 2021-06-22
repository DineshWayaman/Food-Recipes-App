package com.wcreation.foodrecipes;

/**
 * Created by Dinesh Wayaman from W Creation on 7/10/2020.
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.navigation.NavigationView;
import com.wcreation.foodrecipes.Recipes.CakeRecipesActivity;
import com.wcreation.foodrecipes.Recipes.FruitRecipesActivity;
import com.wcreation.foodrecipes.Recipes.MeatRecipesActivity;
import com.wcreation.foodrecipes.Recipes.RiceRecipesActivity;
import com.wcreation.foodrecipes.Recipes.VegRecipesActivity;
import com.wcreation.foodrecipes.adapter.MainPageRecyclerViewAdapter;
import com.wcreation.foodrecipes.modal.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private final String JSON_URL = "https://drive.google.com/u/0/uc?id=19BfoT913Va2ONcBrYDlNAGyOLpwaYf9t&export=download";
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Anime> lstAnime ;
    private RecyclerView recyclerView ;
    ImageSlider imageSlider;
    ProgressBar progressBar;
    private Button btnRice,btnMeat,btnCake,btnFruit,btnVeg;

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (!isConnected()) {
           // new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                   // .setTitle("Internet Connection Alert(W Creation)")
                  //  .setMessage("Please Check Your Internet Connection.")
                  //  .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    //    @Override
                    //    public void onClick(DialogInterface dialog, int which) {
                   //         finish();
                   //     }
                  //  });
            AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(this);
            alertdialogBuilder.setTitle("Internet Connection Problem");

            alertdialogBuilder.setIcon(android.R.drawable.ic_dialog_alert);
            alertdialogBuilder.setMessage("Please Check Your Internet Connection.");
            alertdialogBuilder.setCancelable(false);
            alertdialogBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertdialogBuilder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(MainActivity.this, "Close", Toast.LENGTH_SHORT).show();
                    recreate();
                }
            });
            AlertDialog alertDialog=alertdialogBuilder.create();
            alertDialog.show();



        } else {

            imageSlider = (ImageSlider) findViewById(R.id.slider);
            progressBar = (ProgressBar) findViewById(R.id.progressbar);
            btnRice = (Button) findViewById(R.id.btnRice);
            btnMeat = (Button) findViewById(R.id.btnMeat);
            btnCake = (Button) findViewById(R.id.btnCake);
            btnFruit = (Button) findViewById(R.id.btnFruit);
            btnVeg = (Button) findViewById(R.id.btnVeg);

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            navigationView = (NavigationView) findViewById(R.id.navmenu);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();


            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.riceRecipes:
                            Intent i = new Intent(MainActivity.this, RiceRecipesActivity.class);
                            startActivity(i);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.home:
                            Intent home = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(home);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.meatRecipes:
                            Intent i3 = new Intent(MainActivity.this, MeatRecipesActivity.class);
                            startActivity(i3);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.cakeRecipes:
                            Intent i2 = new Intent(MainActivity.this, CakeRecipesActivity.class);
                            startActivity(i2);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.fruitRecipes:
                            Intent i4 = new Intent(MainActivity.this, FruitRecipesActivity.class);
                            startActivity(i4);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.vegRecipes:
                            Intent ichem = new Intent(MainActivity.this, VegRecipesActivity.class);
                            startActivity(ichem);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.aboutUs:
                            Intent aboutus = new Intent(MainActivity.this, AboutUsActivity.class);
                            startActivity(aboutus);
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;

                        case R.id.rateApp:
                            // String url2 = "https://drive.google.com/file/d/1AnSQ_boqsK3zzoFcJjB-awtZ1KWITWtM/view?usp=sharing";
                            //  Intent i2 = new Intent(Intent.ACTION_VIEW);
                            //  i2.setData(Uri.parse(url2));
                            //  startActivity(i2);
                            //  return true;
                            final String appPackageName = getPackageName();
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                            return false;

                        case R.id.faceBookPage:
                            String url = "https://www.facebook.com/Wayaman99";
                            Intent face = new Intent(Intent.ACTION_VIEW);
                            face.setData(Uri.parse(url));
                            startActivity(face);
                            return false;

                        case R.id.sharaApp:
                            final String shareappPackageName = getPackageName();
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out " + getResources().getString(R.string.app_name) + " App at: https://play.google.com/store/apps/details?id=" + shareappPackageName);
                            sendIntent.setType("text/plain");
                            startActivity(sendIntent);
                            return false;


                        case R.id.exit:
                            exit();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            break;


                    }

                    return true;
                }
            });


            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/web-to-app-eb619.appspot.com/o/Food%20App%20Slide%2Fslide11.jpg?alt=media&token=a16bf6e9-adec-45e6-882d-48a2c26e7ad6", "Rice Recipes"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/web-to-app-eb619.appspot.com/o/Food%20App%20Slide%2Fslide2.jpg?alt=media&token=bce5ac25-bf7d-4b45-ba6c-d4dd2277fb41", "Meat Recipes"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/web-to-app-eb619.appspot.com/o/Food%20App%20Slide%2Fslide3.jpg?alt=media&token=2dcda7cf-b316-48f0-8e48-9aaeccc321fc", "Cake Recipes"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/web-to-app-eb619.appspot.com/o/Food%20App%20Slide%2Fslide4.jpg?alt=media&token=36cea295-fc74-4304-bc94-903917474d86", "Fruit Recipes"));
            slideModels.add(new SlideModel("https://firebasestorage.googleapis.com/v0/b/web-to-app-eb619.appspot.com/o/Food%20App%20Slide%2Fslide5.jpg?alt=media&token=64692e6a-704e-4903-b88f-dc39b0dc19e8", "Vegetarian Recipes"));
            imageSlider.setImageList(slideModels, true);


            lstAnime = new ArrayList<>();
            recyclerView = findViewById(R.id.recyclerView);

            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(manager);

            jsonrequest();


            btnRice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, RiceRecipesActivity.class);
                    startActivity(i);
                }
            });

            btnMeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, MeatRecipesActivity.class);
                    startActivity(i);
                }
            });

            btnCake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, CakeRecipesActivity.class);
                    startActivity(i);
                }
            });
            btnFruit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, FruitRecipesActivity.class);
                    startActivity(i);
                }
            });
            btnVeg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, VegRecipesActivity.class);
                    startActivity(i);
                }
            });

        }
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Anime anime = new Anime() ;
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


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;
    }

    private void setuprecyclerview(List<Anime> lstAnime) {


        MainPageRecyclerViewAdapter myadapter = new MainPageRecyclerViewAdapter(this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }

    private void exit(){

        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(this);
        alertdialogBuilder.setTitle("Confirm Your Exit.!!");

        alertdialogBuilder.setIcon(R.drawable.ic_exit);
        alertdialogBuilder.setMessage("Are you sure you want to exit ?");
        alertdialogBuilder.setCancelable(false);
        alertdialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertdialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "❤❤ You are back ❤❤", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=alertdialogBuilder.create();
        alertDialog.show();
    }


    private boolean isConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo!= null && networkInfo.isConnected();
    }

    @Override
    public void onBackPressed() {
        exit();
    }

}
