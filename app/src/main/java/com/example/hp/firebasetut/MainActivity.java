package com.example.hp.firebasetut;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonregister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    //firebase auth variable
    private FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//what is use of this
        buttonregister = (Button) findViewById(R.id.buttonregister);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonregister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view == buttonregister) {
            registerUser();
        }
        if (view == textViewSignin) {
            //open login activity here

            startActivity(new Intent(this, LoginActivity.class));
            finish();

        }

    }

    private void registerUser() {
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();//what is trim
        String Expn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
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

        if (!email.matches(Expn) && email.length() > 0) {
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        //For everything acceptble
        //adding progress bar
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        //Create user with email id password
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()) {
                    //user succesfully logged
                    //switch to next activity
                    Toast.makeText(MainActivity.this, "Registered Succesfully", Toast.LENGTH_LONG).show();
                    progressDialog.cancel();

                    //--------------------------------------------------------------------ADDED LATER---------------
                    progressDialog.setMessage("log IN in process...");
                    progressDialog.show();

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));

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

                                    }

                                }
                            });
                    //----------------------------------------------------------------------------------------------------

                }

                Toast.makeText(MainActivity.this, "login succesfull", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Profileactivity.class));
                /*else {

                    Log.d("Fail", "h");
                    Toast.makeText(MainActivity.this, "Registeration failed", Toast.LENGTH_LONG).show();
                }*/

            }
        });


    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

}

