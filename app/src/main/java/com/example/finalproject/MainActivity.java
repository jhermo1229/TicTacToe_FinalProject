package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

/**
 * Main activity of the final project
 */
public class MainActivity extends AppCompatActivity {

    private FrameLayout homeFrame;
    public static int playerOneScore = 0, playerTwoScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Created a fragment which will be used as home page
        homeFrame = findViewById(R.id.homeFrame);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homeFrame, new HomeFragment());
        transaction.commit();

    }
}