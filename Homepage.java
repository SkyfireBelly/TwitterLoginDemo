package com.example.shubham.twitterlogindemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Homepage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
      String userName= getIntent().getStringExtra("UserName");

        TextView uname= (TextView) findViewById(R.id.tv_username);
        uname.setText(userName);

    }
}
