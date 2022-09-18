package com.example.marvelapi.api;



import com.example.marvelapi.models.character.MainResponse;
import com.example.marvelapi.models.comics.ComicsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MrvlAPICharacters {
    @GET("/v1/public/characters")
    Call<MainResponse> getData(@Query("apikey") String apiKey,
                               @Query("hash") String hash,
                               @Query("ts") Integer timestamp,
                               @Query("limit") Integer limit);
    @GET("v1/public/characters/{id}}/comics")
    Call<ComicsResponse> getComicsData(@Path("id") Integer id,
                                       @Query("apikey") String apiKey,
                                       @Query("hash") String hash,
                                       @Query("ts") Integer timestamp,
                                       @Query("limit") Integer limit);
}
