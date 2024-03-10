// this activity is like the merging of TeamsRVActivity and SetFavouriteTeam
// it displays the list of teams in a recyclerView, allowing the user to click on the one to become their favourite

package com.example.coursework.Teams;

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
import com.example.coursework.EntityClass.Team;
import com.example.coursework.HomeActivity;
import com.example.coursework.R;

import java.util.List;

// implements the SetFavouriteTeamsAdapter's ListItemClickListener to deal with the user's choice
public class SetFavouriteTeamActivity extends AppCompatActivity
        implements SetFavouriteTeamAdapter.ListItemClickListener {

    private SetFavouriteTeamAdapter mAdapter;
    private RecyclerView mList;

    List<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.teams_rv_activity);

        // allows space for the device's system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // sets the action bar to exist, and to have a back button
        setSupportActionBar(findViewById(R.id.toolBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Select Favourite Team");


        mList = (RecyclerView) findViewById(R.id.rv_main);

        // A LinearLayoutManager measures and positions item views within a RecyclerView into a linear list
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);

        // this improves performance since changes in content could change the child layout size in the RecyclerView
        mList.setHasFixedSize(false);

        // Retrieve data from Room database
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        teams = db.teamDao().getAllTeams();

        // Pass in this as the ListItemClickListener to the DriversAdapter constructor
        // The Adapter is responsible for displaying each item in the list
        // passes null as favouriteTeam since the user is choosing a new favourite
        mAdapter = new SetFavouriteTeamAdapter(teams, null, this);
        mList.setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(int position) {
        // when the user has clicked on a team, the team they picked is accessed via the position variable
        Team favouriteTeam = teams.get(position);

        // updates shared Preferences accordingly
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("favouriteTeam").apply();
        editor.putString("favouriteTeam", favouriteTeam.getTeamName()).apply();

        // Return to HomeActivity when favourite team has been selected
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
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