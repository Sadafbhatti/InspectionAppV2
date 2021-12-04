package com.example.inspectionapp;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private static Retrofit retrofit;
    private static final String BASE_URL="https://vpic.nhtsa.dot.gov/";
    //api/vehicles/decodevin/"+query+"?format=json
    //String query="5UXWX7C5*BA"+"?format=json";

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
