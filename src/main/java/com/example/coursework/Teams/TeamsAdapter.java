// This adapter is used by TeamsRVActivity
// it populates the items in the recyclerView with Team objects from the Room database

package com.example.coursework.Teams;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework.EntityClass.Team;
import com.example.coursework.R;

import java.util.List;
import java.util.Objects;


public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.MyViewHolder> {


    // An on-click handler that is defined to make it easy for an Activity to interface with our RecyclerView
    final private ListItemClickListener mOnClickListener;

    private List<Team> mTeams;
    private String mfavouriteTeam;

    Context context;


    // an interface called ListItemClickListener
    // a void method called onListItemClick that takes an int as a parameter
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    // Constructor that accepts a list of Team objects, the user's favourite team, and the specification for the ListItemClickListener
    public TeamsAdapter(List<Team> teams, String favouriteTeam, ListItemClickListener listener) {
        mTeams = teams;
        mfavouriteTeam = favouriteTeam;
        mOnClickListener = listener;
    }

    // viewGroup: The ViewGroup that these ViewHolders are contained within.
    // viewType: If your RecyclerView has more than one type of item you can use this to provide a different layout.
    // returns a new MyViewHolder that holds the Views for each list item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.team_rv_item;
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
        Team team = mTeams.get(position);

        // uses the Driver database functions to populate the views
        holder.listTeamName.setText(team.getTeamName());
        holder.listTeamLogo.setImageResource(team.getTeamLogoId());
        holder.listTeamCar.setImageResource(team.getTeamCarId());
        holder.listTeamPoints.setText("Pts: " + team.getWCCpoints());
        holder.listTeamColourFlash.setBackgroundColor((ContextCompat.getColor(context, team.getTeamColourId())));

        // if the user has selected a favourite driver, that driver's name will be gold
        if (mfavouriteTeam != null) {
            if (Objects.equals(team.getTeamName(), mfavouriteTeam)) {
                holder.listTeamName.setTextColor((ContextCompat.getColor(context, R.color.gold)));
            } else {
                holder.listTeamName.setTextColor((ContextCompat.getColor(context, R.color.black)));
            }
        }

        // set an onClickListener for each item in the recycler view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // creates an explicit intent for the new activity
                Intent intent = new Intent(context, SingleTeamActivity.class);
                // utilises the Parcelable interface I implemented for the Team object to pass the team as Intent
                intent.putExtra("team", team);
                context.startActivity(intent);
            }
        });
    }


    // This method simply returns the number of items to display
    @Override
    public int getItemCount() {
        return mTeams.size();
    }


    // Implement OnClickListener in the NumberViewHolder class
    // Cache of the children views for a list item.
    class MyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout listCard;

        TextView listTeamColourFlash;
        ImageView listTeamLogo;
        TextView listTeamName;
        ImageView listTeamCar;
        TextView listTeamPoints;

        // Constructor for the ViewHolder
        public MyViewHolder(View itemView) {
            super(itemView);

            listCard = (ConstraintLayout) itemView.findViewById(R.id.rv_card);
            listTeamColourFlash = (TextView) itemView.findViewById(R.id.rv_team_colour);
            listTeamLogo = (ImageView) itemView.findViewById(R.id.rv_team_logo);
            listTeamName = (TextView) itemView.findViewById(R.id.rv_team_name);
            listTeamCar = (ImageView) itemView.findViewById(R.id.rv_team_car);
            listTeamPoints = (TextView) itemView.findViewById(R.id.rv_team_points);
        }

    }
}
