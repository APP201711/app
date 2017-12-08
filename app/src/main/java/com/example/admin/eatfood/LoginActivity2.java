package com.example.admin.eatfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity2 extends AppCompatActivity {

    private String userUID;
    private Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //跳到註冊畫面
        btn_register = (Button) findViewById(R.id.btn_register);

        Button nextPageBtn = (Button) findViewById(R.id.btn_register);
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity2.this, SignActivity.class);
                startActivity(intent);
            }
        });

    }
    public void login(View v){

        final String username = ((EditText)findViewById(R.id.Username)).getText().toString();
        final String password = ((EditText)findViewById(R.id.password)).getText().toString();

        Log.d("AUTH", username+"/"+password);
        User usr = User.login(username,password);
        Log.e("LoginStatus", String.valueOf(usr.LoginStatus));
        if(usr.LoginStatus){
            Intent intent = new Intent();
            intent.setClass(LoginActivity2.this, PostActivity.class);
            startActivity(intent);
        }

    }

}

