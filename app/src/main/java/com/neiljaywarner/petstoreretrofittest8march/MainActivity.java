package com.neiljaywarner.petstoreretrofittest8march;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.api.PetApi;
import io.swagger.client.model.Pet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://petstore.swagger.io/v2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // http://petstore.swagger.io/v2/pet/findByStatus?status=pending

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // TOOD: use client to get logging.

        PetApi petApi = retrofit.create(PetApi.class);
        ArrayList<String> statusList = new ArrayList<>();
        statusList.add("pending");
        Call<List<Pet>> callPetList = petApi.findPetsByStatus(statusList);
        callPetList.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                Log.e("NJW", "onResponse; code=" + response.code());
                List<Pet> petList = response.body();
                Log.e("NJW", "pet1=" + petList.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Log.e("NJW", "onFailure: message=" + t.getMessage());

            }
        });
        //Call<Void> callCreateUser = gatherAPI.createAccount(registrationInfo);




    }
}
