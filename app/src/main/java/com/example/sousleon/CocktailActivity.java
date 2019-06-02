package com.example.sousleon;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CocktailActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "CocktailActivity";

    //Variables
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageURLs = new ArrayList<>();

    private ArrayList<drinkObj> drinks = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocktail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CocktailActivity.this, MainActivity.class);
                Snackbar.make(view, "Switched To Food Recipes", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(intent);
            }
        });

        Log.d(TAG, "onCreate: started.");

        jsoncall();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_menu:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_drink:
                intent = new Intent(this, CocktailActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_favorite:
                intent = new Intent(this, Favorites_Security.class);
                startActivity(intent);
                break;
        }

        return true;
    }

    private void jsoncall(){
        FetchDrinkTask task = new FetchDrinkTask(new WeakReference<AppCompatActivity>(this));
        task.execute();
    }

    private static class FetchDrinkTask extends AsyncTask<String, Void, ArrayList<drinkObj>> {
        private final String URL_JSON = "https://www.thecocktaildb.com/api/json/v2/1/recent.php";
        private final WeakReference<AppCompatActivity> activityWeakReference;

        private ArrayList<String> mNames = new ArrayList<>();
        private ArrayList<String> mImageURLs = new ArrayList<>();

        public FetchDrinkTask(WeakReference<AppCompatActivity> activityWeakReference) {

            this.activityWeakReference = activityWeakReference;
        }

        @Override
        protected ArrayList<drinkObj> doInBackground(String... strings) {
            try {
                URL url = new URL(URL_JSON);
                URLConnection connection = url.openConnection();
                connection.connect();

                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String json = bufferedReader.lines().collect(Collectors.joining("\n"));

                JSONObject jsonObject = new JSONObject(json);

                // TODO - Parse jsonObject and convert and return to drinkObj[]

                JSONArray jsonArray = jsonObject.getJSONArray("drinks");
                ArrayList<drinkObj> drinks = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++ ){
                    drinkObj drink = new drinkObj();

                    drink.setIdDrink(jsonArray.getJSONObject(i).getString("idDrink"));
                    drink.setStrDrink(jsonArray.getJSONObject(i).getString("strDrink"));
                    drink.setStrDrinkAlternate(jsonArray.getJSONObject(i).getString("strDrinkAlternate"));
                    drink.setStrTags(jsonArray.getJSONObject(i).getString("strTags"));
                    drink.setStrInstructions(jsonArray.getJSONObject(i).getString("strInstructions"));
                    drink.setStrDrinkThumb(jsonArray.getJSONObject(i).getString("strDrinkThumb"));
                    drink.setStrTags(jsonArray.getJSONObject(i).getString("strTags"));
                    drink.setStrVideo(jsonArray.getJSONObject(i).getString("strVideo"));
                    drink.setStrIBA(jsonArray.getJSONObject(i).getString("strIBA"));
                    drink.setStrAlcoholic(jsonArray.getJSONObject(i).getString("strAlcoholic"));
                    drink.setStrGlass(jsonArray.getJSONObject(i).getString("strGlass"));
                    drink.setStrInstructions(jsonArray.getJSONObject(i).getString("strInstructions"));
                    drink.setStrDrinkThumb(jsonArray.getJSONObject(i).getString("strDrinkThumb"));
                    drink.setStrIngredient1(jsonArray.getJSONObject(i).getString("strIngredient1"));
                    drink.setStrIngredient2(jsonArray.getJSONObject(i).getString("strIngredient2"));
                    drink.setStrIngredient3(jsonArray.getJSONObject(i).getString("strIngredient3"));
                    drink.setStrIngredient4(jsonArray.getJSONObject(i).getString("strIngredient4"));
                    drink.setStrIngredient5(jsonArray.getJSONObject(i).getString("strIngredient5"));
                    drink.setStrIngredient6(jsonArray.getJSONObject(i).getString("strIngredient6"));
                    drink.setStrIngredient7(jsonArray.getJSONObject(i).getString("strIngredient7"));
                    drink.setStrIngredient8(jsonArray.getJSONObject(i).getString("strIngredient8"));
                    drink.setStrIngredient9(jsonArray.getJSONObject(i).getString("strIngredient9"));
                    drink.setStrIngredient10(jsonArray.getJSONObject(i).getString("strIngredient10"));
                    drink.setStrIngredient11(jsonArray.getJSONObject(i).getString("strIngredient11"));
                    drink.setStrIngredient12(jsonArray.getJSONObject(i).getString("strIngredient12"));
                    drink.setStrIngredient13(jsonArray.getJSONObject(i).getString("strIngredient13"));
                    drink.setStrIngredient14(jsonArray.getJSONObject(i).getString("strIngredient14"));
                    drink.setStrIngredient15(jsonArray.getJSONObject(i).getString("strIngredient15"));
                    drink.setStrMeasure1(jsonArray.getJSONObject(i).getString("strMeasure1"));
                    drink.setStrMeasure2(jsonArray.getJSONObject(i).getString("strMeasure2"));
                    drink.setStrMeasure3(jsonArray.getJSONObject(i).getString("strMeasure3"));
                    drink.setStrMeasure4(jsonArray.getJSONObject(i).getString("strMeasure4"));
                    drink.setStrMeasure5(jsonArray.getJSONObject(i).getString("strMeasure5"));
                    drink.setStrMeasure6(jsonArray.getJSONObject(i).getString("strMeasure6"));
                    drink.setStrMeasure7(jsonArray.getJSONObject(i).getString("strMeasure7"));
                    drink.setStrMeasure8(jsonArray.getJSONObject(i).getString("strMeasure8"));
                    drink.setStrMeasure9(jsonArray.getJSONObject(i).getString("strMeasure9"));
                    drink.setStrMeasure10(jsonArray.getJSONObject(i).getString("strMeasure10"));
                    drink.setStrMeasure11(jsonArray.getJSONObject(i).getString("strMeasure11"));
                    drink.setStrMeasure12(jsonArray.getJSONObject(i).getString("strMeasure12"));
                    drink.setStrMeasure13(jsonArray.getJSONObject(i).getString("strMeasure13"));
                    drink.setStrMeasure14(jsonArray.getJSONObject(i).getString("strMeasure14"));
                    drink.setStrMeasure15(jsonArray.getJSONObject(i).getString("strMeasure15"));
                    drink.setStrCategory(jsonArray.getJSONObject(i).getString("strCategory"));
                    drink.setDateModified(jsonArray.getJSONObject(i).getString("dateModified"));
                    drink.setDateModified(jsonArray.getJSONObject(i).getString("dateModified"));

                    drinks.add(drink);
                }

                return drinks;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<drinkObj> drinkObj) {
            super.onPostExecute(drinkObj);
            // TODO - Magic to do with activity - initImageBitmaps()
            Log.d(TAG, "onPostExecute: initImageBitmaps");

            for (drinkObj d : drinkObj){
                mImageURLs.add(d.getStrDrinkThumb());
                mNames.add(d.getStrDrink());
            }

            initImageBitmaps();
        }

        private void initImageBitmaps(){
            Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

            initRecyclerView();
        }

        private void initRecyclerView(){
            Log.d(TAG, "initRecyclerView: init recycler view.");
            AppCompatActivity activity = activityWeakReference.get();
            RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(mNames, mImageURLs, activity);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        }
    }
}
