// this activity is called to update the user's favourite driver
// returns a success message once it has completed the update

package com.example.coursework.Drivers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coursework.EntityClass.Driver;

public class SetFavouriteDriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retrieves the Driver object from the intent extras
        Driver favouriteDriver = getIntent().getParcelableExtra("driver");

        // updates the sharedPreferences value to match the Driver
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("favouriteDriver").apply();
        editor.putString("favouriteDriver", favouriteDriver.getLastName()).apply();

        // returns success result, and ends
        setResult(Activity.RESULT_OK);
        finish();
    }
}