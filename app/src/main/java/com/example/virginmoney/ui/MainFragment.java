package com.example.virginmoney.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virginmoney.R;
import com.example.virginmoney.adapter.ItemAdapter;
import com.example.virginmoney.adapter.ItemRoomAdapter;
import com.example.virginmoney.databinding.MainFragmentBinding;
import com.example.virginmoney.service.API;
import com.example.virginmoney.service.People;
import com.example.virginmoney.service.Room;
import com.example.virginmoney.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements ItemAdapter.NewsAdapterListener, ItemRoomAdapter.NewsAdapterListener {


    MainFragmentBinding binding;
    public static final String PARAM_CATEGORY = "param-category";
    public static final String PARAM_LIST_STATE = "param-state";
    private Parcelable listState;

    private API.Category newsCategory;
    List<People> people_list = new ArrayList<>();
    List<Room> room_list = new ArrayList<>();

    private final ItemAdapter newsAdapter = new ItemAdapter(null, this);
    private final ItemRoomAdapter newsRoomAdapter = new ItemRoomAdapter(null, this);

    public static MainFragment newInstance(API.Category category) {
        MainFragment fragment = new MainFragment();
        if (category == null) {
            return fragment;
        }
        Bundle args = new Bundle();
        args.putString(PARAM_CATEGORY, category.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            newsCategory = API.Category
                    .valueOf(getArguments().getString(PARAM_CATEGORY));
        } else {
           // showSaved = true;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil
                .inflate(inflater, R.layout.main_fragment, container, false);

        RecyclerView recyclerView = binding.rvNewsPosts;
        recyclerView.setAdapter(newsAdapter);



        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(PARAM_LIST_STATE);
        }

        NewsViewModel viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        if (newsCategory.title.equalsIgnoreCase("PEOPLE")) {
            if (people_list.size() == 0) {
                viewModel.getPeoples().observe(getViewLifecycleOwner(), new Observer<List<People>>() {

                    @Override
                    public void onChanged(@Nullable List<People> People) {
                        if (People != null) {
                            newsAdapter.setPeoples(People);

                        }
                    }
                });
            } else {
                newsAdapter.setPeoples(people_list);
            }
        }else {


            if (room_list.size() == 0) {
                viewModel.getRooms().observe(getViewLifecycleOwner(), new Observer<List<Room>>() {

                    @Override
                    public void onChanged(@Nullable List<Room> rooms) {
                        if (rooms != null) {
                            RecyclerView recyclerView_w = binding.rvNewsPosts;
                            recyclerView_w.setAdapter(newsRoomAdapter);
                            newsRoomAdapter.setPeoples(rooms);

                        }
                    }
                });
            } else {
                RecyclerView recyclerView_w = binding.rvNewsPosts;
                recyclerView_w.setAdapter(newsRoomAdapter);
                newsRoomAdapter.setPeoples(room_list);
            }
        }
    }

    @Override
    public void onNewsItemClicked(People people) {
        Intent intent = new Intent(getContext(), PeopleDetailActivity.class);
        intent.putExtra(PeopleDetailActivity.PARAM_ARTICLE, people);
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);
        binding.rvNewsPosts.setLayoutAnimation(controller);
        binding.rvNewsPosts.scheduleLayoutAnimation();

        if (getActivity() != null) {
            getActivity().overridePendingTransition(R.anim.slide_up_animation, R.anim.fade_exit_transition);
            //getActivity().finish();
        }
        startActivity(intent);


    }

    @Override
    public void onItemOptionsClicked(People people) {

    }

    @Override
    public void onNewsItemClicked(Room people) {

    }

    @Override
    public void onItemOptionsClicked(Room people) {

    }
}
