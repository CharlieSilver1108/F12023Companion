// This is the 'Data Access Object' interface for the Driver Entity in the Room Database
// It bridges the gap between Java and SQL

package com.example.coursework.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.coursework.EntityClass.Driver;

import java.util.List;

@Dao
public interface DriverDao {
    @Insert
    void insert(Driver driver);

    @Query("SELECT * FROM drivers")
    List<Driver> getAllDrivers();

    @Query("SELECT * FROM drivers WHERE id=:idToFind")
    Driver getDriverById(int idToFind);
}