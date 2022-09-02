package com.example.virginmoney.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.virginmoney.service.ApiClient;
import com.example.virginmoney.service.People;
import com.example.virginmoney.service.Room;
import com.example.virginmoney.utils.AppExecutors;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private final Repository repository;
    ApiClient newsApiService;
    AppExecutors mExecutor;

    public MutableLiveData<Integer> progress = new MutableLiveData<>();

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = Repository.getInstance(application.getApplicationContext());

    }



    public LiveData<List<People>> getPeoples() {
        progress.setValue(0);
        return repository.getPeopleDetails();
    }

    public LiveData<List<Room>> getRooms() {
        progress.setValue(0);
        return repository.getRoomsDetails();
    }

}
