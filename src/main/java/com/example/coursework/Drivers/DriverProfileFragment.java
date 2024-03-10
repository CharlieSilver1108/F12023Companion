// This fragment displays the information about a specific driver in a user-friendly format
// The Driver object is received from the recyclerView Activity

package com.example.coursework.Drivers;

import android.app.Activity;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;

import com.example.coursework.EntityClass.Driver;
import com.example.coursework.R;

public class DriverProfileFragment extends Fragment {

    // the driver whose profile to display
    private Driver driver;


    // startActivityForResult() equivalent for Fragment
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        // receives back the 'success' code from the new activity
        if (result.getResultCode() == Activity.RESULT_OK){
            // display success message to user
            Toast.makeText(getContext(), "Driver Favourited", Toast.LENGTH_LONG).show();
        } else {
            // display failure message to user
            Toast.makeText(getContext(), "Driver Was Unable To Be Favourited", Toast.LENGTH_LONG).show();

        }
    });

    // to allow the parent activity to set the fragment's driver variable
    public void setDriver(Driver driver) {
        this.driver = driver;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.driver_profile_fragment, container, false);


        // Get the views to be populated
        ImageView profileDriverImage = rootView.findViewById(R.id.driverImage);
        TextView profileDriverName = rootView.findViewById(R.id.driverName);
        TextView profileDriverTeam = rootView.findViewById(R.id.driverTeam);
        TextView profileDriverAge = rootView.findViewById(R.id.driverAge);
        TextView profileDriverNationality = rootView.findViewById(R.id.driverNationality);
        TextView profileDriverNumber = rootView.findViewById(R.id.driverNumber);
        TextView profileDriverPoints = rootView.findViewById(R.id.driverPoints);
        TextView profileDriverPosition = rootView.findViewById(R.id.driverPosition);
        TextView profileDriverBio = rootView.findViewById(R.id.driverBio);

        // registers the profile card for the long-press to bring up a context menu
        CardView profileCard = rootView.findViewById(R.id.driversCard);
        registerForContextMenu(profileCard);


        // Populate the views
        if (driver != null) {
            profileDriverName.setText(driver.getFirstName() + " " + driver.getLastName());
            profileDriverTeam.setText("Team: " + driver.getTeam());
            profileDriverAge.setText("Age: " + driver.getAge());
            profileDriverNationality.setText("Nationality: " + driver.getNationality());
            profileDriverNumber.setText("Number: " + driver.getNumber());
            profileDriverPoints.setText("WDC Points: " + driver.getWDCpoints());
            profileDriverPosition.setText("WDC Position: " + driver.getWDCposition());
            profileDriverBio.setText("Bio: " + driver.getBiography());
            profileDriverImage.setImageResource(driver.getImageId()); // Assuming getImageId() returns drawable resource id
        }

        return rootView;
    }


    // provides a contextMenu when the user long-presses the profile card
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.driver_profile_context_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.set_as_favourite_driver){
            // if the user selects to favourite the driver, the Driver object is passed as an Intent to the SetFavouriteDriver activity
            Intent intent = new Intent(getActivity(), SetFavouriteDriver.class);
            intent.putExtra("driver", driver);

            // use of launcher.launch as an API replacement for the deprecated startActivityForResult()
            launcher.launch(intent);
            return true;
        } else{
            return super.onContextItemSelected(item);
        }
    }


}