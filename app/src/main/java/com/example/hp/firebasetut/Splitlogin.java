package com.example.hp.firebasetut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Splitlogin extends AppCompatActivity  {
Button buttonuser,buttonadmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitlogin);
        buttonadmin = (Button)findViewById(R.id.adminlogin);
        buttonuser = (Button)findViewById(R.id.userlogin);
        buttonuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Splitlogin.this,LoginActivity.class));
                finish();
            }
        });
        buttonadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Splitlogin.this, Adminlogin.class));
                finish();
            }
        });
    }

}
