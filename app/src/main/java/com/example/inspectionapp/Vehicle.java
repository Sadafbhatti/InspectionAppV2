package com.example.inspectionapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//@Entity
public class Vehicle {

   // @PrimaryKey(autoGenerate = true)
    public int uid;
    public String vin;  //"vin":"JNKCA31A61T027494",
    public String make; //   "make":"Infiniti",
    public String model; //    "model":"I30",
    public String mileagein;
    public String mileageout;
    public String testdrive;
    public String plate;

    public Vehicle(){
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.mileagein = mileagein;
        this.mileageout = mileageout;
        this.testdrive = testdrive;
        this.plate = plate;
    }

    public Vehicle(String vin, String make, String model, String mileagein, String mileageout, String testdrive, String plate) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.mileagein = mileagein;
        this.mileageout = mileageout;
        this.testdrive = testdrive;
        this.plate = plate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMileagein() {
        return mileagein;
    }

    public void setMileagein(String mileagein) {
        this.mileagein = mileagein;
    }

    public String getMileageout() {
        return mileageout;
    }

    public void setMileageout(String mileageout) {
        this.mileageout = mileageout;
    }

    public String getTestdrive() {
        return testdrive;
    }

    public void setTestdrive(String testdrive) {
        this.testdrive = testdrive;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "uid=" + uid +
                ", vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", mileagein='" + mileagein + '\'' +
                ", mileageout='" + mileageout + '\'' +
                ", testdrive='" + testdrive + '\'' +
                ", plate='" + plate + '\'' +
                '}';
    }
}






/**
 * @Entity
 * public class User {
 *     @PrimaryKey
 *     public int uid;
 *
 *     @ColumnInfo(name = "first_name")
 *     public String firstName;
 *
 *     @ColumnInfo(name = "last_name")
 *     public String lastName;
 * }
 */
