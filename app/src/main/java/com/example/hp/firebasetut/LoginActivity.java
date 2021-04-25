package com.example.hp.firebasetut;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignIn.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);


        if (firebaseAuth.getCurrentUser() != null) {
            //stranger manager activity
            Toast.makeText(this, "Already login jumping to new activity", Toast.LENGTH_SHORT).show();

        }


    }

    private void userlogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().toString();

        if (TextUtils.isEmpty(email)) {
            //check for empty field
            Toast.makeText(this, "Please enter email ID", Toast.LENGTH_SHORT).show();
            return;
            // break;
        }

        if (TextUtils.isEmpty(password)) {
            // check for empty field
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        //For everything acceptble
        //adding progress bar

        progressDialog.setMessage("log IN in process...");
        progressDialog.show();

        //auth method
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //Start profile ativity
                            //stranger management
                            progressDialog.dismiss();


                            // Toast.makeText(this,"login succesfull",Toast.LENGTH_SHORT).show();

                            Toast.makeText(LoginActivity.this, "login succesfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Profileactivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Email id or Password is Incorrect..", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


    }


    @Override
    public void onClick(View v) {

        if (v == buttonSignIn) {
            userlogin();
        }
        if (v == textViewSignup) {

            startActivity(new Intent(this, MainActivity.class));
            finish();

        }

    }


}
