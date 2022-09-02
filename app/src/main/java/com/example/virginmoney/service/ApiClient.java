package com.example.virginmoney.service;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.virginmoney.utils.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class ApiClient {

    private static final String API_URL = "https://61e947967bc0550017bc61bf.mockapi.io/api/v1/";

    private static final Object LOCK = new Object();
    private static API sNewsApi;
    private static ApiClient sInstance;


    // Required private constructor
    private ApiClient() {
    }

    public static ApiClient getInstance(Context context) {
        if (sInstance == null || sNewsApi == null) {
            synchronized (LOCK) {
                // 5 MB of cache
                Cache cache = new Cache(context.getApplicationContext().getCacheDir(), 5 * 1024 * 1024);

                // Used for cache connection
                Interceptor networkInterceptor = new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        // set max-age and max-stale properties for cache header
                        CacheControl cacheControl = new CacheControl.Builder()
                                .maxAge(1, TimeUnit.HOURS)
                                .maxStale(3, TimeUnit.DAYS)
                                .build();
                        return chain.proceed(chain.request())
                                .newBuilder()
                                .removeHeader("Pragma")
                                .header("Cache-Control", cacheControl.toString())
                                .build();
                    }
                };

                // For logging
                HttpLoggingInterceptor loggingInterceptor =
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);


                // Building OkHttp client
                OkHttpClient client = new OkHttpClient.Builder()
                        .cache(cache)
                        .addNetworkInterceptor(networkInterceptor)
                        .addInterceptor(loggingInterceptor)
                        .build();

                // Configure GSON
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Date.class, new DateDeserializer())
                        .create();

                // Retrofit Builder
                Retrofit.Builder builder =
                        new Retrofit
                                .Builder()
                                .baseUrl(API_URL)
                                .client(client)
                                .addConverterFactory(GsonConverterFactory.create(gson));
                // Set NewsApi instance
                sNewsApi = builder.build().create(API.class);
                sInstance = new ApiClient();
            }
        }
        return sInstance;
    }

    ArrayList<People> recyclerDataArrayList;
    ArrayList<Room> roomArrayList;

    public LiveData<List<People>> getPeoples() {



        final MutableLiveData<List<People>> networkArticleLiveData = new MutableLiveData<>();
        final MutableLiveData<People> networkArticleLive= new MutableLiveData<>();

        //Call<People> networkCall = sNewsApi.getPeoples();
        Call<ArrayList<People>> networkCall = sNewsApi.getPeoples();


        networkCall.enqueue(new Callback<ArrayList<People>>() {
            @Override
            public void onResponse(Call<ArrayList<People>> call, retrofit2.Response<ArrayList<People>> response) {

                if (response.isSuccessful()) {
                    recyclerDataArrayList = response.body();

                    for (int i = 0; i < recyclerDataArrayList.size(); i++) {

                    }
                    networkArticleLiveData.setValue(recyclerDataArrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<People>> call, Throwable t) {

            }
        });
        return networkArticleLiveData;
    }

    public LiveData<List<Room>> getRooms() {



        final MutableLiveData<List<Room>> networkArticleLiveData = new MutableLiveData<>();

        //Call<People> networkCall = sNewsApi.getPeoples();
        Call<ArrayList<Room>> networkCall = sNewsApi.getRooms();


        networkCall.enqueue(new Callback<ArrayList<Room>>() {
            @Override
            public void onResponse(Call<ArrayList<Room>> call, retrofit2.Response<ArrayList<Room>> response) {

                if (response.isSuccessful()) {
                    roomArrayList = response.body();

                    for (int i = 0; i < roomArrayList.size(); i++) {

                    }
                    networkArticleLiveData.setValue(roomArrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Room>> call, Throwable t) {

            }
        });
        return networkArticleLiveData;
    }

}
