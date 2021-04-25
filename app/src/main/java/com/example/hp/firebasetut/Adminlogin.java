package com.example.hp.firebasetut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Adminlogin extends AppCompatActivity {
    EditText editText1,editText2;
    Button button1;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        editText1 = (EditText) findViewById(R.id.adminemail);
        editText2 = (EditText)findViewById(R.id.adminpassword);
        button1 = (Button)findViewById(R.id.buttonadminlogin);
        text1 = (TextView)findViewById(R.id.textviewtosplitlogin);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (( editText1.getText().toString().equals("admin")) && (editText2.getText().toString().equals("admin"))){
                    startActivity(new Intent(Adminlogin.this, Adminprofile.class));
                    finish();
                }else{
                    Toast.makeText(Adminlogin.this,"Enter the correct password",Toast.LENGTH_LONG).show();
                }
            }
        });
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Adminlogin.this, Splitlogin.class));
                finish();

            }
        });
    }

}
