package com.example.inspectionapp;

import android.util.Log;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;

public class NetworkManager {
    public static final String TAG=" ==== ACTIVITY=====";
    // String query="5UXWX7C5*BA"; // must be at least 8-17 chars of vehicle+"?format=json";
    String vin,make,  model;

    public void networkcall(String myquery) {
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getInfoByVIN(myquery);
        //call.enqueue does the background threading so doesnt need to use executor
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull retrofit2.Response<Model> response) {
                Log.e(TAG, "On Response Code:" + response.code());
                assert response.body() != null;
                String SearchCriteria=response.body().getSearchCriteria();
                ArrayList<Model.Results> Results = response.body().getResults();

                for (Model.Results Result1 : Results) {
                    //5UXWX7C5*BA
                    //{"Value":"X3","ValueId":"1719","Variable":"Model","VariableId":28},{"Value":"2011","ValueId":"","Variable":"Model Year","VariableId":29}
                    String Variable=Result1.getVariable();
                    if (Result1.Variable.equals("Model")){
                        model=Result1.getValue();
                        vin=SearchCriteria.substring(4);
                    }
                    else if(Result1.Variable.equals("Make")){
                        make=Result1.getValue();
                    }
                    Log.e(TAG, "----"+ Result1.getVariable()+"---Stored--"+Variable);
                }
                if(response.code()!=200){
                    System.out.println("======API response not 200 Error=======");
                }
            }
            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                Log.e(TAG, "On Error :" + t.getMessage());
            }
        });
    }

}
