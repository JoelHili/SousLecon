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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;

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
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "FoodActivity";

    //Variables
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageURLs = new ArrayList<>();

    private String URL_JSON = "https://www.themealdb.com/api/json/v2/1/latest.php";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<mealObj> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CocktailActivity.class);
                Snackbar.make(view, "Switched To Cocktail Recipes", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(intent);
            }
        });

        Log.d(TAG, "onCreate: started.");

        jsoncall();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void jsoncall(){
        FetchMealTask task = new FetchMealTask(new WeakReference<AppCompatActivity>(this));
        task.execute();
    }

    private static class FetchMealTask extends AsyncTask<String, Void, ArrayList<mealObj>> {
        private final String URL_JSON = "https://www.themealdb.com/api/json/v2/1/latest.php";
        private final WeakReference<AppCompatActivity> activityWeakReference;

        private ArrayList<String> mNames = new ArrayList<>();
        private ArrayList<String> mImageURLs = new ArrayList<>();

        public FetchMealTask(WeakReference<AppCompatActivity> activityWeakReference) {

            this.activityWeakReference = activityWeakReference;
        }

        @Override
        protected ArrayList<mealObj> doInBackground(String... strings) {
            try {
                URL url = new URL(URL_JSON);
                URLConnection connection = url.openConnection();
                connection.connect();

                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String json = bufferedReader.lines().collect(Collectors.joining("\n"));

                JSONObject jsonObject = new JSONObject(json);

                // TODO - Parse jsonObject and convert and return to mealObj[]

                JSONArray jsonArray = jsonObject.getJSONArray("meals");
                ArrayList<mealObj> meals = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++ ){
                    mealObj meal = new mealObj();

                    meal.setIdMeal(jsonArray.getJSONObject(i).getString("idMeal"));
                    meal.setStrMeal(jsonArray.getJSONObject(i).getString("strMeal"));
                    meal.setStrCategory(jsonArray.getJSONObject(i).getString("strCategory"));
                    meal.setStrArea(jsonArray.getJSONObject(i).getString("strArea"));
                    meal.setStrInstructions(jsonArray.getJSONObject(i).getString("strInstructions"));
                    meal.setStrMealThumb(jsonArray.getJSONObject(i).getString("strMealThumb"));
                    meal.setStrTags(jsonArray.getJSONObject(i).getString("strTags"));
                    meal.setStrYoutube(jsonArray.getJSONObject(i).getString("strYoutube"));
                    meal.setStrIngredient1(jsonArray.getJSONObject(i).getString("strIngredient1"));
                    meal.setStrIngredient2(jsonArray.getJSONObject(i).getString("strIngredient2"));
                    meal.setStrIngredient3(jsonArray.getJSONObject(i).getString("strIngredient3"));
                    meal.setStrIngredient4(jsonArray.getJSONObject(i).getString("strIngredient4"));
                    meal.setStrIngredient5(jsonArray.getJSONObject(i).getString("strIngredient5"));
                    meal.setStrIngredient6(jsonArray.getJSONObject(i).getString("strIngredient6"));
                    meal.setStrIngredient7(jsonArray.getJSONObject(i).getString("strIngredient7"));
                    meal.setStrIngredient8(jsonArray.getJSONObject(i).getString("strIngredient8"));
                    meal.setStrIngredient9(jsonArray.getJSONObject(i).getString("strIngredient9"));
                    meal.setStrIngredient10(jsonArray.getJSONObject(i).getString("strIngredient10"));
                    meal.setStrIngredient11(jsonArray.getJSONObject(i).getString("strIngredient11"));
                    meal.setStrIngredient12(jsonArray.getJSONObject(i).getString("strIngredient12"));
                    meal.setStrIngredient13(jsonArray.getJSONObject(i).getString("strIngredient13"));
                    meal.setStrIngredient14(jsonArray.getJSONObject(i).getString("strIngredient14"));
                    meal.setStrIngredient15(jsonArray.getJSONObject(i).getString("strIngredient15"));
                    meal.setStrIngredient16(jsonArray.getJSONObject(i).getString("strIngredient16"));
                    meal.setStrIngredient17(jsonArray.getJSONObject(i).getString("strIngredient17"));
                    meal.setStrIngredient18(jsonArray.getJSONObject(i).getString("strIngredient18"));
                    meal.setStrIngredient19(jsonArray.getJSONObject(i).getString("strIngredient19"));
                    meal.setStrIngredient20(jsonArray.getJSONObject(i).getString("strIngredient20"));
                    meal.setStrMeasure1(jsonArray.getJSONObject(i).getString("strMeasure1"));
                    meal.setStrMeasure2(jsonArray.getJSONObject(i).getString("strMeasure2"));
                    meal.setStrMeasure3(jsonArray.getJSONObject(i).getString("strMeasure3"));
                    meal.setStrMeasure4(jsonArray.getJSONObject(i).getString("strMeasure4"));
                    meal.setStrMeasure5(jsonArray.getJSONObject(i).getString("strMeasure5"));
                    meal.setStrMeasure6(jsonArray.getJSONObject(i).getString("strMeasure6"));
                    meal.setStrMeasure7(jsonArray.getJSONObject(i).getString("strMeasure7"));
                    meal.setStrMeasure8(jsonArray.getJSONObject(i).getString("strMeasure8"));
                    meal.setStrMeasure9(jsonArray.getJSONObject(i).getString("strMeasure9"));
                    meal.setStrMeasure10(jsonArray.getJSONObject(i).getString("strMeasure10"));
                    meal.setStrMeasure11(jsonArray.getJSONObject(i).getString("strMeasure11"));
                    meal.setStrMeasure12(jsonArray.getJSONObject(i).getString("strMeasure12"));
                    meal.setStrMeasure13(jsonArray.getJSONObject(i).getString("strMeasure13"));
                    meal.setStrMeasure14(jsonArray.getJSONObject(i).getString("strMeasure14"));
                    meal.setStrMeasure15(jsonArray.getJSONObject(i).getString("strMeasure15"));
                    meal.setStrMeasure16(jsonArray.getJSONObject(i).getString("strMeasure16"));
                    meal.setStrMeasure17(jsonArray.getJSONObject(i).getString("strMeasure17"));
                    meal.setStrMeasure18(jsonArray.getJSONObject(i).getString("strMeasure18"));
                    meal.setStrMeasure19(jsonArray.getJSONObject(i).getString("strMeasure19"));
                    meal.setStrMeasure20(jsonArray.getJSONObject(i).getString("strMeasure20"));
                    meal.setStrSource(jsonArray.getJSONObject(i).getString("strSource"));
                    meal.setDateModified(jsonArray.getJSONObject(i).getString("dateModified"));

                    meals.add(meal);
                }

                return meals;
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<mealObj> mealObj) {
            super.onPostExecute(mealObj);
            // TODO - Magic to do with activity - initImageBitmaps()
            Log.d(TAG, "onPostExecute: initImageBitmaps");

            for (mealObj d : mealObj){
                mImageURLs.add(d.getStrMealThumb());
                mNames.add(d.getStrMeal());
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
