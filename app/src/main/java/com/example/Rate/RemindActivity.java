package com.example.Rate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class RemindActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton fab_main, fab_add, fab_tran;
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    com.example.Rate.CustomAdapter customAdapter;
    ListView list;
    ArrayList<Data> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);

        list = findViewById(R.id.list);
        fab_main = findViewById(R.id.fab_main);
        fab_add = findViewById(R.id.fab_add);
        fab_tran = findViewById(R.id.fab_tran);
        fab_main.setOnClickListener(this);
        fab_add.setOnClickListener(this);
        fab_tran.setOnClickListener(this);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);


        loadData();
        customAdapter = new com.example.Rate.CustomAdapter(arrayList, this);
        list.setAdapter(customAdapter);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.fab_main:
                anim();
                break;

            case R.id.fab_tran:
                anim();
                Intent intent  = new Intent(RemindActivity.this, com.example.Rate.MainActivity.class);
                startActivity(intent);
                break;
            case R.id.fab_add:
                Intent intent2  = new Intent(RemindActivity.this, AddListActivity.class);
                intent2.putExtra("position",-1);
                startActivity(intent2);
                anim();
                break;
        }
    }

    public void anim(){

        if (isFabOpen) {
            fab_tran.startAnimation(fab_close);
            fab_add.startAnimation(fab_close);
            fab_tran.setClickable(false);
            fab_add.setClickable(false);
            isFabOpen = false;
        } else {
            fab_tran.startAnimation(fab_open);
            fab_add.startAnimation(fab_open);
            fab_tran.setClickable(true);
            fab_add.setClickable(true);
            isFabOpen = true;
        }
    }


    public void loadData(){
        Gson gson = new Gson();
        SharedPreferences prefs = getSharedPreferences("obj",MODE_PRIVATE);
        String json = prefs.getString("obj","");
        ArrayList<Data> alt;
        alt = gson.fromJson(json, new TypeToken<ArrayList<Data>>(){}.getType());
        if (alt != null) arrayList.addAll(alt);
    }

}
