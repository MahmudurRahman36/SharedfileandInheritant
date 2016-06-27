package com.marsgmail.sharedfileandinheritant;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Facebook extends AppCompatActivity {
    SharedPreferences Preferences;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        Preferences = getSharedPreferences("SharedFile", Context.MODE_PRIVATE);

        tvName = (TextView) findViewById(R.id.tvName);

        String name = Preferences.getString("Name", null).toUpperCase();
        tvName.setText(name);
        tvName.setVisibility(View.VISIBLE);
    }
}
