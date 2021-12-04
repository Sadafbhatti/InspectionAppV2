package com.example.inspectionapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DatabaseDao {



    @Query("SELECT * FROM Customer")
    List<Vehicle> getAllCustomers();

    @Query("SELECT * FROM Customer WHERE uid IN (:userIds)")
    List<Customer> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Customer findByName(String first, String last);

    @Insert
    void insertAll(Customer... users);

    @Delete
    void delete(Customer user);
}