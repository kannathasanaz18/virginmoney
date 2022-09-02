package com.example.virginmoney.service;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface API {


    enum Category {
        PEOPLE("People"),
        ROOMS("Rooms");
        public final String title;
        Category(String title) {
            this.title = title;
        }
    }


    //https://61e947967bc0550017bc61bf.mockapi.io/api/v1/people
    @GET("/people")
    Call<ArrayList<People>> getPeoples();

    @GET("/rooms")
    Call<ArrayList<Room>> getRooms();


}
