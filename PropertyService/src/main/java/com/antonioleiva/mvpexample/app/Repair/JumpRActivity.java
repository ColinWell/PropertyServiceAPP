package com.antonioleiva.mvpexample.app.Repair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.antonioleiva.mvpexample.app.R;

/**
 * Created by 85732 on 2018/5/5.
 */

public class JumpRActivity extends Activity implements View.OnClickListener{
    private Button rb;
    private Button rhistoryB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmenu);
        rb=(Button)findViewById(R.id.rb);
        rb.setOnClickListener(this);
        rhistoryB=(Button)findViewById(R.id.rhistoryB);
        rhistoryB.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rhistoryB:
                Intent intent=new Intent(JumpRActivity.this,RHistory.class);
                startActivity(intent);
                break;
            case R.id.rb:
                Intent intent1=new Intent(JumpRActivity.this,RepairActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
