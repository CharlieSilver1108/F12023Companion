// This activity displays two fragments: TeamNewsFramgent and TeamProfileFragment
// It receives a Team object as Intent from TeamsRVActivity, and dynamically loads data dependant on the team

package com.example.coursework.Teams;

import android.content.Context;
import android.content.Intent;
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

import com.example.coursework.EntityClass.Team;
import com.example.coursework.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class SingleTeamActivity extends AppCompatActivity {
    private Team team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_single_activity);


        // Get the team from the intent extras, utilising the Parcelable interface I implemented
        team = getIntent().getParcelableExtra("team");

        // sets the action bar to exist, and to have a back button
        setSupportActionBar(findViewById(R.id.toolBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(team.getTeamName());

        // creates a button that stays in the bottom right corner, which allows the user to select the team as their favourite
        FloatingActionButton setFavouriteTeam = findViewById(R.id.set_favourite_team);
        setFavouriteTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // updates sharedPreferences accordingly
                SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("favouriteTeam").apply();
                editor.putString("favouriteTeam", team.getTeamName()).apply();

                // displays success message to the user
                Toast.makeText(getApplicationContext(), "Team Favourited", Toast.LENGTH_LONG).show();
            }
        });
    }


    // function which loads the TeamProfileFragment
    public void loadProfileFragment(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment profileFragment = new TeamProfileFragment();
        ((TeamProfileFragment) profileFragment).setTeam(team);
        fragmentTransaction.replace(R.id.fragment_switch, profileFragment);

        // updates the UI of the buttons to emphasise which fragment is viewable
        findViewById(R.id.button1).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.f1light));
        findViewById(R.id.button2).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.f1superLight));

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    // function which loads the TeamNewsFragment
    public void loadNewsFragment(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment newsFragment = new TeamNewsFragment();

        // creates the URL to be viewed in the fragment
        String url = getURL();
        ((TeamNewsFragment) newsFragment).setURL(url);

        fragmentTransaction.replace(R.id.fragment_switch, newsFragment);

        // updates the UI of the buttons to emphasise which fragment is viewable
        findViewById(R.id.button1).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.f1superLight));
        findViewById(R.id.button2).setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.f1light));

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    // a function which creates the URL which is displayed by the NewsFragment
    private String getURL(){
        String baseURL = "https://www.skysports.com/f1/";
        String fullURL = "";
        if (Objects.equals(team.getTeamName(), "Oracle Red Bull Racing")){
            fullURL = baseURL + "teams/red-bull";
        } else if (Objects.equals(team.getTeamName(), "Mercedes AMG Petronas F1 Team")){
            fullURL = baseURL + "teams/mercedes";
        } else if (Objects.equals(team.getTeamName(), "Scuderia Ferrari")){
            fullURL = baseURL + "teams/ferrari";
        } else if (Objects.equals(team.getTeamName(), "Mclaren F1 Team")){
            fullURL = baseURL + "teams/mclaren";
        } else if (Objects.equals(team.getTeamName(), "Aston Martin Aramco Cognizant F1 Team")){
            fullURL = baseURL + "teams/aston-martin";
        } else if (Objects.equals(team.getTeamName(), "BWT Alpine F1 Team")){
            fullURL = baseURL + "teams/alpine";
        } else if (Objects.equals(team.getTeamName(), "Williams Racing")){
            fullURL = baseURL + "teams/williams";
        } else if (Objects.equals(team.getTeamName(), "Scuderia AlphaTauri")){
            fullURL = baseURL + "teams/rb";
        } else if (Objects.equals(team.getTeamName(), "Alfa Romeo F1 Team Stake")){
            fullURL = baseURL + "teams/sauber";
        } else if (Objects.equals(team.getTeamName(), "MoneyGram Haas F1 Team")){
            fullURL = baseURL + "teams/haas";
        } else {
             fullURL = baseURL + "news";
        }
        return fullURL;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // the only option item is the back button, which returns to the previous activity
        if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, TeamsRVActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }
}