package com.example.virginmoney.viewmodel;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.virginmoney.service.ApiClient;
import com.example.virginmoney.service.People;
import com.example.virginmoney.service.Room;
import com.example.virginmoney.utils.AppExecutors;

import java.util.List;

public class Repository {


    private static final Object LOCK = new Object();
    private static Repository sInstance;

    private final ApiClient newsApiService;
    AppExecutors mExecutor;

    public synchronized static Repository getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new Repository(context);
            }
        }
        return sInstance;
    }


    private Repository(Context context) {
        newsApiService = ApiClient.getInstance(context);

        mExecutor = AppExecutors.getInstance();


   /*     networkArticleLiveData.observeForever(new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable final List<Article> articles) {
                if (articles != null) {
                    mExecutor.getDiskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            headlinesDao.bulkInsert(articles);
                        }
                    });
                }
            }
        });*/


    }

/*
    public LiveData<List<People>> getPeopleDetails() {
        final LiveData<List<People>> networkData = newsApiService.getPeoples();
     *//*   networkData.observeForever(new Observer<List<People>>() {
            @Override
            public void onChanged(@Nullable List<People> articles) {
                if (articles != null) {
                    networkArticleLiveData.setValue(articles);
                    networkData.removeObserver(this);
                }
            }
        });*//*
       // return newsRepository.getYoutubechannels(specification,id);
        return res.getArticleByCategory(specs.getCategory());
    }*/



    //  People list
    public LiveData<List<People>> getPeopleDetails() {
        final LiveData<List<People>> networkData = newsApiService.getPeoples();
        return networkData;

    }

    //  Rooms Details
    public LiveData<List<Room>> getRoomsDetails() {
        final LiveData<List<Room>> networkData = newsApiService.getRooms();
        return networkData;

    }



}
