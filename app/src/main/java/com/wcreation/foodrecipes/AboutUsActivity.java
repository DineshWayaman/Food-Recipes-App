package com.wcreation.foodrecipes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.wcreation.foodrecipes.Recipes.CakeRecipesActivity;
import com.wcreation.foodrecipes.Recipes.FruitRecipesActivity;
import com.wcreation.foodrecipes.Recipes.MeatRecipesActivity;
import com.wcreation.foodrecipes.Recipes.RiceRecipesActivity;
import com.wcreation.foodrecipes.Recipes.VegRecipesActivity;

public class AboutUsActivity extends AppCompatActivity {

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        toolbar = (Toolbar) findViewById(R.id.toolbarAbout);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.navmenuAbout);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerAbout);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {

                    case R.id.home:
                        Intent home = new Intent(AboutUsActivity.this,MainActivity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.riceRecipes:
                        Intent i = new Intent(AboutUsActivity.this, RiceRecipesActivity.class);
                        startActivity(i);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.meatRecipes:
                        Intent i3 = new Intent(AboutUsActivity.this, MeatRecipesActivity.class);
                        startActivity(i3);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.cakeRecipes:
                        Intent i2 = new Intent(AboutUsActivity.this, CakeRecipesActivity.class);
                        startActivity(i2);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.fruitRecipes:
                        Intent i4 = new Intent(AboutUsActivity.this, FruitRecipesActivity.class);
                        startActivity(i4);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.vegRecipes:
                        Intent ichem = new Intent(AboutUsActivity.this, VegRecipesActivity.class);
                        startActivity(ichem);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.aboutUs:
                        Intent aboutus = new Intent(AboutUsActivity.this, AboutUsActivity.class);
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
                Toast.makeText(AboutUsActivity.this, "❤❤ You are back ❤❤", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=alertdialogBuilder.create();
        alertDialog.show();
    }


}
