// This is the home page for the app, the default launch activity
// it contains a title, and clickable cards
// currently, the drivers, teams, and news are fully implemented


package com.example.coursework;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coursework.Drivers.DriversRVActivity;
import com.example.coursework.Teams.TeamsRVActivity;

import java.util.Objects;


public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // allows space for the device's system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // sets the action bar to exist, and to have a title
        setSupportActionBar(findViewById(R.id.toolBar));
        setTitle("F1 2023 Companion");

        // based on user preferences
        setDriverImage();
        setTeamImage();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_option_menu, menu);
        return true;
    }


    // item has been selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.options_menu_search){
            // do search
//            return true;
        } else if (item.getItemId() == R.id.options_menu_switch){
            // do switch
//            return true;
        } else if (item.getItemId() == R.id.options_menu_clear_favourites){
            // displays a pop-up confirmation box to the user to check they meant to remove all favourite
            showConfirmClearDialogue();
            return true;
        }
        return false;
    }


    // displays a pop-up confirmation box to the user to check they meant to remove all favourite

    private void showConfirmClearDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setView(R.layout.dialogue_confirm);
        // if the user selects yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Clear all data in the SharedPreferences
                getSharedPreferences("myPrefs", Context.MODE_PRIVATE).edit().clear().apply();

                // refresh the activity
                recreate();
            }
        });
        // if the user selects no
        builder.setNegativeButton("No", null);
        builder.show();
    }


    // updates the image on the 'drivers' card to be the user's favourite driver
    public void setDriverImage(){
        // retrieve the favourite from sharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String favouriteDriver = sharedPreferences.getString("favouriteDriver", null);

        ImageView imageToUpdate = findViewById(R.id.driversImage);

        if (Objects.equals(favouriteDriver, "Verstappen")){
            imageToUpdate.setImageResource(R.drawable.verstappen);
        } else if (Objects.equals(favouriteDriver, "Perez")){
            imageToUpdate.setImageResource(R.drawable.perez);
        } else if (Objects.equals(favouriteDriver, "Hamilton")){
            imageToUpdate.setImageResource(R.drawable.hamilton);
        } else if (Objects.equals(favouriteDriver, "Russell")){
            imageToUpdate.setImageResource(R.drawable.russell);
        } else if (Objects.equals(favouriteDriver, "Leclerc")){
            imageToUpdate.setImageResource(R.drawable.leclerc);
        } else if (Objects.equals(favouriteDriver, "Sainz")){
            imageToUpdate.setImageResource(R.drawable.sainz);
        } else if (Objects.equals(favouriteDriver, "Norris")){
            imageToUpdate.setImageResource(R.drawable.norris);
        } else if (Objects.equals(favouriteDriver, "Piastri")){
            imageToUpdate.setImageResource(R.drawable.piastri);
        } else if (Objects.equals(favouriteDriver, "Alonso")){
            imageToUpdate.setImageResource(R.drawable.alonso);
        } else if (Objects.equals(favouriteDriver, "Stroll")){
            imageToUpdate.setImageResource(R.drawable.stroll);
        } else if (Objects.equals(favouriteDriver, "Gasly")){
            imageToUpdate.setImageResource(R.drawable.gasly);
        } else if (Objects.equals(favouriteDriver, "Ocon")){
            imageToUpdate.setImageResource(R.drawable.ocon);
        } else if (Objects.equals(favouriteDriver, "Albon")) {
            imageToUpdate.setImageResource(R.drawable.albon);
        } else if (Objects.equals(favouriteDriver, "Sargeant")){
            imageToUpdate.setImageResource(R.drawable.sergeant);
        } else if (Objects.equals(favouriteDriver, "Tsunoda")){
            imageToUpdate.setImageResource(R.drawable.tsunoda);
        } else if (Objects.equals(favouriteDriver, "Ricciardo")){
            imageToUpdate.setImageResource(R.drawable.ricciardo);
        } else if (Objects.equals(favouriteDriver, "Bottas")){
            imageToUpdate.setImageResource(R.drawable.bottas);
        } else if (Objects.equals(favouriteDriver, "Zhou")){
            imageToUpdate.setImageResource(R.drawable.zhou);
        } else if (Objects.equals(favouriteDriver, "Hulkenberg")){
            imageToUpdate.setImageResource(R.drawable.hulkenberg);
        } else if (Objects.equals(favouriteDriver, "Magnussen")){
            imageToUpdate.setImageResource(R.drawable.magnussen);
        } else{
            imageToUpdate.setImageResource(R.drawable.verstappen);
        }

    }

    // the same as setDriverImage, but for favourite team
    public void setTeamImage(){
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String favouriteTeam = sharedPreferences.getString("favouriteTeam", null);

        ImageView imageToUpdate = findViewById(R.id.teamsImage);

        if (Objects.equals(favouriteTeam, "Oracle Red Bull Racing")){
            imageToUpdate.setImageResource(R.drawable.red_bull);
        } else if (Objects.equals(favouriteTeam, "Mercedes AMG Petronas F1 Team")){
            imageToUpdate.setImageResource(R.drawable.mercedes);
        } else if (Objects.equals(favouriteTeam, "Scuderia Ferrari")){
            imageToUpdate.setImageResource(R.drawable.ferrari);
        } else if (Objects.equals(favouriteTeam, "Mclaren F1 Team")){
            imageToUpdate.setImageResource(R.drawable.mclaren);
        } else if (Objects.equals(favouriteTeam, "Aston Martin Aramco Cognizant F1 Team")){
            imageToUpdate.setImageResource(R.drawable.aston_martin);
        } else if (Objects.equals(favouriteTeam, "BWT Alpine F1 Team")){
            imageToUpdate.setImageResource(R.drawable.alpine);
        } else if (Objects.equals(favouriteTeam, "Williams Racing")){
            imageToUpdate.setImageResource(R.drawable.williams);
        } else if (Objects.equals(favouriteTeam, "Scuderia AlphaTauri")){
            imageToUpdate.setImageResource(R.drawable.alpha_tauri);
        }else if (Objects.equals(favouriteTeam, "Alfa Romeo F1 Team Stake")){
            imageToUpdate.setImageResource(R.drawable.alfa_romeo);
        }else if (Objects.equals(favouriteTeam, "MoneyGram Haas F1 Team")){
            imageToUpdate.setImageResource(R.drawable.haas);
        } else{
            imageToUpdate.setImageResource(R.drawable.red_bull);
        }
    }


    // called when the 'drivers' card is clicked
    public void launchDriversRVActivity(View v){
        // Create an Intent to launch the target activity
        Intent intent = new Intent(this, DriversRVActivity.class);
        startActivity(intent);
    }

    // called when the 'teams' card is clicked
    public void launchTeamsRVActivity(View v){
        // Create an Intent to launch the target activity
        Intent intent = new Intent(this, TeamsRVActivity.class);
        startActivity(intent);
    }


    // called when the 'news' card is clicked
    // uses implicit intent to launch browser activity
    public void openNewsWebPage(View v) {
        // Create a Uri object from the URL string
        Uri webpage = Uri.parse("https://www.skysports.com/f1/news");

        // Create an Intent with ACTION_VIEW to open the webpage
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, webpage);

        // Create an App Chooser to allow the user to select a browser
        Intent browserChooserIntent = Intent.createChooser(browserIntent , "Choose browser of your choice");


        // Check if there's an app available to handle the intent
        if (browserChooserIntent.resolveActivity(getPackageManager()) != null) {
            // Start the activity with the intent
            startActivity(browserChooserIntent);
        } else {
            // If no app is available, displays a message to the user
            Log.e("error", "No Activity Available to Handle Action");
        }
    }
}