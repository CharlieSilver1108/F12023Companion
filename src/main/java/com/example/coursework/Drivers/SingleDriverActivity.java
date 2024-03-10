// This activity displays two fragments: DriverNewsFramgent and DriverProfileFragment
// It receives a Driver object as Intent from DriversRVActivity, and dynamically loads data dependant on the driver

package com.example.coursework.Drivers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.coursework.EntityClass.Driver;
import com.example.coursework.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SingleDriverActivity extends AppCompatActivity {
    private Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drivers_single_activity);

        // Get the driver from the intent extras, utilising the Parcelable interface I implemented
        driver = getIntent().getParcelableExtra("driver");

        // sets the action bar to exist, and to have a back button
        setSupportActionBar(findViewById(R.id.toolBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(driver.getFirstName() + " " + driver.getLastName());


        // creates a button that stays in the bottom right corner, which allows the user to select the driver as their favourite
        FloatingActionButton setFavouriteDriver = findViewById(R.id.set_favourite_driver);
        setFavouriteDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // updates sharedPreferences accordingly
                SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("favouriteDriver").apply();
                editor.putString("favouriteDriver", driver.getLastName()).apply();

                // displays success message to the user
                Toast.makeText(getApplicationContext(), "Driver Favourited", Toast.LENGTH_LONG).show();
            }
        });
    }


    // function which loads the DriverProfileFragment
    public void loadProfileFragment(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment profileFragment = new DriverProfileFragment();
        ((DriverProfileFragment) profileFragment).setDriver(driver);
        fragmentTransaction.replace(R.id.fragment_switch, profileFragment);

        // updates the UI of the buttons to emphasise which fragment is viewable
        findViewById(R.id.button1).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.f1light));
        findViewById(R.id.button2).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.f1superLight));

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // function which loads the DriverNewsFragment
    public void loadNewsFragment(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment newsFragment = new DriverNewsFragment();

        // creates the URL to be viewed in the fragment
        String url = "https://www.skysports.com/f1/drivers/" + driver.getFirstName().toLowerCase() + "-" + driver.getLastName().toLowerCase();
        ((DriverNewsFragment) newsFragment).setURL(url);

        fragmentTransaction.replace(R.id.fragment_switch, newsFragment);

        // updates the UI of the buttons to emphasise which fragment is viewable
        findViewById(R.id.button1).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.f1superLight));
        findViewById(R.id.button2).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.f1light));

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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

}