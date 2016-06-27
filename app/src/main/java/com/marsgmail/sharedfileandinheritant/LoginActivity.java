package com.marsgmail.sharedfileandinheritant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvName;

    EditText etPassword;

    Button btnBack;
    Button btnLogin;

    SharedPreferences Preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String name = getIntent().getExtras().getString("Name");

        Preferences = getSharedPreferences("SharedFile", Context.MODE_PRIVATE);

        tvName = (TextView) findViewById(R.id.tvName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        tvName.setText(name);
        tvName.setVisibility(View.VISIBLE);

        btnBack.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                Intent gotoSignUp = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(gotoSignUp);
                break;

            case R.id.btnLogin:
                String password = Preferences.getString("Password", null);
                String Password = etPassword.getText().toString();

                Toast.makeText(LoginActivity.this, "You Have Entered " + Password, Toast.LENGTH_SHORT).show();


                if (Password.equals(password)) {
                    Toast.makeText(LoginActivity.this, "You Have Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent gotoFacebook = new Intent(LoginActivity.this, Facebook.class);
                    startActivity(gotoFacebook);
                } else {
                    Toast.makeText(LoginActivity.this, "But The Password didnot match", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Please Enter Correct Password", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
