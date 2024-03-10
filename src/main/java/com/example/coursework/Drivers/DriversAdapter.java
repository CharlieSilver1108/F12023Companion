// This adapter is used by DriversRVActivity
// it populates the items in the recyclerView with Driver objects from the Room database

package com.example.coursework.Drivers;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.EntityClass.Driver;
import com.example.coursework.R;

import java.util.List;
import java.util.Objects;


public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.MyViewHolder> {

    private List<Driver> mDrivers;
    private String mfavouriteDriver;

    Context context;


    // an interface called ListItemClickListener
    // a void method called onListItemClick that takes an int as a parameter
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    // Constructor that accepts a list of Driver objects, the user's favourite driver, and the specification for the ListItemClickListener
    public DriversAdapter(List<Driver> drivers, String favouriteDriver, ListItemClickListener listener) {
        mDrivers = drivers;
        mfavouriteDriver = favouriteDriver;
    }

    // viewGroup: The ViewGroup that these ViewHolders are contained within.
    // viewType: If your RecyclerView has more than one type of item you can use this to provide a different layout.
    // returns a new MyViewHolder that holds the Views for each list item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.driver_rv_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    // called by the RecyclerView to display the data at the specified position
    // In this method, the contents of the ViewHolder get updated for each particular position, using the "position" argument
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position){
        // iterates through the list of the Driver objects
        Driver driver = mDrivers.get(position);

        // uses the Driver database functions to populate the views
        holder.listDriverName.setText(driver.getFirstName() + " " + driver.getLastName().toUpperCase());
        holder.listDriverNumber.setText(driver.getNumber());
        holder.listDriverTeam.setText(driver.getTeam());
        holder.listDriverPoints.setText("Pts: " + driver.getWDCpoints());
        holder.listDriverTeamColourFlash.setBackgroundColor((ContextCompat.getColor(context, getTeamColour(driver.getTeam()))));

        // if the user has selected a favourite driver, that driver's name will be gold
        if (mfavouriteDriver != null) {
            if (Objects.equals(driver.getLastName(), mfavouriteDriver)) {
                holder.listDriverName.setTextColor((ContextCompat.getColor(context, R.color.gold)));
            } else {
                holder.listDriverName.setTextColor((ContextCompat.getColor(context, R.color.black)));
            }
        }

        // set an onClickListener for each item in the recycler view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creates an explicit intent for the new activity
                Intent intent = new Intent(context, SingleDriverActivity.class);
                // utilises the Parcelable interface I implemented for the Driver object to pass the driver as Intent
                intent.putExtra("driver", driver);
                context.startActivity(intent);
            }
        });

    }

    // provides better readability to have this as a separate function
    private int getTeamColour(String teamName){
        if (Objects.equals(teamName, "Oracle Red Bull Racing")) {
            return R.color.Red_Bull_Racing;
        } else if (Objects.equals(teamName, "Mercedes AMG Petronas F1 Team")) {
            return R.color.Mercedes;
        } else if (Objects.equals(teamName, "Scuderia Ferrari")) {
            return R.color.Ferrari;
        } else if (Objects.equals(teamName, "Mclaren F1 Team")) {
            return R.color.Mclaren;
        } else if (Objects.equals(teamName, "Aston Martin Aramco Cognizant F1 Team")) {
            return R.color.Aston_Martin;
        } else if (Objects.equals(teamName, "BWT Alpine F1 Team")) {
            return R.color.Alpine;
        } else if (Objects.equals(teamName, "Williams Racing")) {
            return R.color.Williams;
        } else if (Objects.equals(teamName, "Scuderia AlphaTauri")) {
            return R.color.AlphaTauri;
        } else if (Objects.equals(teamName, "Alfa Romeo F1 Team Stake")) {
            return R.color.Alfa_Romeo;
        } else if (Objects.equals(teamName, "MoneyGram Haas F1 Team")) {
            return R.color.Haas;
        }
        return R.color.white;
    }

    // This method simply returns the number of items to display
    @Override
    public int getItemCount() {
        return mDrivers.size();
    }


    // Implement OnClickListener in the NumberViewHolder class
    // Cache of the children views for a list item.
    class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout listCard;
        TextView listDriverName;
        TextView listDriverTeam;
        TextView listDriverNumber;
        TextView listDriverPoints;
        TextView listDriverTeamColourFlash;

        // Constructor for the ViewHolder
        public MyViewHolder(View itemView) {
            super(itemView);

            listCard = (ConstraintLayout) itemView.findViewById(R.id.rv_card);
            listDriverName = (TextView) itemView.findViewById(R.id.rv_driver_name);
            listDriverTeam = (TextView) itemView.findViewById(R.id.rv_driver_team);
            listDriverNumber = (TextView) itemView.findViewById(R.id.rv_driver_number);
            listDriverPoints = (TextView) itemView.findViewById(R.id.rv_driver_points);
            listDriverTeamColourFlash = (TextView) itemView.findViewById(R.id.rv_driver_team_colour);
        }
    }
}
