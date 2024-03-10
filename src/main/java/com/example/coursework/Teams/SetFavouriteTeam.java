// this activity is called to update the user's favourite team
// returns a success message once it has completed the update

package com.example.coursework.Teams;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coursework.EntityClass.Team;

public class SetFavouriteTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // retrieves the Team object from the intent extras
        Team favouriteTeam = getIntent().getParcelableExtra("team");

        // updates the sharedPreferences value to match the Team
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("favouriteTeam").apply();
        editor.putString("favouriteTeam", favouriteTeam.getTeamName()).apply();

        // Return success result, and ends
        setResult(Activity.RESULT_OK);
        finish();
    }
}