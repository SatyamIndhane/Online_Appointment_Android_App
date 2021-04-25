package com.example.hp.firebasetut;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profileactivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;


    private DatabaseReference databaseuser;//variable user for database reference
    private TextView textViewlist, textView2;
    private Button buttonadd, buttonlogout;
    private EditText editTextname, editTextcontact, editTextreason, editTexttime, editTextrejectreason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileactivity);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        editTextname = (EditText) findViewById(R.id.editTextname);
        editTextcontact = (EditText) findViewById(R.id.editTextcontact);
        editTextreason = (EditText) findViewById(R.id.editTextreason);

        editTextrejectreason = (EditText) findViewById(R.id.editTextrejectonreason);


        editTexttime = (EditText) findViewById(R.id.editTexttime);
        buttonadd = (Button) findViewById(R.id.buttonadd);
        buttonlogout = (Button) findViewById(R.id.buttonlogout);


        textViewlist = (TextView) findViewById(R.id.textViewlist);


        databaseuser = FirebaseDatabase.getInstance().getReference("user");


        buttonlogout.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        textViewlist.setOnClickListener(this);
    }

    private void saveinfo() {
        String name = editTextname.getText().toString().trim();
        String contact = editTextcontact.getText().toString().trim();
        String reason = editTextreason.getText().toString().trim();
        String rejectreason = editTextrejectreason.getText().toString().trim();
        String time = editTexttime.getText().toString().trim();


        //firebaseAuth.get FirebaseUser user = CurrentUser();


        String id = databaseuser.push().getKey();
        //create string inside user
        Userinfo userinfo = new Userinfo(id, name, contact, reason, time, rejectreason);
        databaseuser.child(id).setValue(userinfo);

        Toast.makeText(this, "DATA SAVED", Toast.LENGTH_LONG).show();

        editTextname.setText("");
        editTextcontact.setText("");
        editTextreason.setText("");
        editTextrejectreason.setText("");
        editTexttime.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v == buttonlogout) {
            firebaseAuth.signOut();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        if (v == buttonadd) {
            saveinfo();

        }
        if (v.getId() == R.id.textViewlist) {

            startActivity(new Intent(this, fetchdata.class));

             finish();

        }

    }

    @Override
    public void onBackPressed() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(
                Profileactivity.this);
        builder.setTitle("Alert");
        builder.setMessage("Are you sure want to exit ??");

        builder.setPositiveButton("Exit",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,

                                        int which) {
                        dialog.dismiss();
                        Profileactivity.this.finish();
                    }
                });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();


    }
}
