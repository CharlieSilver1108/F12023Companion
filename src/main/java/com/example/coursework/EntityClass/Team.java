// This is the Entity Class for the 'teams' table in the Room database
// It is the model that objects are stored as in the database

package com.example.coursework.EntityClass;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams",
        indices = {@Index(value = {"teamName"}, unique = true)})
public class Team implements Parcelable {

    // auto incrementing int id
    @PrimaryKey(autoGenerate = true)
    private int id;


    // fields for the entity
    @ColumnInfo(name = "teamName")
    private String teamName;

    @ColumnInfo(name = "teamPrincipal")
    private String teamPrincipal;

    @ColumnInfo(name = "carModel")
    private String carModel;

    @ColumnInfo(name = "engineSupplier")
    private String engineSupplier;

    @ColumnInfo(name = "baseLocation")
    private String baseLocation;

    @ColumnInfo(name = "WCCpoints")
    private int WCCpoints;

    @ColumnInfo(name = "WCCposition")
    private int WCCposition;

    @ColumnInfo(name = "bio")
    private String bio;

    @ColumnInfo(name = "teamColour")
    private int teamColourId;

    @ColumnInfo(name = "teamLogoId")
    private int teamLogoId;

    @ColumnInfo(name = "teamCarId")
    private int teamCarId;


    // constructor
    public Team(String teamName, String teamPrincipal, String carModel, String engineSupplier, String baseLocation, int WCCpoints, int WCCposition, String bio, int teamColourId, int teamLogoId, int teamCarId) {
        this.teamName = teamName;
        this.teamPrincipal = teamPrincipal;
        this.carModel = carModel;
        this.engineSupplier = engineSupplier;
        this.baseLocation = baseLocation;
        this.WCCpoints = WCCpoints;
        this.WCCposition = WCCposition;
        this.bio = bio;
        this.teamColourId = teamColourId;
        this.teamLogoId = teamLogoId;
        this.teamCarId = teamCarId;
    }


    // Parcelable implementations, to allow for the Team object to be passed as Intent
    public Team(Parcel in) {
        id = in.readInt();
        teamName = in.readString();
        teamPrincipal = in.readString();
        carModel = in.readString();
        engineSupplier = in.readString();
        baseLocation = in.readString();
        WCCpoints = in.readInt();
        WCCposition = in.readInt();
        bio = in.readString();
        teamColourId = in.readInt();
        teamLogoId = in.readInt();
        teamCarId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(teamName);
        dest.writeString(teamPrincipal);
        dest.writeString(carModel);
        dest.writeString(engineSupplier);
        dest.writeString(baseLocation);
        dest.writeInt(WCCpoints);
        dest.writeInt(WCCposition);
        dest.writeString(bio);
        dest.writeInt(teamColourId);
        dest.writeInt(teamLogoId);
        dest.writeInt(teamCarId);
    }

    // required
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };



    // Getters and setters for each field of the entity
    public int getId() { return this.id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamPrincipal() {
        return this.teamPrincipal;
    }

    public void setTeamPrincipal(String teamPrincipal) {
        this.teamPrincipal = teamPrincipal;
    }

    public String getCarModel() {
        return this.carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getEngineSupplier() {
        return this.engineSupplier;
    }

    public void setEngineSupplier(String engineSupplier) {
        this.engineSupplier = engineSupplier;
    }

    public String getBaseLocation() {
        return this.baseLocation;
    }

    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    public int getWCCpoints() {
        return this.WCCpoints;
    }

    public void setWCCpoints(int WCCpoints) {
        this.WCCpoints = WCCpoints;
    }

    public int getWCCposition() {
        return this.WCCposition;
    }

    public void setWCCposition(int WCCposition) {
        this.WCCposition = WCCposition;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getTeamColourId() {
        return this.teamColourId;
    }

    public void setTeamColourId(int teamColourId) {
        this.teamColourId = teamColourId;
    }

    public int getTeamLogoId() {
        return this.teamLogoId;
    }

    public void setTeamLogoId(int teamLogoId) {
        this.teamLogoId = teamLogoId;
    }

    public int getTeamCarId() {
        return this.teamCarId;
    }

    public void setTeamCarId(int teamCarId) {
        this.teamCarId = teamCarId;
    }
}
