package com.example.virginmoney.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.virginmoney.service.API;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private final MainFragment[] newsFragments;


    public ViewPagerAdapter(FragmentManager fm, String[] categories) {
        super(fm);
        newsFragments = new MainFragment[categories.length];
        for (int i = 0; i < categories.length; i++) {
            newsFragments[i] = MainFragment.newInstance(API.Category.valueOf(categories[i]));
        }
    }

/*    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        newsFragments = new MainFragment[0];
        for (int i = 0; i < 2; i++) {
            newsFragments[i] = MainFragment.newInstance(API.Category.valueOf("F!DFSDFF"));
        }
    }*/

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return newsFragments[position];
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
         return newsFragments == null ? 0 : newsFragments.length;
    }
}
