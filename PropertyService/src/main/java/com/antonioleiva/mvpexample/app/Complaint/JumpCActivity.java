package com.antonioleiva.mvpexample.app.Complaint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.antonioleiva.mvpexample.app.Decoration.DecorationActivity;
import com.antonioleiva.mvpexample.app.Decoration.DecorationHistoryActivity;
import com.antonioleiva.mvpexample.app.Decoration.JumpDActivity;
import com.antonioleiva.mvpexample.app.R;

/**
 * Created by 85732 on 2018/5/6.
 */

public class JumpCActivity  extends Activity implements View.OnClickListener{
    private Button cb;
    private Button chistoryB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmenu);
        cb=(Button)findViewById(R.id.cb);
        cb.setOnClickListener(this);
        chistoryB=(Button)findViewById(R.id.chistoryB);
        chistoryB.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cb:
                Intent intent=new Intent(JumpCActivity.this,ComplaintActivity.class);
                startActivity(intent);
                break;
            case R.id.chistoryB:
                Intent intent1=new Intent(JumpCActivity.this,CHistory.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
