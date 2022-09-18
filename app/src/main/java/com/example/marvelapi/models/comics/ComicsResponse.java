package com.example.marvelapi.models.comics;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComicsResponse {

    @SerializedName("data")
    @Expose
    private ComicsData data;


    public ComicsData getData() {
        return data;
    }

    public void setData(ComicsData data) {
        this.data = data;
    }

}