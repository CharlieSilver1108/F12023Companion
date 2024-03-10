// This fragment displays the information about a specific team in a user-friendly format
// The Team object is received from the recyclerView Activity

package com.example.coursework.Teams;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.coursework.EntityClass.Team;
import com.example.coursework.R;


public class TeamProfileFragment extends Fragment {

    // the team whose profile to display
    private Team team;

    // startActivityForResult() equivalent for Fragment
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        // receives back the 'success' code from the new activity
        if (result.getResultCode() == Activity.RESULT_OK){
            // display success message to user
            Toast.makeText(getContext(), "Team Favourited", Toast.LENGTH_LONG).show();
        } else {
            // display failure message to user
            Toast.makeText(getContext(), "Team Was Unable To Be Favourited", Toast.LENGTH_LONG).show();

        }
    });

    // to allow the parent activity to set the fragment's team variable
    public void setTeam(Team team) {
        this.team = team;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.team_profile_fragment, container, false);


        // Get the views to be populated
        TextView profileTeamColour = rootView.findViewById(R.id.team_colour);
        ImageView profileTeamLogo = rootView.findViewById(R.id.teamImage);
        TextView profileTeamName = rootView.findViewById(R.id.teamName);
        TextView profileTeamPrinciple = rootView.findViewById(R.id.teamPrinciple);
        TextView profileCarModel = rootView.findViewById(R.id.carModel);
        TextView profileEngineSupplier = rootView.findViewById(R.id.engineSupplier);
        TextView profileBaseLocation = rootView.findViewById(R.id.baseLocation);
        TextView profileTeamPoints = rootView.findViewById(R.id.teamPoints);
        TextView profileTeamPosition = rootView.findViewById(R.id.teamPosition);
        TextView profileTeamBio = rootView.findViewById(R.id.teamBio);
        ImageView profileTeamCar = rootView.findViewById(R.id.carImage);

        // registers the profile card for the long-press to bring up a context menu
        CardView profileCard = rootView.findViewById(R.id.teamsCard);
        registerForContextMenu(profileCard);

        // Populate the views
        if (team != null) {
            profileTeamColour.setBackgroundColor((ContextCompat.getColor(getContext(), team.getTeamColourId())));
            profileTeamName.setText(team.getTeamName());
            profileTeamPrinciple.setText("Team Principle: " + team.getTeamPrincipal());
            profileCarModel.setText("Car Model: " + team.getCarModel());
            profileEngineSupplier.setText("Engine Supplier: " + team.getEngineSupplier());
            profileBaseLocation.setText("Base Location: " + team.getBaseLocation());
            profileTeamPoints.setText("WCC Points: " + team.getWCCpoints());
            profileTeamPosition.setText("WCC Position: " + team.getWCCposition());
            profileTeamBio.setText("Bio: " + team.getBio());
            profileTeamLogo.setImageResource(team.getTeamLogoId());
            profileTeamCar.setImageResource(team.getTeamCarId());
        }

        return rootView;
    }


    // provides a contextMenu when the user long-presses the profile card
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.team_profile_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.set_as_favourite_team){
            // if the user selects to favourite the driver, the Driver object is passed as an Intent to the SetFavouriteDriver activity

            Intent intent = new Intent(getActivity(), SetFavouriteTeam.class);
            intent.putExtra("team", team);

            // use of launcher.launch as an API replacement for the deprecated startActivityForResult()
            launcher.launch(intent);
            return true;
        } else if (item.getItemId() == R.id.open_base_location){
            // get location name from database
            String locationName = team.getBaseLocation();

            // create a Uri from an intent string and use the result to create an Intent
            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(locationName));

            // create an Intent from gmmIntentUri and set the action to ACTION_VIEW
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            // make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps");

            // attempt to start an activity that can handle the Intent
            if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(mapIntent);
            } else {
                // displays an error message to the use
                Toast.makeText(getContext(), "Unable to Open Location", Toast.LENGTH_LONG).show();
            }
        }
        return super.onContextItemSelected(item);
    }
}