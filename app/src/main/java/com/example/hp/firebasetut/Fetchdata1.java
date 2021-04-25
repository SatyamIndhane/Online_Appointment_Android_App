package com.example.hp.firebasetut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Fetchdata1 extends AppCompatActivity implements View.OnClickListener {

    private Button buttonEdit;
    private Button buttonDelete;
    private Button buttonUpdate;
    Userinfo userinfo;
    EditText tvName;
    EditText tvContact;
    EditText tvReason;
    EditText tvReason1;
    EditText tvDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdata1);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        userinfo = (Userinfo) bundle.getSerializable("user"  );


        tvName = (EditText) findViewById(R.id.tvName);

        tvContact = (EditText) findViewById(R.id.tvContact);

        tvReason = (EditText) findViewById(R.id.tvReason);

        tvReason1 = (EditText) findViewById(R.id.tvReason1);

        tvDate = (EditText) findViewById(R.id.tvDate);

        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonEdit.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);

        try {
            if (userinfo != null) {
                if (userinfo.getName() != null) {
                    tvName.setText(userinfo.getName());
                }
                if (userinfo.getContact() != null) {
                    tvContact.setText(userinfo.getContact());
                }
                if (userinfo.getReason() != null) {
                    tvReason.setText(userinfo.getReason());
                }
                if (userinfo.getRejectreason() != null) {
                    tvReason1.setText(userinfo.getRejectreason());
                }
                if (userinfo.getTime() != null) {
                    tvDate.setText(userinfo.getTime());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Toast.makeText(this, userinfo.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonDelete:
                deleteuserinfo();
                break;

            case R.id.buttonEdit:
                toggleuserinfo();
                buttonEdit.setVisibility(View.INVISIBLE);
                buttonDelete.setVisibility(View.INVISIBLE);
                buttonUpdate.setVisibility(View.INVISIBLE);
                break;

            case R.id.buttonUpdate:
                edituserinfo();
        }
    }

    private void edituserinfo() {


        String Name = tvName.getText().toString();
        String Contact = tvContact.getText().toString();
        String Reason = tvReason.getText().toString();
        String Reason1 = tvReason1.getText().toString();
        String Date = tvDate.getText().toString();
        String userId = userinfo.getUserid();


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(userinfo.getUserid());
        Userinfo userinfo = new Userinfo(userId, Name, Contact, Reason, Reason1, Date);
        databaseReference.setValue(userinfo);
        Toast.makeText(this, Name + " Updated", Toast.LENGTH_SHORT).show();


        toggleuserinfo();
        buttonEdit.setVisibility(View.GONE);
        buttonDelete.setVisibility(View.VISIBLE);
        buttonUpdate.setVisibility(View.GONE);

    }

    private void toggleuserinfo() {
        tvName.setEnabled(true);
        tvContact.setEnabled(true);
        tvReason.setEnabled(true);
        tvReason1.setEnabled(true);
        tvDate.setEnabled(true);

    }

    private void deleteuserinfo() {

        String Name = tvName.getText().toString();
        String userId = userinfo.getUserid();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("user").child(userinfo.getUserid());
        databaseReference.removeValue();

        Toast.makeText(this, Name + " Deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();

    }
}
