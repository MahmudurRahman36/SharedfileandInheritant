package com.marsgmail.sharedfileandinheritant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    EditText etEmail;
    EditText etPassword;
    EditText etConPassword;

    Button btnReset;
    Button btnSignUp;
    Button btnGoToLogin;

    TextView tvName;
    TextView tvEmail;
    TextView tvPassword;

    SharedPreferences Preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Preferences = getSharedPreferences("SharedFile", Context.MODE_PRIVATE);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConPassword = (EditText) findViewById(R.id.etConPassword);

        btnReset = (Button) findViewById(R.id.btnReset);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnGoToLogin = (Button) findViewById(R.id.btnGotoLogin);

        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPassword = (TextView) findViewById(R.id.tvPassword);

        btnReset.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnGoToLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReset:
                etName.setText("");
                etEmail.setText("");
                etPassword.setText("");
                etConPassword.setText("");

                String name = Preferences.getString("Name", null);
                String email = Preferences.getString("Email", null);
                String password = Preferences.getString("Password", null);

                tvName.setText(name);
                tvEmail.setText(email);
                tvPassword.setText(password);

                tvName.setVisibility(View.VISIBLE);
                tvEmail.setVisibility(View.VISIBLE);
                tvPassword.setVisibility(View.VISIBLE);

                break;
            case R.id.btnSignUp:
                String Name = etName.getText().toString();
                String Email = etEmail.getText().toString();
                String Password = etPassword.getText().toString();
                String ConPassword = etConPassword.getText().toString();

                if (Name.isEmpty() || Email.isEmpty() || Password.isEmpty()||ConPassword.isEmpty()) {
                    final AlertDialog.Builder dialoge = new AlertDialog.Builder(MainActivity.this);
                    dialoge.setMessage("Please Fill Up All The Input Field");
                    dialoge.setTitle("Sorry");
                    dialoge.setCancelable(true);
                    dialoge.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialoge, int which) {
                            dialoge.dismiss();

                        }
                    });
                    dialoge.show();

                } else {
                    if(Password.equals(ConPassword)) {
                        Toast.makeText(MainActivity.this, "Congratulation You Have Successfully Registered", Toast.LENGTH_SHORT).show();

                        Editor abc = Preferences.edit();
                        abc.putString("Name", Name);
                        abc.putString("Email", Email);
                        abc.putString("Password", Password);
                        abc.commit();

                        final AlertDialog.Builder dialoge = new AlertDialog.Builder(MainActivity.this);
                        dialoge.setMessage("Congratulation, Successfully Sign Up");
                        dialoge.setTitle("Confirmation");
                        dialoge.setCancelable(true);
                        dialoge.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialoge, int which) {
                                dialoge.dismiss();

                            }
                        });
                        dialoge.show();
                    }
                    else{
                        final AlertDialog.Builder dialoge = new AlertDialog.Builder(MainActivity.this);
                        dialoge.setMessage("Input Password Missmatch");
                        dialoge.setTitle("Ops");
                        dialoge.setCancelable(true);
                        dialoge.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialoge, int which) {
                                dialoge.dismiss();

                            }
                        });
                        dialoge.show();
                    }

                }
                break;
            case R.id.btnGotoLogin: {
                String LName = Preferences.getString("Name", null);
                Intent gotoLoginPage = new Intent(MainActivity.this, LoginActivity.class);
                gotoLoginPage.putExtra("Name", LName);
                startActivity(gotoLoginPage);
            }
            break;

        }
    }
}
