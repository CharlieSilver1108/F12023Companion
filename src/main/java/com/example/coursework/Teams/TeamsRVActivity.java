// This is the activity which uses the TeamsAdapter to display a RecyclerView of all the teams in the database

package com.example.coursework.Teams;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.AppDatabase;
import com.example.coursework.DaoClass.TeamDao;
import com.example.coursework.EntityClass.Team;
import com.example.coursework.HomeActivity;
import com.example.coursework.R;

import java.util.List;


// Implement TeamsAdapter.ListItemClickListener from the TeamsRVActivity
public class TeamsRVActivity extends AppCompatActivity
        implements TeamsAdapter.ListItemClickListener {


    // References to RecyclerView and Adapter to reset the list to its "pretty" state when the reset menu item is clicked.
    private TeamsAdapter mAdapter;
    private RecyclerView mList;

    // a Toast is a simple feedback mechanism used to display a brief message to the user
    private Toast mToast;


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
        setTitle("");

        mList = (RecyclerView) findViewById(R.id.rv_main);

        // A LinearLayoutManager measures and positions item views within a RecyclerView into a linear list
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(layoutManager);

        // this improves performance since changes in content could change the child layout size in the RecyclerView
        mList.setHasFixedSize(false);


        // Retrieve data from Room database
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        TeamDao teamDao = db.teamDao();
        List<Team> teams = teamDao.getAllTeams();

        // using sharedPreferences to store the user's favourite driver/team etc
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String favouriteTeam = sharedPreferences.getString("favouriteTeam", null);

        // Pass in this as the ListItemClickListener to the DriversAdapter constructor
        // The Adapter is responsible for displaying each item in the list.
        mAdapter = new TeamsAdapter(teams, favouriteTeam, this);
        mList.setAdapter(mAdapter);
    }


    // Overrides ListItemClickListener's onListItemClick method
    // clickedItemIndex is the index of the item that was clicked
    @Override
    public void onListItemClick(int clickedItemIndex) {
        // cancel the Toast if it isn't null
        if (mToast != null) {
            mToast.cancel();
        }

        // show a Toast when an item is clicked, displaying which item number was clicked
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }


    // creates the options menu for this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.teams_option_menu, menu);
        return true;
    }


    // item has been selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.options_menu_search){
            // do search
//            return true;
        }else if (item.getItemId() == R. id.options_menu_switch){
            // do switch
//            return true;
        }else if (item.getItemId() == R.id.options_menu_set_favourite_team){
            // Start SetFavouriteTeamActivity when "Set Favourite Team" menu item is selected
            Intent intent = new Intent(this, SetFavouriteTeamActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.options_menu_remove_favourite_team) {
            // displays a pop-up confirmation box to the user to check they meant to remove favourite
            showConfirmClearDialogue();

            return true;
        } else if (item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


    // displays a pop-up confirmation box to the user to check they meant to remove favourite
    private void showConfirmClearDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setView(R.layout.dialogue_confirm);
        // if the user selects yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Clear saved data in the SharedPreferences
                getSharedPreferences("myPrefs", Context.MODE_PRIVATE).edit().remove("favouriteTeam").apply();

                // refresh the activity to reflect changes
                recreate();
            }
        });
        // if the user selects no
        builder.setNegativeButton("No", null);
        builder.show();
    }
}