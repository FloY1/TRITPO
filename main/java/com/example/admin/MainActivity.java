package com.example.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void onMyButtonClick(View view){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);

    }
    public void addClick(View view){
        Intent intent = new Intent(this, AddText.class);
        startActivity(intent);
    }
    public void statisticClickl(View view){
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);
    }
    public void Exit(View view){
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
