package com.example.hp.firebasetut;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Map;

/**
 * S.
 */
// adapter acts as interface bet listview data and our main activity screen
    // builder patter creates stepwise pattern
    // adapter uses 1 obj and uses to another place obj
    // adapter works as connectivity between 2 things
    //

public class UserListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Userinfo> nameList;

    public UserListAdapter(Context context, ArrayList<Userinfo> nameList) {
        this.context = context;
        this.nameList = nameList;
    }

    @Override
    public int getCount() {
        return nameList.size();
    }

    @Override
    public Object getItem(int i) {
        return nameList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.simple_textview_layout, viewGroup, false);


        TextView userName = (TextView) view.findViewById(R.id.tv_user_name);

        userName.setText(nameList.get(i).getName());

        Log.e("getView: ", nameList.get(i).getName());

        return view;
    }
}
