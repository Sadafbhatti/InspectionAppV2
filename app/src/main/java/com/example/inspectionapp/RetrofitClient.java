package com.example.inspectionapp;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private static Retrofit retrofit;
    private static final String BASE_URL="https://vpic.nhtsa.dot.gov/";
    private static OkHttpClient okHttpClient;
    //api/vehicles/decodevin/"+query+"?format=json
    //String query="5UXWX7C5*BA"+"?format=json";


    public static Retrofit getRetrofitInstance(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder().build();
        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
