package com.example.inspectionapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Methods {
    //api/vehicles/decodevin/"+query+"?format=json
   // String Value="5UXWX7C5*BA"+"?format=json";

 //   @GET("api/vehicles/decodevin/5UXWX7C5*BA?format=json")
 //   Call<Model> getAllData();

    @GET("/api/vehicles/decodevin/{vin}?format=json")
    Call<Model> getInfoByVIN(@Path("vin") String vin);
}

//public interface IService {
//
//    String BASE_URL = "https://api.demo.com/";
//
//    @GET("Login") //i.e https://api.demo.com/Search?
//    Call<Products> getUserDetails(@Query("email") String emailID, @Query("password") String password)
//
//}
//    It will be called this way. Considering you did the rest of the code already.
//
//        Call<Results> call = service.getUserDetails("abc@gmail.com", "Password@123");
//        For example when a query is returned, it will look like this.
//
//        https://api.demo.com/Login?email=abc@gmail.com&password=Password@123