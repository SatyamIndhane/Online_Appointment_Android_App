package com.example.hp.firebasetut;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Adminprofile extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private FirebaseAuth firebaseAuth;
    private ListView listView;
    private UserListAdapter userListAdapter;
    ArrayList<String> nameList = new ArrayList<>();
    ArrayList<Userinfo> userInfoList = new ArrayList<>();

    private DatabaseReference databaseuser;

    private static int USER_DETAILS_REQUEST_CODE = 111;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchdata);




        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseuser = FirebaseDatabase.getInstance().getReference().child("user");


        userListAdapter = new UserListAdapter(this, userInfoList);

        listView = (ListView) findViewById(R.id.listviewuser);

        listView.setAdapter(userListAdapter);

        getAllUserNames(databaseuser);


        listView.setOnItemClickListener(this);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        DatabaseReference databaseuser = FirebaseDatabase.getInstance().getReference().child("user");
        getAllUserNames(databaseuser);
    }

    private void getAllUserNames(DatabaseReference databaseuser) {

        databaseuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userInfoList.clear();
//                collectNames((Map<String, Object>) dataSnapshot.getValue());

                //get Userinfo object from DataSnapShot and pass this Userinfo object to adapter

                for (DataSnapshot child : dataSnapshot.getChildren()
                        ) {

                    Userinfo userInfo = child.getValue(Userinfo.class);

                    if (userInfo != null)
                        userInfoList.add(userInfo);
                }

                //notify adapter about updated UserInfoList
                userListAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /*private void collectNames(Map<String, Object> users) {

        for (Map.Entry<String, Object> entry : users.entrySet()
                ) {

            Map singleUser = (Map) entry.getValue();

            if (singleUser != null) {
                nameList.add(String.valueOf(singleUser.get("name")));


            }


        }


        userListAdapter.notifyDataSetChanged();

        if (nameList != null) {
            Log.e("collectNames: ", nameList.toString());
        }

    }
*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this, adminpopup.class);

        Userinfo userInfo = (Userinfo) adapterView.getItemAtPosition(i);


        // Userinfo userInfo = userInfoList.get(i);

        Bundle bundle = new Bundle();

        bundle.putSerializable("user", userInfo);

        intent.putExtras(bundle);

        //Toast.makeText(this, userInfo.toString(), Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, USER_DETAILS_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.e("onActivityResult: ", "request: " + requestCode + " result: " + resultCode);


        if (requestCode == USER_DETAILS_REQUEST_CODE && resultCode == RESULT_OK) {

            Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show();
            userInfoList.clear();
            DatabaseReference databaseuser = FirebaseDatabase.getInstance().getReference().child("user");
            getAllUserNames(databaseuser);
        }

    }

    @Override
   public void onBackPressed() {
        startActivity(new Intent(this,Adminlogin.class));
        finish();
    }
}
