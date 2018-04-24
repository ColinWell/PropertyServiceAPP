package com.antonioleiva.mvpexample.app.payment.parking;

import android.os.Bundle;
import android.app.Activity;

import com.antonioleiva.androidmvp.R;

public class ParkingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
