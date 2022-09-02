package com.example.virginmoney.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.virginmoney.R;
import com.example.virginmoney.databinding.FragmentHeadlineBinding;
import com.example.virginmoney.service.API;
import com.google.android.material.tabs.TabLayout;

public class HeaderFragment extends Fragment implements TabLayout.BaseOnTabSelectedListener {


    FragmentHeadlineBinding binding;
    private final String[] categories = {
            API.Category.PEOPLE.name(),
            API.Category.ROOMS.name(),
    };

    public static HeaderFragment newInstance() {
        return new HeaderFragment();
    }
    public HeaderFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_headline, container, false);

        ViewCompat.setElevation(binding.tablayoutHeadlines, getResources().getDimension(R.dimen.tab_layout_elevation));

        binding.tablayoutHeadlines.setOnTabSelectedListener(this);
        if (getActivity() != null) {
            ViewPagerAdapter viewPager = new ViewPagerAdapter(getChildFragmentManager(), categories);
            binding.viewpagerHeadlines.setAdapter(viewPager);
            binding.tablayoutHeadlines.setupWithViewPager(binding.viewpagerHeadlines);

            float myTabLayoutSize = 360;
            //if (DeviceInfo.getWidthDP(this) >= myTabLayoutSize ){
            //    binding.tablayoutHeadlines.setTabMode(TabLayout.MODE_FIXED);
            //} else {
              //  binding.tablayoutHeadlines.setTabMode(TabLayout.MODE_AUTO);
            //}
            setupTabIcons();
        }



        return this.binding.getRoot();
    }

    private void setupTabIcons() {
        TabLayout.Tab tab;
        for (int i = 0; i < categories.length; i++) {
            tab = binding.tablayoutHeadlines.getTabAt(i);
            if (tab != null) {
               // tab.setIcon(categoryIcons[i]).setText(categories[i]);
              tab.setText(categories[i]);
            }
        }
    }

    /**
     * Called when a tab enters the selected state.
     *
     * @param tab The tab that was selected
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    /**
     * Called when a tab exits the selected state.
     *
     * @param tab The tab that was unselected
     */
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    /**
     * Called when a tab that is already selected is chosen again by the user. Some applications may
     * use this action to return to the top level of a category.
     *
     * @param tab The tab that was reselected.
     */
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
