package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.currency.DI.ServiceLocator;
import com.example.currency.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServiceLocator.getInstance().initializeRepository(getApplication());

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }
}