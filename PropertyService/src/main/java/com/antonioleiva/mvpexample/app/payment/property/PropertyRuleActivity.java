package com.antonioleiva.mvpexample.app.payment.property;

import android.os.Bundle;
import android.app.Activity;

import com.antonioleiva.androidmvp.R;

public class PropertyRuleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_rule);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
