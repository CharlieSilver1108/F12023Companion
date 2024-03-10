// This is the 'Data Access Object' interface for the Team Entity in the Room Database
// It bridges the gap between Java and SQL

package com.example.coursework.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursework.EntityClass.Team;

import java.util.List;

@Dao
public interface TeamDao {
    @Insert
    void insert(Team team);

    @Query("SELECT * FROM teams")
    List<Team> getAllTeams();

    @Query("SELECT * FROM teams WHERE id=:idToFind")
    Team getTeamById(int idToFind);
}