package com.example.virginmoney.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.virginmoney.MainActivity;
import com.example.virginmoney.R;
import com.example.virginmoney.databinding.ActivityPeopleDetailsBinding;
import com.example.virginmoney.service.People;

public class PeopleDetailActivity extends AppCompatActivity {

    ActivityPeopleDetailsBinding binding;
    public static final String PARAM_ARTICLE = "param-article";
    private People people;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this, R.layout.activity_people_details);

       makeUiFullscreen();
       setupArticleAndListener();
    }


    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */
    @Override
    public void onBackPressed() {
       super.onBackPressed();
        finish();

    }

    private void makeUiFullscreen() {
        // When applying fullscreen layout, transparent bar works only for VERSION < 21
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            binding.getRoot().setFitsSystemWindows(true);
        }
        // Make UI fullscreen and make it load stable
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
    }

    /**
     * Extracts people from Arguments and Adds button listeners
     */
    private void setupArticleAndListener() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(PARAM_ARTICLE)) {
            final People article = bundle.getParcelable(PARAM_ARTICLE);
            if (article != null) {
                this.people = article;
                binding.setView(article);

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
