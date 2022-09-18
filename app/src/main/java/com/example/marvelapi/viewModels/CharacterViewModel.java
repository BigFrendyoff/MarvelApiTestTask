package com.example.marvelapi.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvelapi.api.MrvlAPICharacters;
import com.example.marvelapi.models.character.MainResponse;
import com.example.marvelapi.models.character.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Result>> heroList;
    private MrvlAPICharacters api;
    private String BASE_URL = "https://gateway.marvel.com";

    public LiveData<ArrayList<Result>> getHeroes(){
        if (heroList == null){
            heroList = new MutableLiveData<ArrayList<Result>>();
            loadHeroes();
        }
        return heroList;
    }

    private void loadHeroes(){
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(MrvlAPICharacters.class);

        api.getData("640be62b90bd0041363befc90c85476a",
                "39b00aacf48d1082406160c73df9768e",
                1, 100).enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                Log.d("APIE", "200");
                heroList.postValue(response.body().getData().getResults());
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                Log.d("APIE", t.toString());
            }
        });
    }


}
