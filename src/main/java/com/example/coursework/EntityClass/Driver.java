// This is the Entity Class for the 'drivers' table in the Room database
// It is the model that objects are stored as in the database

package com.example.coursework.EntityClass;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

// Foreign key referencing drivers.team = team.teamName
@Entity(tableName = "drivers",
        foreignKeys = @ForeignKey(entity = Team.class, parentColumns = "teamName", childColumns = "team"))
public class Driver implements Parcelable {

    // auto incrementing int id
    @PrimaryKey(autoGenerate = true)
    private int id;


    // fields for the entity
    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "lastName")
    private String lastName;

    @ColumnInfo(name = "team")
    private String team;

    @ColumnInfo(name = "age")
    private int age;

    @ColumnInfo(name = "nationality")
    private String nationality;

    @ColumnInfo(name = "number")
    private String number;

    @ColumnInfo(name = "imageId")
    private int imageId;

    @ColumnInfo(name = "WDCpoints")
    private int WDCpoints;

    @ColumnInfo(name = "WDCposition")
    private int WDCposition;

    @ColumnInfo(name = "biography")
    private String biography;


    //constructor
    public Driver(String firstName, String lastName, String team, int age, String nationality, String number, int imageId, int WDCpoints, int WDCposition, String biography) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.age = age;
        this.nationality = nationality;
        this.number = number;
        this.imageId = imageId;
        this.WDCpoints = WDCpoints;
        this.WDCposition = WDCposition;
        this.biography = biography;
    }


    // Parcelable implementations, to allow for the Driver object to be passed as Intent
    protected Driver(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        team = in.readString();
        age = in.readInt();
        nationality = in.readString();
        number = in.readString();
        imageId = in.readInt();
        WDCpoints = in.readInt();
        WDCposition = in.readInt();
        biography = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(team);
        dest.writeInt(age);
        dest.writeString(nationality);
        dest.writeString(number);
        dest.writeInt(imageId);
        dest.writeInt(WDCpoints);
        dest.writeInt(WDCposition);
        dest.writeString(biography);
    }

    // required
    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Driver> CREATOR = new Creator<Driver>() {
        @Override
        public Driver createFromParcel(Parcel in) {
            return new Driver(in);
        }

        @Override
        public Driver[] newArray(int size) {
            return new Driver[size];
        }
    };


    // Getters and setters for each field of the entity
    public int getId() { return this.id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeam(){
        return this.team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality(){
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNumber(){
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getImageId() {
        return this.imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getWDCpoints() {
        return this.WDCpoints;
    }

    public void setWDCpoints(int WDCpoints) {
        this.WDCpoints = WDCpoints;
    }

    public int getWDCposition() {
        return this.WDCposition;
    }

    public void setWDCposition(int WDCposition) {
        this.WDCposition = WDCposition;
    }

    public String getBiography(){
        return this.biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
