package com.example.virginmoney;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.virginmoney.databinding.ActivityMainBinding;
import com.example.virginmoney.service.API;
import com.example.virginmoney.ui.HeaderFragment;
import com.example.virginmoney.ui.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private HeaderFragment headlinesFragment;
    private final FragmentManager fragmentManager = getSupportFragmentManager();


    FragmentPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (savedInstanceState == null) {
            // Add a default fragment
            headlinesFragment = HeaderFragment.newInstance();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, headlinesFragment)
                    .commit();
        }

       // setupToolbar();



      /*  ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        //viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
      //  viewPagerAdapter= ViewPagerAdapter(getFragmentManager(), categories);
        viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager(), categories);*/

       // vpPager.setAdapter(viewPagerAdapter);


    }

    private final String[] categories = {
            API.Category.PEOPLE.name(),
            API.Category.ROOMS.name(),
    };

   /* private void setupToolbar() {
        //setSupportActionBar(binding.toolbar);
        binding.toolbar.inflateMenu(R.menu.menu_item);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // actionBar.setTitle(getString(R.string.Mangocity_news)+ "-"+getString(R.string.app_download_title));
            // actionBar.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.home_screen));
            //Remove trailing space from toolbar
            binding.toolbar.setContentInsetsAbsolute(10, 10);
        }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
