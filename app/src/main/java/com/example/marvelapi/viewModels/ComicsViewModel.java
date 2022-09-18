package com.example.marvelapi.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.marvelapi.api.MrvlAPICharacters;
import com.example.marvelapi.models.comics.ComicsResponse;
import com.example.marvelapi.models.comics.ComicsResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComicsViewModel extends ViewModel {
    private String BASE_URL = "https://gateway.marvel.com/";
    private MrvlAPICharacters api;
    private MutableLiveData<ArrayList<ComicsResult>> comicsList = new MutableLiveData<ArrayList<ComicsResult>>();

    public LiveData<ArrayList<ComicsResult>> getComics(Integer id){
        loadComics(id);
        return comicsList;
    }

   public void clearList(){
        comicsList.setValue(new ArrayList<>());
   }

    private void loadComics(Integer id){
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(MrvlAPICharacters.class);
        api.getComicsData(id,"640be62b90bd0041363befc90c85476a",
                "39b00aacf48d1082406160c73df9768e",
                1, 100).enqueue(new Callback<ComicsResponse>() {
            @Override
            public void onResponse(Call<ComicsResponse> call, Response<ComicsResponse> response) {
                Log.d("APIE", response.code() + "");
                Log.d("APIE", "Triggered!");
//                comicsList.setValue(response.body().getData().getResults());
                comicsList.postValue(response.body().getData().getResults());
            }

            @Override
            public void onFailure(Call<ComicsResponse> call, Throwable t) {
                Log.d("APIE", t.toString());

            }
        });
    }
}
