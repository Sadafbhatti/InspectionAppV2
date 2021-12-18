package com.example.inspectionapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DatabaseDao {

    @Insert
    void insertAllData(Customer customer);

    //Select All Data
    @Query("select * from  customer")
    List<Customer> getAllData();

    //DELETE DATA
    @Query("delete from customer where `key`= :id")
    void deleteData(int id);

    //Update Data
    @Query("update customer SET name= :name ,email =:email, phone =:phone where `key`= :key")
    void updateData(String name, String email, String phone, int key);

/*
    @Query("SELECT * FROM Customer")
    List<Vehicle> getAllCustomers();

    @Query("SELECT * FROM Customer WHERE uid IN (:key)")
    List<Customer> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Customer WHERE name LIKE :name AND " +
            "reason LIKE :reason LIMIT 1")
    Customer findByName(String name);

    @Insert
    void insertAll(Customer customer);

    @Delete
    void delete(Customer customer);
   */
}
