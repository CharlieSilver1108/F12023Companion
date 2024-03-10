// this activity is like the merging of DriversRVActivity and SetFavouriteDriver
// it displays the list of drivers in a recyclerView, allowing the user to click on the one to become their favourite

package com.example.coursework.Drivers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.AppDatabase;
import com.example.coursework.EntityClass.Driver;
import com.example.coursework.HomeActivity;
import com.example.coursework.R;

import java.util.List;

// implements the SetFavouriteDriverAdapter's ListItemClickListener to deal with the user's choice
public class SetFavouriteDriverActivity extends AppCompatActivity
        implements SetFavouriteDriverAdapter.ListItemClickListener {

    private SetFavouriteDriverAdapter mAdapter;
    private RecyclerView mList;

    List<Driver> drivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.drivers_rv_activity);

        // allows space for the device's system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // sets the action bar to exist, and to have a back button
        setSupportActionBar(findViewById(R.id.toolBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Select Favourite Driver");


        mList = (RecyclerView) findViewById(R.id.rv_main);

        // A LinearLayoutManager measures and positions item views within a RecyclerView into a linear list
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);

        // this improves performance since changes in content do not change the child layout size in the RecyclerView
        mList.setHasFixedSize(true);

        // Retrieve data from Room database
        AppDatabase db = AppDatabase.getInstance(this);
        drivers = db.driverDao().getAllDrivers();

        // Pass in this as the ListItemClickListener to the DriversAdapter constructor
        // The Adapter is responsible for displaying each item in the list
        // passes null as favouriteDriver since the user is choosing a new favourite
        mAdapter = new SetFavouriteDriverAdapter(drivers, null,this);
        mList.setAdapter(mAdapter);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // the only option item is the back button, which returns to the previous activity
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return false;
    }


    @Override
    public void onListItemClick(int position) {
        // when the user has clicked on a driver, the driver they picked is accessed via the position variable
        Driver favouriteDriver = drivers.get(position);

        // updates shared Preferences accordingly
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("favouriteDriver").apply();
        editor.putString("favouriteDriver", favouriteDriver.getLastName()).apply();

        // Return to HomeActivity when favourite driver has been selected
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}