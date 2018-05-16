package com.antonioleiva.mvpexample.app.EmergencyContact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.main.MainActivity;

/**
 * Created by 85732 on 2018/4/23.
 */

public class EmergencyContactActivity extends Activity implements View.OnClickListener{
    private Button help;
    private TextView Ename;
    private TextView Ephone;
    private Button buttonE;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        Intent emerintent=getIntent();
        String name=emerintent.getStringExtra("name");
        String phone=emerintent.getStringExtra("phone");
        Ename=(TextView) findViewById(R.id.name);
        Ephone=(TextView)findViewById(R.id.phone);
        Ename.setText(name);
        Ephone.setText(phone);
        buttonE=(Button)findViewById(R.id.buttonE);
        buttonE.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonE:
                Intent intent=new Intent(EmergencyContactActivity.this, MainActivity.class);
                startActivity(intent);

        }
    }
}
