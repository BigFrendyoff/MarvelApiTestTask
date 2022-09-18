package com.example.marvelapi.models.comics;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ComicsData {


    @SerializedName("results")
    @Expose
    private ArrayList<ComicsResult> results = null;


    public ArrayList<ComicsResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<ComicsResult> results) {
        this.results = results;
    }

}